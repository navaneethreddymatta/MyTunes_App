package com.example.navanee.mytunes;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Created by navanee on 03-10-2016.
 */
public class TunesParser {
    static ArrayList<Tune> ParseTunes (InputStream in) throws XmlPullParserException, IOException, ParseException {
        ArrayList<Tune> tunesList;
        Tune myTune = null;
        XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(in,"UTF-8");
        tunesList = new ArrayList<Tune>();
        int event = parser.getEventType();
        while(event != XmlPullParser.END_DOCUMENT) {
            switch(event) {
                case XmlPullParser.START_TAG:
                    if(parser.getName().equals("entry")) {
                        Log.d("demo","creating an object");
                        myTune = new Tune();
                    } else if(parser.getName().equals("title")) {
                        if(myTune != null) {
                            myTune.setTitle(parser.nextText().trim());
                        }
                    } else if(parser.getName().equals("summary")) {
                        if(myTune != null) {
                            myTune.setSummary(parser.nextText().trim());
                        }
                    } else if(parser.getName().equals("im:releaseDate")) {
                        if(myTune != null) {
                            String rDate = parser.nextText().trim();
                            Date date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(rDate);
                            myTune.setReleaseDate(date1);
                        }
                    } else if(parser.getName().equals("updated")) {
                        if(myTune != null) {
                            String uDate = parser.nextText().trim();
                            Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(uDate);
                            myTune.setLastUpdated(date2);
                        }
                    } else if(parser.getName().equals("im:image")) {
                        if(myTune != null) {
                            Log.d("demo","im:image");
                            Log.d("demo",parser.getAttributeValue(null,"height"));
                            if(Integer.parseInt(parser.getAttributeValue(null, "height")) == 60) {
                                Log.d("demo","in side");
                                myTune.setThumbnail(parser.nextText().trim());
                            } else if(Integer.parseInt(parser.getAttributeValue(null, "height")) == 170) {
                                myTune.setImage(parser.nextText().trim());
                            }
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("entry")) {
                        Log.d("demo","adding an object");
                        Log.d("demo", String.valueOf(myTune.getThumbnail()));
                        myTune.setMatched(false);
                        tunesList.add(myTune);
                    }
                    break;
                default:
                    break;
            }
            event = parser.next();
        }
        Collections.sort(tunesList, new Comparator<Tune>() {
            @Override
            public int compare(Tune t1, Tune t2) {
                if(t2.getReleaseDate().before(t1.getReleaseDate())) return -1;
                else if(t2.getReleaseDate().after(t1.getReleaseDate())) return 1;
                else return 0;
            }
        });
        return tunesList;
    }
}
