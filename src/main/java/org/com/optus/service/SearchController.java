package org.com.optus.service;

import org.com.optus.exception.TextSearchException;
import org.com.optus.model.TextCountListResponse;
import org.com.optus.textloader.TextLoader;
import org.com.optus.textloader.TextLoaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/counter-api")
public class SearchController {
    private static final Logger LOGGER = Logger.getLogger(TextLoaderImpl.class.getName());
    @Autowired
    private TextLoader textLoader;

    @RequestMapping(value = "/search", method = RequestMethod.POST, headers = "Accept=application/json")
    public TextCountListResponse getTextCount(@RequestBody String queryList) throws TextSearchException {
        LOGGER.log(Level.INFO, queryList);
        return textLoader.getCount(queryList);
    }

    @RequestMapping(value = "/top/{count}", method = RequestMethod.GET, headers = "Accept=application/csv")
    public String getTopList(@PathVariable int count) throws TextSearchException {
        return textLoader.getTopList(count);
    }
}
