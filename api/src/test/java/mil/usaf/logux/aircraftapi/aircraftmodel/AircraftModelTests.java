package mil.usaf.logux.aircraftapi.aircraftmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mil.usaf.logux.aircraftapi.category.Category;
import mil.usaf.logux.aircraftapi.manufacturer.Manufacturer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AircraftModelTests {

  @Autowired private TestEntityManager entityManager;

  private AircraftModel aircraftModel;

  private Category category;

  private Manufacturer manufacturer;

  @BeforeEach
  public void setup() {
    category = new Category();
    category.setName("Prototype");
    entityManager.persistAndFlush(category);

    manufacturer = new Manufacturer();
    manufacturer.setName("ACME");
    entityManager.persistAndFlush(manufacturer);

    aircraftModel = new AircraftModel();
    aircraftModel.setName("X-1");
    aircraftModel.setCategory(category);
    aircraftModel.setManufacturer(manufacturer);
  }

  @Test
  public void test_getId() {
    entityManager.persistAndFlush(aircraftModel);
    assertNotNull(aircraftModel.getId());
  }

  @Test
  public void test_getName() {
    assertEquals(aircraftModel.getName(), "X-1");
  }

  @Test
  public void test_nameCannotBeBlank() {
    aircraftModel.setName("");
    assertThrows(
        ConstraintViolationException.class,
        () -> {
          entityManager.persist(aircraftModel);
        });
  }

  @Test
  public void test_nameMustBeUnique() {
    entityManager.persist(aircraftModel);
    AircraftModel anotherAircraftModel = new AircraftModel();
    anotherAircraftModel.setName("X-1");
    anotherAircraftModel.setCategory(category);
    anotherAircraftModel.setManufacturer(manufacturer);

    assertThrows(
        PersistenceException.class,
        () -> {
          entityManager.persist(anotherAircraftModel);
        });
  }

  @Test
  public void test_getCategory() {
    entityManager.persistAndFlush(aircraftModel);
    assertEquals(aircraftModel.getCategory().getId(), category.getId());
  }

  @Test
  public void test_getManufacturer() {
    entityManager.persistAndFlush(aircraftModel);
    assertEquals(aircraftModel.getManufacturer().getId(), manufacturer.getId());
  }

  @Test
  public void test_getCreatedAt() {
    entityManager.persistAndFlush(aircraftModel);
    assertNotNull(aircraftModel.getCreatedAt());
  }

  @Test
  public void test_getUpdatedAt() {
    entityManager.persistAndFlush(aircraftModel);
    assertNotNull(aircraftModel.getCreatedAt());
  }

  @Test
  public void test_toString() {
    entityManager.persistAndFlush(aircraftModel);
    String aircraftModelString = aircraftModel.toString();
    assertTrue(aircraftModelString.contains("id="));
    assertTrue(aircraftModelString.contains("name=X-1"));
    assertTrue(aircraftModelString.contains("createdAt="));
    assertTrue(aircraftModelString.contains("updatedAt="));
  }
}
