package mil.usaf.aircraftapi.aircraftmodel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class AircraftModelNotFoundException extends RuntimeException {

  AircraftModelNotFoundException(Long id) {
    super("Could not find aircraft model " + id);
  }
}
