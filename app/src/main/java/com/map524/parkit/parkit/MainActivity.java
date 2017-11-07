package com.map524.parkit.parkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // &^& Button click handler functions
    public void handle_map_click(){
        // &^& Create an intent for the click
        Intent switch_to_map_view = new Intent(MainActivity.this, NearbyActivity.class);

        startActivity(switch_to_map_view);
    }

    public void handle_search_click(){
        Intent switch_to_search_view = new Intent(MainActivity.this, SearchActivity.class);

        startActivity(switch_to_search_view);
    }

    public void handle_b3_click(){
        Toast.makeText(this, "Under construction! Try again later", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // &^& Add button handlers by defining a reference to the R object
        ImageButton map = (ImageButton) findViewById(R.id.map_button);
        ImageButton search = (ImageButton) findViewById(R.id.search_button);
        ImageButton b3 = (ImageButton) findViewById(R.id.b3_button);

        // &^& Defining an onclick method for buttons
        map.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    handle_map_click();
                }
            }
        );

        search.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    handle_search_click();
                }
            }
        );

        b3.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    handle_b3_click();
                }
            }
        );
    }
}



