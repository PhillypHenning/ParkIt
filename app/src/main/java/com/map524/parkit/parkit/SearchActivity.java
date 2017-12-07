package com.map524.parkit.parkit;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import com.map524.parkit.parkit.ParkingDataModel;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Search by name - will bring up the closest spot to that location
        // -> Text entry

        // Flag cost - sort in descending cost (Free++)

        // Flag dist = sort in descending distance


    }
}
