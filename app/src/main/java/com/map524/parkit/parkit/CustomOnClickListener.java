package com.map524.parkit.parkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 * Created by phil_ on 06/12/17.
 */

public class CustomOnClickListener implements View.OnClickListener {
    float slat;
    float slng;
    float dlat;
    float dlng;
    Context mcontext;

    public CustomOnClickListener(float slat, float slng, float dlat, float dlng, Context context){
        this.slat = slat;
        this.slng = slng;
        this.dlat = dlat;
        this.dlng = dlng;
        this.mcontext = context;
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" + "saddr=" + this.slat + "," + this.slng + "&daddr=" + this.dlat + "," +this.slng));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"); ((Activity) mcontext).startActivity(intent);
    }

}
