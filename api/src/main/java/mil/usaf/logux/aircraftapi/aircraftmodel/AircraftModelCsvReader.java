package mil.usaf.logux.aircraftapi.aircraftmodel;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.File;
import java.util.List;

public class AircraftModelCsvReader {

  public static List<AircraftModelCsvRow> readFile(File csvFile) throws Exception {
    MappingIterator<AircraftModelCsvRow> aircraftModelIterator =
        new CsvMapper().readerWithTypedSchemaFor(AircraftModelCsvRow.class).readValues(csvFile);

    return aircraftModelIterator.readAll();
  }
}
