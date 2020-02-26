package mil.usaf.logux.aircraftapi.category;

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
}
