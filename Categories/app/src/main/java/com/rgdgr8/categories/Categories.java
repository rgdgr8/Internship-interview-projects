package com.rgdgr8.categories;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Categories {
    private static Categories sInstance;
    private final List<Category> categoriesList;
    private final AssetManager assetManager;
    private static final String[] categories = {"Science", "Maths", "Commerce", "Arts", "Design"
            , "Architecture", "Category7", "Category8", "Category9", "Category10"};
    private static final String ASSET_ROOT = "course_covers";

    private Categories(Context context) {
        this.categoriesList = new ArrayList<>();
        assetManager = context.getAssets();
        Random random = new Random();
        random.setSeed(1L);

        for (String category : categories) {
            categoriesList.add(new Category(category, random.nextInt(50)));
        }
        try {
            String[] images = assetManager.list(ASSET_ROOT);
            for (int i = 0; i < images.length; i++) {
                InputStream inputStream = assetManager.open(ASSET_ROOT + "/" + images[i]);
                Drawable drawable = Drawable.createFromStream(inputStream,null);

                categoriesList.get(i).setCover(drawable);
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Categories getInstance(Context context) {
        if (sInstance == null)
            sInstance = new Categories(context);
        return sInstance;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

}
