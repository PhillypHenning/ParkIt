package com.map524.parkit.parkit;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by pahenning-folz on 11/7/2017.
 */

public class NearbyLvFragment extends Fragment {
    String[][] PARKING_DATA_ARRAY = {
            {"831","TTC Commuter Lot - Islington Fieldway Lot - 22 Fieldway Rd","43.642473","-79.527540","false","surface","Surface","true","false","270","0.00","Coins","Charge (Visa / Mastercard / American Express Only)","","Pay and Display","","","Monday - Friday","Flat Rate in effect 5:00 AM to 2:00 AM","","If entering 5 AM to 3 PM","$3.00","","","","","yes","43.642292","-79.527448","330.84","9.53","0","Saturday - Sunday & Holidays","Flat Rate (5am - 2am)","$2.00","","","","","","","","","","","","","","","","","","","","NO PARKING FROM 2AM - 5AM EVERY NIGHT","","If entering 3 PM to 2 AM","$2.00","","","","","","","","","","","",""},
            {"532","14 Barkwin Dr.","43.738468","-79.564984","$1.00 / Half Hour","surface","Surface","false","1.00","23","0.00","Coins","Charge (Visa / Mastercard / American Express Only)","","Pay and Display","","","Monday - Sunday & Holidays","Maximum (Any 24 hr Period)","$5.00","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""  },
            {"259","4 Spadina Road","43.666878","-79.404116","$1.50 / Half Hour","surface","Surface","false","1.50","51","0.00","Coins","Charge (Visa / Mastercard / American Express Only)","","Pay and Display","","","Monday - Sunday & Holidays","Day Maximum (7am 6pm)","$10.00","Night Maximum (6pm - 7am)","$6.00","","","","","yes","43.667005","-79.403974","280.56","2.62","0","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""  },
            {"270","180 Spadina Avenue","43.649589","-79.396818","$2.25 / Half Hour","surface","Surface","false","2.25","35","","Coins","Charge (Visa / Mastercard / American Express Only)","","Pay and Display","","","Monday - Sunday & Holidays","Day Maximum (7am - 6pm)","$15.00","Night Maximum (6pm - 7am)","$7.00","","","","","yes","43.649537","-79.396779","272.50","-8.59","0","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""  },
            {"262","302 Queen Street West","43.649429","-79.393569","$2.25 / Half Hour","surface","Surface","false","2.25","99","0.00","Coins","Charge (Visa / Mastercard / American Express Only)","","Pay and Display","","","Monday - Wednesday ","Day Maximum (7am - 6pm)","$16.00","Night Maximum (6pm - 7am)","$8.00","","","","","yes","43.649280","-79.393576","330.10","-6.74","0","Thursday - Sunday","Day Maximum (7am - 6pm)","$20.00","Night Maximum (6pm - 7am)","$20.00","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""}
    };

    ArrayList<ParkingDataModel> dataModels;
    ListView listview;
    private static NearbyListviewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.nearby_lv_fragment, parent, false);

        listview = (ListView) rootView.findViewById(R.id.nearby_lv);
        dataModels = new ArrayList<>();
        dataModels.addAll(parse_data(PARKING_DATA_ARRAY));

        adapter = new NearbyListviewAdapter(dataModels, getActivity().getApplicationContext());


        listview.setAdapter(adapter);
        return rootView;
    }

    public ArrayList<ParkingDataModel> parse_data(String[][] s){
        ArrayList<ParkingDataModel> data = new ArrayList<ParkingDataModel>();
        for(String[] item : s){
            Integer id = Integer.parseInt(item[0]);
            Double lat = Double.parseDouble(item[2]);
            Double lng = Double.parseDouble(item[3]);
            //Double rate = Double.parseDouble(item[4]);
            Double max_height = Double.parseDouble(item[9]);

            data.add(new ParkingDataModel(id, lat, lng, item[4], max_height, item[1], item[6], item[7], item[10], item[11], item[15]));
        }

        return data;
    }
}
