package fp.sales;

import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository {
    Stream<Entry> stream;

    {
        try {
            stream = readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");


    public static Stream<Entry> readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

        return reader.lines().skip(1).map(line -> { String[] values = line.split("\t");
            return new Entry(values[2],
                    LocalDate.parse(values[0], formatter),
                    values[1],
                    values[3],
                    Double.parseDouble(values[5].replace(",", ".")));
        });
    }
}
