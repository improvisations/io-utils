package de.improvisations.utils.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Log4j2
public class JSONWriter {

    public <T> void save(final T json, final File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(json);
            FileUtils.writeStringToFile(file, data, "UTF-8");
        } catch (Exception e) {
            log.warn("JSON cannot be saved to {}", file);
        }
    }
}
