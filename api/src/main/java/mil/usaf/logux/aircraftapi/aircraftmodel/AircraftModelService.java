package mil.usaf.logux.aircraftapi.aircraftmodel;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class AircraftModelService {

    public void importCsv() throws Exception {
        String filename = "src/main/resources/data/usaf-aircraft-models.csv";
        File csvFile = new File(filename);
        List<AircraftModelCsvRow> rows = AircraftModelCsvReader.readFile(csvFile);

        rows.iterator().forEachRemaining((row) -> 
            System.out.println(row.getName())
        );
    }
}
