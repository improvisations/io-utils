package de.improvisations.utils.reader;

import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TextReaderTest {
    private final TextReader reader = new TextReader();

    @Test
    public void testLoadFromNullFile() throws Exception {
        Optional<String> text = reader.load(null);
        assertEquals("Loading from null file => empty Optional", Optional.empty(), text);
    }

    @Test
    public void testLoadFromNotExistingFile() throws Exception {
        Optional<String> text = reader.load(new File("NOT EXISTING FILE"));
        assertEquals("Loading from not existing file => empty Optional", Optional.empty(), text);
    }

    @Test
    public void testLoadFromExistingFile() throws Exception {
        File file = new File(this.getClass().getResource("sample.txt").toURI());
        Optional<String> text = reader.load(file);
        assertEquals("Loading from existing file => text with given encoding", "SAMPLE TEXT", text.get());
    }
}