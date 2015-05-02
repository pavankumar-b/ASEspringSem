package com.example.pavan.goeasy;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.net.Uri;

public class UniversityEvent extends ActionBarActivity {
private List<String> result;
private final String TAG = "MapLocation";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_event);
        Boolean event=true;
        //TextView listText= (TextView) findViewById(R.id.textView10);
        Button map= (Button) findViewById(R.id.map);

        try {
            FetchEventLists task1 = (FetchEventLists) new FetchEventLists().execute();
            result = task1.get();
            int resultSize=result.size();
            ListIterator itr=result.listIterator();
            String finalOutput="";
            while(itr.hasNext()) {
                finalOutput=finalOutput+itr.next().toString()+"\n"+"\n";
            }
           // listText.setText(finalOutput);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.listtrans,result);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* try {

                    // Process text for network transmission
                    //String address = addrText.getText().toString();
                    String address = "UMKC , kansas city";
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
                }*/

                Intent locations=new Intent(UniversityEvent.this,locations.class);
                startActivity(locations);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_university_event, menu);
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

    public class FetchEventLists extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... details) {
            // TODO: attempt authentication against a network service.

            String url="http://10.0.2.2:56120/Service1.svc/GetEventsDetails/4-5-2015";
            Boolean rtr=true;
            StringBuilder stringBuilder = new StringBuilder();
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
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
                        String user = line;
                        rtr = false;
                        try {
                            JSONArray jsonArray = new JSONArray(line);
                            JSONObject jsonObject = new JSONObject();
                            int size=jsonArray.length();
                            result=new ArrayList<>();
                            for(int i=0;i<size;i++){
                                jsonObject= jsonArray.getJSONObject(i);
                                result.add(jsonObject.getString("date").replace("/","")+"\t"+jsonObject.getString("eventName")+"\t"+jsonObject.getString("location"));
                                System.out.println(result);
                            }
                        } catch (Exception e) {
                            Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
                        }
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                }
            }
            catch(Exception e){

            }

            return result;

            //return readJSONFeed(url);

        }

        @Override
        protected void onPostExecute(List<String> result) {
            try {
                //JSONArray jsonArray = new JSONArray(result)
          /*      JSONObject jsonObject = new JSONObject(result);

                Toast.makeText(getBaseContext(),
                        jsonObject.getString("pwd") +
                                " - " + jsonObject.getString("user"),
                        Toast.LENGTH_SHORT).show();

                System.out.print("Password is" + jsonObject.getString("pwd") + "USER is" + jsonObject.getString("user"));*/
            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }
        }

        @Override
        protected void onCancelled() {
            //mAuthTask = null;
            //showProgress(false);
        }

    }
}
