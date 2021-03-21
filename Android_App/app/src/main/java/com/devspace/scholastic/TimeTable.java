package com.devspace.scholastic;

public class TimeTable {

    private String time, subject, classLink;

    public TimeTable(String time, String subject, String classLink) {
        this.time = time;
        this.subject = subject;
        this.classLink = classLink;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public String getClassLink() {
        return classLink;
    }
}
