package org.com.optus.util;

import org.com.optus.exception.TextSearchException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class TextSearchUtility {

    public static final JSONParser parser = new JSONParser();

    public static List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {
        if (wordMap != null) {
            Set<Entry<String, Integer>> set = wordMap.entrySet();
            List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    public static JSONObject convertStringToJson(String jsonString) throws TextSearchException {
        try {
            JSONObject json = (JSONObject) parser.parse(jsonString);
            return json;

        } catch (Exception e) {
            throw new TextSearchException("Problem in Parsing Json" + jsonString);
        }
    }
}
