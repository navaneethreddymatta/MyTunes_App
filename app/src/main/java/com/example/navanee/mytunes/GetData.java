package com.example.navanee.mytunes;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by navanee on 03-10-2016.
 */
public class GetData extends AsyncTask<String,Void, ArrayList<Tune>> {
    DataRetreiver activity;

    public GetData(DataRetreiver activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Tune> doInBackground(String... params) {
        try{
            URL url = new URL(params[0]);
            Log.d("demo", params[0]);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode = con.getResponseCode();
            if(statuscode == HttpURLConnection.HTTP_OK)
            {
                InputStream inputStream= con.getInputStream();
                return TunesParser.ParseTunes(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Tune> tunes) {
        super.onPostExecute(tunes);
        Log.d("demo", String.valueOf(tunes.size()));
        activity.setData(tunes);
    }

    interface DataRetreiver{
        void setData(ArrayList<Tune> newsList);
    }
}
