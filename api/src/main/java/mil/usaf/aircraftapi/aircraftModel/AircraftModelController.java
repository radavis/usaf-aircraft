package mil.usaf.aircraftapi.aircraftmodel;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aircraftModel")
public class AircraftModelController {

  @Autowired private AircraftModelRepository aircraftModelRepository;

  @GetMapping
  public List<AircraftModel> index() {
    return aircraftModelRepository.findAll();
  }

  @GetMapping("/{id}")
  public AircraftModel show(@PathVariable Long id) {
    return aircraftModelRepository
        .findById(id)
        .orElseThrow(() -> new AircraftModelNotFoundException(id));
  }
}
