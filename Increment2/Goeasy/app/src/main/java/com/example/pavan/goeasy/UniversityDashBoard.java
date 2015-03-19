package com.example.pavan.goeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by pavan on 3/17/2015.
 */
public class UniversityDashBoard extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.univ_dashboard);
        ImageView view1= (ImageView) findViewById(R.id.imageView);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UnivDas = new Intent(UniversityDashBoard.this,UnivWebPage.class);
                UnivDas.putExtra("url","http://www.umkc.edu/welcome");
                startActivity(UnivDas);
            }
        });
        ImageView view2 = (ImageView) findViewById(R.id.imageView2);
        view2.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                 public void onClick(View v)
                                     {
                                         Intent UnivDas = new Intent(UniversityDashBoard.this,UnivWebPage.class);
                                         UnivDas.putExtra("url","https://www.google.com/maps");
                                         startActivity(UnivDas);
                                     }
                                 }
        );
        ImageView view3 = (ImageView) findViewById(R.id.imageView3);
        view3.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v)
                                     {
                                         Intent UnivDas = new Intent(UniversityDashBoard.this,UnivWebPage.class);
                                         UnivDas.putExtra("url","http://www.umkc.edu/academics/");
                                         startActivity(UnivDas);
                                     }
                                 }
        );
        ImageView view4 = (ImageView) findViewById(R.id.imageView4);
        view4.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v)
                                     {
                                         Intent UnivDas = new Intent(UniversityDashBoard.this,UnivWebPage.class);
                                         UnivDas.putExtra("url","http://www.umkc.edu/calendar/");
                                         startActivity(UnivDas);
                                     }
                                 }
        );
        ImageView view5 = (ImageView) findViewById(R.id.imageView5);
        view5.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v)
                                     {
                                         Intent UnivDas = new Intent(UniversityDashBoard.this,UnivWebPage.class);
                                         UnivDas.putExtra("url","http://info.umkc.edu/unews/category/sports/");
                                         startActivity(UnivDas);
                                     }
                                 }
        );

        ImageView view6 = (ImageView) findViewById(R.id.imageView6);
        view6.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v)
                                     {
                                         Intent UnivDas = new Intent(UniversityDashBoard.this,UnivWebPage.class);
                                         UnivDas.putExtra("url","http://www.umkc.edu/transportation/");
                                         startActivity(UnivDas);
                                     }
                                 }
        );



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_university_fpage, menu);
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
