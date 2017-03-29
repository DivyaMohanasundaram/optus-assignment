package org.com.optus.util;

import org.com.optus.exception.TextSearchException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class TextSearchUtilityTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void convertStringToJsonShouldReturnValidResult() throws TextSearchException {
        String input = "{\"Key\":[\"1\",\"2\",\"3\",\"4\"]}";
        JSONObject jsonObject = TextSearchUtility.convertStringToJson(input);
        JSONArray words = (JSONArray) jsonObject.get("Key");
        assertNotNull(words);
        assertEquals(4, words.size());
    }

    @Test
    public void convertStringToJsonShouldThrowExceptionTextSearchException() throws TextSearchException {
        String input = "{Key:[\"1\",\"2\",\"3\",\"4\"]}";
        exception.expect(TextSearchException.class);
        JSONObject jsonObject = TextSearchUtility.convertStringToJson(input);

    }

    @Test
    public void sortByValueShouldReturnEmptyList()  {
        List<Map.Entry<String, Integer>> entryList = TextSearchUtility.sortByValue(null);
        assertEquals(0, entryList.size());
    }

    @Test
    public void sortByValueShouldReturnShortedListBasedonValue()  {
        Map<String,Integer> unsorted= new TreeMap<>();
        unsorted.put("A",3);
        unsorted.put("B",2);
        unsorted.put("C",1);
        unsorted.put("D",3);
        List<Map.Entry<String, Integer>> entryList = TextSearchUtility.sortByValue(unsorted);
        assertEquals(4, entryList.size());
        assertEquals(3,entryList.get(0).getValue().intValue());
        assertEquals(1,entryList.get(3).getValue().intValue());
    }

    @Test
    public void sortByValueShouldReturnEmptyListForEmptyMap()  {
        Map<String,Integer> unsorted= new TreeMap<>();
        List<Map.Entry<String, Integer>> entryList = TextSearchUtility.sortByValue(unsorted);
        assertEquals(0, entryList.size());
    }
}
