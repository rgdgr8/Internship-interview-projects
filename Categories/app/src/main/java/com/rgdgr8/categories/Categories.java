package com.rgdgr8.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Categories {
    private static Categories sInstance;
    private final List<Category> categoriesList;
    private static final String[] categories = {"Science", "Maths", "Commerce", "Arts", "Design"
            , "Architecture", "Category7", "Category8", "Category9", "Category10"};

    private Categories() {
        this.categoriesList = new ArrayList<>();
        Random random = new Random();
        random.setSeed(1L);

        for (String category : categories) {
            categoriesList.add(new Category(category, String.valueOf(random.nextInt(50))));
        }
    }

    public static Categories getInstance() {
        if (sInstance == null)
            sInstance = new Categories();
        return sInstance;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }
}
