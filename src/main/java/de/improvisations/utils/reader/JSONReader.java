package de.improvisations.utils.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Optional;

@Log4j2
public class JSONReader {
    public <T> Optional<T> load(final File file) {
        Optional<T> json = Optional.empty();

        try {
            String text = FileUtils.readFileToString(file, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            json = Optional.of(mapper.readValue(text, new TypeReference<T>() {}));
        } catch (Exception e) {
            log.warn("JSON cannot be loaded from {}", file);
        }

        return json;
    }
}