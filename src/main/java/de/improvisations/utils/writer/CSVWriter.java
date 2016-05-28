package de.improvisations.utils.writer;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

@Log4j2
public class CSVWriter {
    public <T> void save(final List<T> csv, final File file) {
        try {
            Class clazz = csv.get(0).getClass();

            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(clazz);
            schema = schema.withHeader();

            ObjectWriter writer = mapper.writer(schema);
            String data = writer.writeValueAsString(csv);

            FileUtils.writeStringToFile(file, data, "UTF-8");
        } catch (Exception e) {
            log.warn("CSV cannot be saved to {}", file);
        }
    }
}
