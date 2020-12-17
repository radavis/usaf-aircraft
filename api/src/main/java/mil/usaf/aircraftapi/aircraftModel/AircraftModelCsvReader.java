package mil.usaf.aircraftapi.aircraftmodel;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class AircraftModelCsvReader {

  private static final char CSV_COLUMN_SEPARATOR = ';';

  public static List<AircraftModelCsvRow> readFile(File csvFile) throws FileNotFoundException {
    return new CsvToBeanBuilder<AircraftModelCsvRow>(new FileReader(csvFile))
        .withSeparator(CSV_COLUMN_SEPARATOR)
        .withIgnoreQuotations(true)
        .withType(AircraftModelCsvRow.class)
        .build()
        .parse();
  }
}
