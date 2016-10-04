package com.example.navanee.mytunes;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class TuneDetails extends AppCompatActivity {
    TextView tTitle;
    TextView tDate;
    ImageView tImage;
    TextView tSummary;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tune_details);
        setAllViews();
        Tune cTune = (Tune) getIntent().getExtras().get("TuneObj");
        tTitle.setText(cTune.getTitle());
        tSummary.setText(cTune.getSummary());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        tDate.setText(dateFormat.format(cTune.getReleaseDate()));
        //tDate.setText("Release Date");
        new SetImageInRow(tImage).execute(cTune.getImage());
    }

    public void setAllViews() {
        tTitle = (TextView) findViewById(R.id.tuneTitle);
        tDate = (TextView) findViewById(R.id.tuneRDate);
        tImage = (ImageView) findViewById(R.id.tuneImage);
        tSummary = (TextView) findViewById(R.id.tuneSummary);
    }
}
