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

public class transportListBase2Adapter extends BaseAdapter {
    private static ArrayList<TransportDetails> transportDetailsrrayList;



    private LayoutInflater l_Inflater;

    public transportListBase2Adapter(Context context, ArrayList<TransportDetails> results) {
        transportDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return transportDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return transportDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.listbustransport, null);
            holder = new ViewHolder();

            holder.txt_busNo = (TextView) convertView.findViewById(R.id.busNo);
            holder.txt_route = (TextView) convertView.findViewById(R.id.route);
            holder.txt_time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_busNo.setText(transportDetailsrrayList.get(position).getBusNo());
        holder.txt_route.setText(transportDetailsrrayList.get(position).getDirection());
        holder.txt_time.setText(transportDetailsrrayList.get(position).getTime());
        //holder.txt_time.setText(itemDetailsrrayList.get(position).getTime());
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

        return convertView;
    }

    static class ViewHolder {
        TextView txt_busNo;
        TextView txt_route;
        TextView txt_time;

    }
}
