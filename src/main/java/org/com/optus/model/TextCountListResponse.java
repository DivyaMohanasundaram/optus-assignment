package org.com.optus.model;


import java.util.ArrayList;
import java.util.List;

public class TextCountListResponse {

    private List<String> counts;

    public TextCountListResponse() {
        counts = new ArrayList<>();
    }

    public List<String> getCounts() {
        return counts;
    }

    public void setCounts(List<String> counts) {
        this.counts.addAll(counts);
    }
}
