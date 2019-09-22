package com.hawahuri.expensemanager.utils;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Helper {

    public static void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public static void setIcon(String iconName, ImageView imageView) {
        try {
            String imageURI = RetrofitClient.IMAGE_URL + iconName;
            URL url = new URL(imageURI);
            imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
