package com.map524.parkit.parkit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pahenning-folz on 11/14/2017.
 */

public class NearbyLotAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ParkingDataModel> data;

    private static LayoutInflater mInflater;

    public NearbyLotAdapter(Context context, ArrayList<ParkingDataModel> mData){
        this.context = context;
        this.data = mData;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public ParkingDataModel getItem(int position){
        return data.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View vi = convertView;
        if(vi ==null)
            vi = mInflater.inflate(R.layout.nearby_list_item, null);
        TextView text = (TextView) vi.findViewById(R.id.nearby_list_item_addr);
        text.setText(data.get(position).getAddress());
        return vi;
        }

    //------------------------- Static views for each row--------------------//
    static class ViewHolder{
            TextView address;
            int pos; //stores pos of item within list

    }
}


