package com.rgdgr8.categories;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Category {
    private final String subject;
    private final int courses;
    private Drawable cover = null;

    public String getSubject() {
        return subject;
    }

    public int getCourses() {
        return courses;
    }

    public Drawable getCover() {
        return cover;
    }

    public void setCover(Drawable drawable) {
        cover = drawable;
    }

    public Category(String subject, int courses) {
        this.subject = subject;
        this.courses = courses;
    }
}
