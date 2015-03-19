package com.example.pavan.goeasy;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DashBoard extends ActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        WebView liveView= (WebView) findViewById(R.id.webViewLive);
        WebView transportView= (WebView) findViewById(R.id.webViewTransport);
        Button goBtn= (Button) findViewById(R.id.button2);
        //WebView webViewUniv= (WebView) findViewById(R.id.webViewUniv);


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


        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent univDash=new Intent(DashBoard.this,UniversityDashBoard.class);
                startActivity(univDash);
            }
        });
        /*LocalActivityManager mLocalActivityManager = getLocalActivityManager();

        View view = getLocalActivityManager().startActivity("ArchiveActivity",
                new Intent(this, UniversityFPage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();*/

        //setContentView(view);
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
    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }



    private class ReadWeatherJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                //JSONArray jsonArray = new JSONArray(result)
                JSONObject jsonObject = new JSONObject(result);

                Toast.makeText(getBaseContext(),
                        jsonObject.getString("pwd") +
                                " - " + jsonObject.getString("user"),
                        Toast.LENGTH_SHORT).show();

                System.out.print("Password is"+jsonObject.getString("pwd")+"USER is"+jsonObject.getString("user"));
            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }
        }
    }
}
