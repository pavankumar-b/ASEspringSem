package com.example.pavan.goeasy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;

public class locations extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
private Spinner spinner1,spinner2;
    private String loc="";
    private final String TAG = "MapLocation";

    private CustomOnItemSelectedListener customOnItemSelectedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        //addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();
    }
    // add items into spinner dynamically
   /* public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }*/
    public void addListenerOnSpinnerItemSelection() {
        customOnItemSelectedListener=new CustomOnItemSelectedListener();
        spinner1 = (Spinner) findViewById(R.id.spinner1);


        ArrayAdapter<String>adapter = new ArrayAdapter<String>(locations.this,
                android.R.layout.simple_spinner_item);

        spinner1.setOnItemSelectedListener(this);
        Button map = (Button) findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //String loc=customOnItemSelectedListener.getLocation();
             try {

                    // Process text for network transmission
                    //String address = addrText.getText().toString();
                    String address = loc;
                    address = address.replace(' ', '+');

                    // Create Intent object for starting Google Maps application
                    Intent geoIntent = new Intent(
                            android.content.Intent.ACTION_VIEW, Uri
                            .parse("geo:0,0?q=" + address));

                    // Use the Intent to start Google Maps application using Activity.startActivity()
                    startActivity(geoIntent);

                } catch (Exception e) {
                    // Log any error messages to LogCat using Log.e()
                    Log.e(TAG, e.toString());
                }
                Toast.makeText(locations.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + loc,
                        Toast.LENGTH_SHORT).show();
            }

        });


    }


    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
        if(parent.getItemAtPosition(position)==null) {
        }
        else{
            loc=parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
