package com.map524.parkit.parkit;

/**
 * Created by dfoster8 on 11/14/2017.
 */

public class JLoader {

    /*
    * package com.map524.parkit.parkit;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import com.map524.parkit.parkit.ParkingDataModel;

public class JLoader {

    public String greenPJSON() throws IOException{
        String line = null;

        StringBuilder sb;
        InputStream file = getAssets().open("greenP.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        sb = new StringBuilder();

        try{
            while((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }

    }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            try{
                file.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
Log.i("json string", sb.toString());
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView seaLots = (ListView)findViewById(R.id.listLots);

        ArrayList<ParkingDataModel> parkList = new ArrayList<ParkingDataModel>();
        ParkingDataModel pdm;

        try{
            JSONObject ob = new JSONObject(greenPJSON());
            JSONArray m_carparks = ob.getJSONArray("carparks");


            for(int i=0;i<m_carparks.length(); i++){
                JSONObject jo_inLot = m_carparks.getJSONObject(i);
                Log.i("Details for: ", jo_inLot.getString("address"));
               // car park details from the json object
                Integer id = jo_inLot.getInt("id");
                Double lat = jo_inLot.getDouble("lat");
                Double lng = jo_inLot.getDouble("lng");

                String max_height = jo_inLot.getString("max_height");
                String rate = jo_inLot.getString("rate");

                String address = jo_inLot.getString("address");
                String carpark_type = jo_inLot.getString("carpark_type");

                String payment_methods = jo_inLot.getString("payment_methods");
                String payment_options = jo_inLot.getString("payment_options");




                pdm = new ParkingDataModel(id,lat,lng,rate,max_height, address, carpark_type, payment_methods, payment_options);
                Log.i("ParkingDataModel-->: ", pdm.toString());
                parkList.add(pdm);
                Log.i("ParkingLotsList-->",parkList.toString());

                try{
                    String content = parkList.toArray().toString();
                    File file = new File("C:/~Desktop/dataModel.csv");
                    if(!file.exists()){
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();

                }catch(Exception e){e.printStackTrace();}


            }

        }catch(Exception e){
            e.printStackTrace();
        }

}
    }
    * */
}
