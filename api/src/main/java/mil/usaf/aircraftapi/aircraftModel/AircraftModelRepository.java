package mil.usaf.aircraftapi.aircraftmodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftModelRepository extends JpaRepository<AircraftModel, Long> {
  AircraftModel findByName(String name);
}
