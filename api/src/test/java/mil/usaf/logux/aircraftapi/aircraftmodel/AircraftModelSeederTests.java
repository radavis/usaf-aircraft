package mil.usaf.logux.aircraftapi.aircraftmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AircraftModelSeederTests {

  @Autowired private AircraftModelRepository aircraftModelRepository;

  @Autowired private AircraftModelSeeder aircraftModelSeeder;

  @Test
  public void test_seed() throws Exception {
    aircraftModelSeeder.seed();
    long initialCount = aircraftModelRepository.count();
    aircraftModelSeeder.seed();
    long finalCount = aircraftModelRepository.count();
    assertTrue(finalCount > 0);
    assertEquals(initialCount, finalCount);
  }
}
