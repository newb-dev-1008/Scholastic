package com.devspace.scholastic;

public class Deadlines {

    private String title, subheading, body;

    public Deadlines(String title, String subheading, String body) {
        this.title = title;
        this.subheading = subheading;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getSubheading() {
        return subheading;
    }

    public String getBody() {
        return body;
    }
}
