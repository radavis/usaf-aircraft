package mil.usaf.logux.aircraftapi.manufacturer;

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
}
