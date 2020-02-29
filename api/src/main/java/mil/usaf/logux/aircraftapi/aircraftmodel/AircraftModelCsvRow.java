package mil.usaf.logux.aircraftapi.aircraftmodel;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AircraftModelCsvRow {

  @CsvBindByName public String name;

  @CsvBindByName public String origin;

  @CsvBindByName public String type;

  @CsvBindByName public String variant;

  @CsvBindByName public Integer inService;

  @CsvBindByName public String notes;

  @CsvBindByName public String category;

  @CsvBindByName public String wikipediaUrl;

  @CsvBindByName public String title;

  @CsvBindByName public String manufacturer;

  @CsvBindByName public String imageUrl;
}
