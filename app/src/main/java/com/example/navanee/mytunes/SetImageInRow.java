package com.example.navanee.mytunes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by navanee on 03-10-2016.
 */
public class SetImageInRow extends AsyncTask<String, Void,Bitmap> {
    Bitmap bitmap;
    private final WeakReference<ImageView> imageViewReference;

    public SetImageInRow(ImageView imgView) {
        imageViewReference = new WeakReference<ImageView>(imgView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url= null;
        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            bitmap = BitmapFactory.decodeStream(con.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}

