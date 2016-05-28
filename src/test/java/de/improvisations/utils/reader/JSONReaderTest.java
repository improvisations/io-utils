package de.improvisations.utils.reader;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Log4j2
public class JSONReaderTest {
    private final JSONReader reader = new JSONReader();

    @Test
    public void testLoadFromNullFile() throws Exception {
        Optional<Object> json = reader.load(null);
        assertEquals("Loading from null file => empty Optional", Optional.empty(), json);
    }

    @Test
    public void testLoadFromNotExistingFile() throws Exception {
        Optional<Object> json = reader.load(new File("NOT EXISTING FILE"));
        assertEquals("Loading from not existing file => empty Optional", Optional.empty(), json);
    }

    @Test
    public void testLoadFromExistingFile() throws Exception {
        File file = new File(this.getClass().getResource("sample.json").toURI());
        Optional<Object> json = reader.load(file);
        assertNotEquals("Loading from existing file => JSON object", Optional.empty(), json.get());
    }

    @Test
    public void testLoadMap() throws Exception {
        File file = new File(this.getClass().getResource("sample.json").toURI());
        Optional<Map<String, Object>> dictionary = reader.load(file);

        assertEquals("Title should be correct", "Test Dictionary Türkçe -> English", dictionary.get().get("title"));
        assertEquals("Author should be correct", "Ulaş Yılmaz", dictionary.get().get("author"));
        assertEquals("Last update should be correct", "Sat May 28 20:56:02 EDT 2016", dictionary.get().get("last update"));
        assertEquals("Source language should be correct", "Türkçe", dictionary.get().get("source language"));
        assertEquals("Source encoding should be correct", "UTF-8", dictionary.get().get("source encoding"));
        assertEquals("Target language should be correct", "English", dictionary.get().get("target language"));
        assertEquals("Target encoding should be correct", "UTF-8", dictionary.get().get("target encoding"));
        assertEquals("Words contain 2 words", 2, CollectionUtils.size(dictionary.get().get("words")));
    }

    @Test
    public void testLoadList() throws Exception {
        File file = new File(this.getClass().getResource("words.json").toURI());
        Optional<List<Map<String, String>>> words = reader.load(file);

        Map<String, String> word1 = new HashMap<>();
        word1.put("key", "şeker");
        word1.put("definition", "sugar");

        Map<String, String> word2 = new HashMap<>();
        word2.put("key", "2 | iki");
        word2.put("definition", "two");

        List<Map<String, String>> expected = new ArrayList<>();
        expected.add(word1);
        expected.add(word2);

        assertEquals("Words should be correct", expected, words.get());
    }
}