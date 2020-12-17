package mil.usaf.aircraftapi.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import mil.usaf.aircraftapi.aircraftmodel.AircraftModel;
import mil.usaf.aircraftapi.manufacturer.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryTests {

  @Autowired private TestEntityManager entityManager;

  private Category category;

  @BeforeEach
  public void setup() {
    category = new Category();
    category.setName("Top Secret Prototype");
  }

  @Test
  public void test_getId() {
    entityManager.persistAndFlush(category);
    assertNotNull(category.getId());
  }

  @Test
  public void test_getName() {
    assertEquals(category.getName(), "Top Secret Prototype");
  }

  @Test
  public void test_nameCannotBeBlank() {
    category.setName("");
    assertThrows(
        ConstraintViolationException.class,
        () -> {
          entityManager.persist(category);
        });
  }

  @Test
  public void test_nameMustBeUnique() {
    entityManager.persist(category);
    Category anotherCategory = new Category();
    anotherCategory.setName("Top Secret Prototype");

    assertThrows(
        PersistenceException.class,
        () -> {
          entityManager.persist(anotherCategory);
        });
  }

  @Test
  public void test_getCreatedAt() {
    entityManager.persistAndFlush(category);
    assertNotNull(category.getCreatedAt());
  }

  @Test
  public void test_toString() {
    entityManager.persistAndFlush(category);
    String categoryString = category.toString();
    assertTrue(categoryString.contains("id="));
    assertTrue(categoryString.contains("name=Top Secret Prototype"));
    assertTrue(categoryString.contains("createdAt="));
  }

  @Test
  public void test_getAircraftModels() {
    entityManager.persistAndFlush(category);

    Category experimental = new Category();
    experimental.setName("Experimental");
    entityManager.persistAndFlush(experimental);

    Manufacturer bell = new Manufacturer();
    bell.setName("Bell");
    entityManager.persistAndFlush(bell);

    Manufacturer northAmerican = new Manufacturer();
    northAmerican.setName("North American Aviation");
    entityManager.persistAndFlush(northAmerican);

    AircraftModel x1 = new AircraftModel();
    AircraftModel x2 = new AircraftModel();
    AircraftModel x15 = new AircraftModel();
    x1.setName("X-1");
    x1.setManufacturer(bell);
    x1.setCategory(category);
    entityManager.persistAndFlush(x1);

    x2.setName("X-2");
    x2.setManufacturer(bell);
    x2.setCategory(category);
    entityManager.persistAndFlush(x2);

    x15.setName("X-15");
    x15.setManufacturer(northAmerican);
    x15.setCategory(experimental);
    entityManager.persistAndFlush(x15);

    entityManager.refresh(category);
    Set<AircraftModel> result = category.getAircraftModels();
    assertTrue(result.contains(x1));
    assertTrue(result.contains(x2));
    assertFalse(result.contains(x15));
  }
}
