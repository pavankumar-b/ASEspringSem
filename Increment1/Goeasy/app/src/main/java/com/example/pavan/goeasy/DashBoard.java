package com.example.pavan.goeasy;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TabHost;


public class DashBoard extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        WebView liveView= (WebView) findViewById(R.id.webViewLive);
        WebView transportView= (WebView) findViewById(R.id.webViewTransport);
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec=tabHost.newTabSpec("LiveEvents");
        tabSpec.setContent(R.id.liveEvents);
        tabSpec.setIndicator("Live Events");
        liveView.loadUrl("http://kansascity.eventful.com/events");
        tabHost.addTab(tabSpec);

        tabSpec=tabHost.newTabSpec("University");
        tabSpec.setContent(R.id.univ);
        tabSpec.setIndicator("University");
        tabHost.addTab(tabSpec);

        tabSpec=tabHost.newTabSpec("Transportation");
        tabSpec.setContent(R.id.transportation);
        tabSpec.setIndicator("Transportation");
        transportView.loadUrl("http://www.kcata.org/documents/routes/maps/35mwk.gif");
        tabHost.addTab(tabSpec);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
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
