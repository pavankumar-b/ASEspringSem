package com.example.pavan.goeasy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by pavan on 4/5/2015.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
        if(parent.getItemAtPosition(position)==null) {
        }
        else{
            setLocation(parent.getItemAtPosition(position).toString());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
