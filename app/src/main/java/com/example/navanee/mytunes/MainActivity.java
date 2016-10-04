package com.example.navanee.mytunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetData.DataRetreiver,View.OnClickListener {
    TextView searchText;
    Button goBtn;
    Button clearBtn;
    ListView tunesListView;
    ArrayList<Tune> tunesList;
    ArrayList<Tune> matchedList = new ArrayList<Tune>();
    ArrayList<Tune> unMatchedList = new ArrayList<Tune>();
    ArrayList<Tune> tempList = new ArrayList<Tune>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAllViews();
        Log.d("demo","About to call data");

        new GetData(this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml");
    }
    public void setAllViews() {
        searchText = (TextView) findViewById(R.id.searchKey);
        goBtn = (Button) findViewById(R.id.goButton);
        clearBtn = (Button) findViewById(R.id.clearButton);
        tunesListView = (ListView) findViewById(R.id.tunesList);
        goBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
    }

    @Override
    public void setData(ArrayList<Tune> tunes) {
        tunesList = tunes;
        showUI(tunesList);
    }

    public void showUI(ArrayList<Tune> tunes) {
        tempList = tunes;
        TunesAdapter adapter = new TunesAdapter(this,R.layout.row_layout,tempList);
        tunesListView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        tunesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,TuneDetails.class);
                intent.putExtra("TuneObj", tempList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.goButton){
            matchedList.clear();
            unMatchedList.clear();
            String searchVal = searchText.getText().toString();
            for(Tune i:tunesList) {
                if((i.getTitle()).contains(searchVal)) {
                    i.setMatched(true);
                    matchedList.add(i);
                }
                else {
                    i.setMatched(false);
                    unMatchedList.add(i);
                }
            }
            for(Tune j:unMatchedList) {
                matchedList.add(j);
            }
            showUI(matchedList);

        } else if (view.getId() == R.id.clearButton) {
            searchText.setText("");
            for(Tune i:tunesList) {
                i.setMatched(false);
            }
            showUI(tunesList);
        }
    }
}
