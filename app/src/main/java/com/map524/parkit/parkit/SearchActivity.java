package com.map524.parkit.parkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JLoader nearby = new JLoader();
        ArrayList<ParkingDataModel> parkingLotsGP = nearby.JLoaderList();

        ListView seaLots = (ListView) findViewById(R.id.searchLV);

        NearbyListviewAdapter nblva = new NearbyListviewAdapter(parkingLotsGP, this);

        seaLots.setAdapter(nblva);






        setContentView(R.layout.activity_search);
    }
}
