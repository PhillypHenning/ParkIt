package com.map524.parkit.parkit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.map524.parkit.parkit.R;

import java.util.ArrayList;

/**
 * Created by pahenning-folz on 11/7/2017.
 */

public class NearbyListviewAdapter extends BaseAdapter{

    private ArrayList<ParkingDataModel> dataset;
    private Context mcontext;

    private class viewHolder{
        TextView id;
        TextView lat;
        TextView lng;
        TextView rate;
        TextView max_height;
        TextView address;
        TextView carpark_desc;
        TextView carpark_type;
        TextView payment_methods;
        TextView payment_options;
        TextView rate2;

        public void convert_holder( View convertView){
            this.rate = (TextView) convertView.findViewById(R.id.nearby_list_item_rate);
            this.address = (TextView) convertView.findViewById(R.id.nearby_list_item_addr);
            this.payment_methods = (TextView) convertView.findViewById(R.id.nearby_list_item_payment);;
        }
    }


    public NearbyListviewAdapter(ArrayList<ParkingDataModel> data, Context context){
        this.dataset = data;
        this.mcontext = context;
    }

    public ParkingDataModel getItem(int position){
        return dataset.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public int getCount(){
        return dataset.size();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        viewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) mcontext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            //For the first time it will execute
            convertView = mInflater.inflate(R.layout.nearby_lv_fragment, null);
            holder = new viewHolder();
            holder.rate = (TextView) convertView.findViewById(R.id.nearby_list_item_rate);
            holder.payment_methods = (TextView) convertView.findViewById(R.id.nearby_list_item_payment);
            convertView.setTag(holder);
        } else {
            //second time onwards this case will execute
            holder = (viewHolder) convertView.getTag();
        }

        holder.rate.setText(dataset.get(position).getRate());
        holder.payment_methods.setText(dataset.get(position).getPayment_methods());

        return convertView;


        /*
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_lv_fragment,null);
        }

        viewHolder holder = null;
        if(convertView == null) {
            holder = new viewHolder();
            holder.convert_holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }

        ((TextView) convertView.findViewById(R.id.nearby_list_item_rate)).setText(dataset.get(position).getAddress());
        // holder.address.setText(test);
        holder.rate.setText(dataset.get(position).getRate());
        holder.payment_methods.setText(dataset.get(position).getPayment_methods());

        return convertView;
        */
        }
    }
