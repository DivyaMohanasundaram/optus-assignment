package org.com.optus.textloader;

import org.com.optus.exception.TextSearchException;
import org.com.optus.model.TextCountListResponse;


public interface TextLoader {

    public TextCountListResponse getCount(String queryList) throws TextSearchException;

    public String getTopList(int count) throws TextSearchException;

}
