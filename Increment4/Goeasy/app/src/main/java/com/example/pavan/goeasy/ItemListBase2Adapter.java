package com.example.pavan.goeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavan.goeasy.R;
import com.example.pavan.goeasy.SportsDetails;

import java.util.ArrayList;

public class ItemListBase2Adapter extends BaseAdapter {
    private static ArrayList<SportsDetails> itemDetailsrrayList;



    private LayoutInflater l_Inflater;

    public ItemListBase2Adapter(Context context, ArrayList<SportsDetails> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.listtrans, null);
            holder = new ViewHolder();
            holder.txt_date = (TextView) convertView.findViewById(R.id.Date);
            holder.txt_eventName = (TextView) convertView.findViewById(R.id.evenName);
            holder.txt_location = (TextView) convertView.findViewById(R.id.location);
            holder.txt_time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_date.setText(itemDetailsrrayList.get(position).getDate());
        holder.txt_eventName.setText(itemDetailsrrayList.get(position).getEvent());
        holder.txt_location.setText(itemDetailsrrayList.get(position).getLocation());
        holder.txt_time.setText(itemDetailsrrayList.get(position).getTime());
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

        return convertView;
    }

    static class ViewHolder {
        TextView txt_date;
        TextView txt_eventName;
        TextView txt_location;
        TextView txt_time;

    }
}
