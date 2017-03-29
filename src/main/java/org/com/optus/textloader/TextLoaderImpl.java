package org.com.optus.textloader;

import org.com.optus.exception.TextSearchException;
import org.com.optus.model.TextCountListResponse;
import org.com.optus.util.TextSearchUtility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;


@Service
public class TextLoaderImpl implements TextLoader {
    private static Resource resource = new ClassPathResource("input.txt");
    public static List<Map.Entry<String, Integer>> orderedList = null;
    public static Map<String, Integer> wordMap = new TreeMap();
    private static final Logger LOGGER = Logger.getLogger(TextLoaderImpl.class.getName());

    static {
        try {
            LoadParagraph();
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.WARNING, "Problem in reading input data" + e);

        }
    }

    private static void LoadParagraph() throws IOException, Exception {
        try {
            InputStream inputFile = resource.getInputStream();

            Scanner scanner = new Scanner(inputFile);
            StringBuilder inputAsStringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                inputAsStringBuilder.append(scanner.nextLine());
            }

            String inputString = inputAsStringBuilder.toString().replaceAll("[-+.^:,]", "");

            String[] inputAsArray = inputString.trim().split("\\s+");
            for (String word : inputAsArray) {

                if (wordMap.containsKey(word)) {
                    Integer count = wordMap.get(word);
                    wordMap.put(word, count + 1);

                } else {
                    wordMap.put(word, 1);
                }

            }

            orderedList = TextSearchUtility.sortByValue(wordMap);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public TextCountListResponse getCount(String queryList) throws TextSearchException {

        TextCountListResponse textCountListResponse = new TextCountListResponse();
        try {
            JSONObject jsonObject = TextSearchUtility.convertStringToJson(queryList);
            JSONArray words = (JSONArray) jsonObject.get("searchText");

            for (Object word : words) {
                Integer count = wordMap.get(word);
                if (count == null) {
                    count = 0;
                }
                textCountListResponse.getCounts().add(word.toString() + ":" + Integer.toString(count));
            }
        } catch (Exception e) {
            throw new TextSearchException("Problem in GetCount " + e);
        }
        return textCountListResponse;
    }

    @Override
    public String getTopList(int count) throws TextSearchException {
        StringBuilder sb = new StringBuilder();
        try {
            List<Map.Entry<String, Integer>> entryList = orderedList.subList(0, count);
            for (Map.Entry<String, Integer> entity : entryList) {
                sb.append(entity.getKey() + "|" + entity.getValue());
                sb.append(System.getProperty("line.separator"));
            }
        }
        catch (Exception e) {
            throw new TextSearchException("Problem in getTopList " + e);
        }
        return sb.toString();
    }
}
