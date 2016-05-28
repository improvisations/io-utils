package de.improvisations.utils.reader;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CSVReaderTest {
    private final CSVReader reader = new CSVReader();

    @Test
    public void testLoadFromNullFile() throws Exception {
        List<Object> csv = reader.load(null);
        assertEquals("Loading from null file => empty List", 0, csv.size());
    }

    @Test
    public void testLoadFromNotExistingFile() throws Exception {
        List<Object> csv = reader.load(new File("NOT EXISTING FILE"));
        assertEquals("Loading from not existing file => empty List", 0, csv.size());
    }

    @Test
    public void testLoadFromExistingFile() throws Exception {
        File file = new File(this.getClass().getResource("words.csv").toURI());
        List<Object> csv = reader.load(file);
        assertEquals("Loading from existing file => List of objects", 2, csv.size());
    }

    @Test
    public void testLoadList() throws Exception {
        File file = new File(this.getClass().getResource("words.csv").toURI());
        List<Map<String, String>> csv = reader.load(file);

        Map<String, String> word1 = new HashMap<>();
        word1.put("key", "şeker");
        word1.put("definition", "sugar");

        Map<String, String> word2 = new HashMap<>();
        word2.put("key", "2 | iki");
        word2.put("definition", "two");

        List<Map<String, String>> expected = new ArrayList<>();
        expected.add(word1);
        expected.add(word2);

        assertEquals("CSV should be correct", expected, csv);
    }
}