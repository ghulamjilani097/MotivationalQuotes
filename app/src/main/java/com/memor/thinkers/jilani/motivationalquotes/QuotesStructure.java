package com.memor.thinkers.jilani.motivationalquotes;

public class QuotesStructure
{
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
