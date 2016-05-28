package de.improvisations.utils.reader;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Optional;

@Log4j2
public class TextReader {

    public Optional<String> load(final File file) {
        Optional<String> text = Optional.empty();

        try {
            text = Optional.of(FileUtils.readFileToString(file, "UTF-8"));
        } catch (Exception e) {
            log.warn("Text cannot be loaded from {}", file, e);
        }

        return text;
    }
}
