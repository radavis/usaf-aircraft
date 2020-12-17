package mil.usaf.aircraftapi.manufacturer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import mil.usaf.aircraftapi.aircraftmodel.AircraftModel;
import mil.usaf.aircraftapi.category.Category;
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
public class ManufacturerTests {

  @Autowired private TestEntityManager entityManager;

  private Manufacturer manufacturer;

  @BeforeEach
  public void setup() {
    manufacturer = new Manufacturer();
    manufacturer.setName("ACME Aeronautics");
    manufacturer.setLocation("Baltimore, Maryland, United States");
  }

  @Test
  public void test_getId() {
    entityManager.persistAndFlush(manufacturer);
    assertNotNull(manufacturer.getId());
  }

  @Test
  public void test_getName() {
    assertEquals(manufacturer.getName(), "ACME Aeronautics");
  }

  @Test
  public void test_nameCannotBeBlank() {
    manufacturer.setName("");
    assertThrows(
        ConstraintViolationException.class,
        () -> {
          entityManager.persist(manufacturer);
        });
  }

  @Test
  public void test_nameMustBeUnique() {
    entityManager.persist(manufacturer);
    Manufacturer anotherManufacturer = new Manufacturer();
    anotherManufacturer.setName("ACME Aeronautics");

    assertThrows(
        PersistenceException.class,
        () -> {
          entityManager.persist(anotherManufacturer);
        });
  }

  @Test
  public void test_getCreatedAt() {
    entityManager.persistAndFlush(manufacturer);
    assertNotNull(manufacturer.getCreatedAt());
  }

  @Test
  public void test_toString() {
    entityManager.persistAndFlush(manufacturer);
    String manufacturerString = manufacturer.toString();
    assertTrue(manufacturerString.contains("id="));
    assertTrue(manufacturerString.contains("name=ACME Aeronautics"));
    assertTrue(manufacturerString.contains("location=Baltimore, Maryland"));
    assertTrue(manufacturerString.contains("createdAt="));
  }

  @Test
  public void test_getAircraftModels() {
    entityManager.persistAndFlush(manufacturer);

    Category experimental = new Category();
    experimental.setName("Experimental");
    entityManager.persistAndFlush(experimental);

    Manufacturer bell = new Manufacturer();
    bell.setName("Bell");
    entityManager.persistAndFlush(bell);

    AircraftModel x1 = new AircraftModel();
    AircraftModel x2 = new AircraftModel();
    AircraftModel x15 = new AircraftModel();
    x1.setName("X-1");
    x1.setManufacturer(bell);
    x1.setCategory(experimental);
    entityManager.persistAndFlush(x1);

    x2.setName("X-2");
    x2.setManufacturer(bell);
    x2.setCategory(experimental);
    entityManager.persistAndFlush(x2);

    x15.setName("X-15");
    x15.setManufacturer(manufacturer);
    x15.setCategory(experimental);
    entityManager.persistAndFlush(x15);

    entityManager.refresh(bell);
    Set<AircraftModel> result = bell.getAircraftModels();
    assertTrue(result.contains(x1));
    assertTrue(result.contains(x2));
    assertFalse(result.contains(x15));
  }
}
