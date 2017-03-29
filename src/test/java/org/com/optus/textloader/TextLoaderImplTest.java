package org.com.optus.textloader;


import org.com.optus.exception.TextSearchException;
import org.com.optus.model.TextCountListResponse;
import org.com.optus.util.TextSearchUtility;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TextLoaderImplTest {

    @Mock
    private TextSearchUtility textSearchUtility;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    private TextLoaderImpl textLoaderImpl = null;

    @Before
    public void initialize() {
        textLoaderImpl = new TextLoaderImpl();
    }

    @Test
    public void LoadParagraphShouldLoadInputAndInitialiseData() {
        assertEquals(187, textLoaderImpl.wordMap.size());
        assertEquals(187, textLoaderImpl.orderedList.size());
    }

    @Test
    public void getCountShouldReturnOccurrences() throws TextSearchException {
        String inputQuery = "{\"searchText\":[\"Duis\",\"Donec\",\"Augue\",\"4\"]}";
        TextCountListResponse actualResponse = textLoaderImpl.getCount(inputQuery);
        List<String> counts = actualResponse.getCounts();
        assertEquals("Duis:11", counts.get(0));
        assertEquals("Donec:8", counts.get(1));
        assertEquals("Augue:0", counts.get(2));
        assertEquals("4:0", counts.get(3));
    }

    @Test
    public void getCountShouldReturnEmptyList() throws TextSearchException {
        String inputQuery = "{\"searchText\":[]}";
        TextCountListResponse actualResponse = textLoaderImpl.getCount(inputQuery);
        List<String> counts = actualResponse.getCounts();
        assertEquals(0, counts.size());
    }

    @Test
    public void getTopListShouldReturnTopElements() throws TextSearchException {
        String topList = textLoaderImpl.getTopList(1);
        assertEquals("eget|17\n", topList);
    }

    @Test
    public void getTopListShouldReturnEmpty() throws TextSearchException {
        String topList = textLoaderImpl.getTopList(0);
        assertEquals("", topList);
    }

    @Test
    public void getTopListShouldThrowException() throws TextSearchException {
        exception.expect(TextSearchException.class);
        String topList = textLoaderImpl.getTopList(1000);
    }
}
