package com.rgdgr8.categories;

public class Category {
    private String subject;
    private String courses;

    public String getSubject() {
        return subject;
    }

    public String getCourses() {
        return courses;
    }

    public Category(String subject, String courses) {
        this.subject = subject;
        this.courses = courses;
    }
}
