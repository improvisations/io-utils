package de.improvisations.utils.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CSVReader {

    public <T> List<T> load(final File file) {
        List<T> csv = new ArrayList<>();

        try {
            String text = FileUtils.readFileToString(file, "UTF-8");

            CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
            CsvSchema schema = mapper.schemaFor(new TypeReference<List<T>>() {}).withHeader();
            ObjectReader objectReader = mapper.readerFor(new TypeReference<List<T>>() {}).with(schema);

            csv = objectReader.readValue(text);
        } catch (Exception  e) {
            log.warn("CSV cannot be loaded from {}", file);
        }

        return csv;
    }
}
