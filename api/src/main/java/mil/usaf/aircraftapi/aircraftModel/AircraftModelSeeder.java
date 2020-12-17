package mil.usaf.aircraftapi.aircraftmodel;

import java.io.File;
import java.util.List;
import javax.annotation.PostConstruct;
import mil.usaf.aircraftapi.category.Category;
import mil.usaf.aircraftapi.category.CategoryRepository;
import mil.usaf.aircraftapi.manufacturer.Manufacturer;
import mil.usaf.aircraftapi.manufacturer.ManufacturerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AircraftModelSeeder {

  @Autowired private AircraftModelRepository aircraftModelRepository;

  @Autowired private CategoryRepository categoryRepository;

  @Autowired private ManufacturerRepository manufacturerRepository;

  private final Logger logger = LoggerFactory.getLogger(AircraftModelSeeder.class);

  private final String filename = "src/main/resources/data/usaf-aircraft-models.csv";

  @PostConstruct
  public void seed() throws Exception {
    File csvFile = new File(filename);
    List<AircraftModelCsvRow> rows = AircraftModelCsvReader.readFile(csvFile);

    Category category;
    Manufacturer manufacturer;
    AircraftModel aircraftModel;

    for (AircraftModelCsvRow row : rows) {
      aircraftModel = aircraftModelRepository.findByName(row.getName());

      if (aircraftModel == null) {
        aircraftModel = new AircraftModel();
      }

      category = categoryRepository.findByName(row.getCategory());
      manufacturer = manufacturerRepository.findByName(row.getManufacturer());

      aircraftModel.setName(row.getName());
      aircraftModel.setCategory(category);
      aircraftModel.setManufacturer(manufacturer);
      aircraftModel.setWikipediaUrl(row.getWikipediaUrl());
      aircraftModel.setImageUrl(row.getImageUrl());

      logger.info("Persisting " + aircraftModel.toString());
      aircraftModelRepository.saveAndFlush(aircraftModel);
    }
  }
}
