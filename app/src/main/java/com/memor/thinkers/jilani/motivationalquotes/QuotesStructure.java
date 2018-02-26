package com.memor.thinkers.jilani.motivationalquotes;

import java.lang.ref.SoftReference;

/**
 * Created by Jilani on 13-01-2018.
 */

public class QuotesStructure {

    private String quotes;
    private String author;

    public QuotesStructure(String quotes, String author) {
        this.quotes = quotes;
        this.author = author;
    }

    public String getQuotes() {
        return quotes;
    }

    public String getAuthor() {
        return author;
    }
}
