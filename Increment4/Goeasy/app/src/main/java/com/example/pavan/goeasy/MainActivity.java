package com.example.pavan.goeasy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent myIntent=getIntent();
        String email =myIntent.getStringExtra("email");

        TextView text= (TextView) findViewById(R.id.loginEmail);
        text.setText("Welcome "+email);
        text.setTextColor(Color.RED);

        Button quickInfo= (Button) findViewById(R.id.quickInfo);
        //Button register= (Button) findViewById(R.id.register);

        quickInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashboard= new Intent(MainActivity.this,DashBoard.class);
                startActivity(dashboard);
            }
        });

       /* register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration=new Intent(MainActivity.this,Registration.class);
                startActivity(registration);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
