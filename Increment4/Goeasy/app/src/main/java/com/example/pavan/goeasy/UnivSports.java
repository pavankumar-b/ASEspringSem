package com.example.pavan.goeasy;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pavan.goeasy.ItemListBase2Adapter;
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


public class UnivSports extends ActionBarActivity {
    private List<String> result;
    private ArrayList<SportsDetails> sportsDetailsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_univ_sports);
        //TextView content= (TextView) findViewById(R.id.pageContent);
        sportsDetailsList=new ArrayList<SportsDetails>();

        try {
            FetchEventLists task1 = (FetchEventLists) new FetchEventLists().execute();
            sportsDetailsList = task1.get();
            int resultSize=sportsDetailsList.size();
            ListIterator itr=sportsDetailsList.listIterator();
            String finalOutput="";
            /*while(itr.hasNext()) {
                finalOutput=finalOutput+itr.next().toString()+"\n"+"\n";
            }*/
            //content.setText(finalOutput);
            //ArrayAdapter<String> adap=new ArrayAdapter<String>(this,R.layout.listtrans,sportsDetailsList);

            ArrayList<SportsDetails> details=sportsDetailsList;

            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new ItemListBase2Adapter(this,details));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = listView.getItemAtPosition(position);
                    SportsDetails obj_sportsDetails = (SportsDetails) o;
                    String location=obj_sportsDetails.getLocation();
                    try{
                        String address = location;
                        address = address.replace(' ', '+');

                        // Create Intent object for starting Google Maps application
                        Intent geoIntent = new Intent(
                                android.content.Intent.ACTION_VIEW, Uri
                                .parse("geo:0,0?q=" + address));

                        // Use the Intent to start Google Maps application using Activity.startActivity()
                        startActivity(geoIntent);
                    }
                    catch(Exception e){

                    }
                    // Toast.makeText(StudentHome.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_univ_sports, menu);
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
    public class FetchEventLists extends AsyncTask<String, Void, ArrayList<SportsDetails>> {

        @Override
        protected ArrayList<SportsDetails> doInBackground(String... details) {
            // TODO: attempt authentication against a network service.

            String url="http://10.0.2.2:56120/Service1.svc/GetSportsDetails/4-5-2015";
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
                                SportsDetails sportList=new SportsDetails();
                                sportList.setDate(jsonObject.getString("date").replace("/",""));
                                sportList.setEvent(jsonObject.getString("eventName"));
                                sportList.setLocation(jsonObject.getString("location"));
                                sportList.setTime(jsonObject.getString("time"));

                                sportsDetailsList.add(sportList);

                                result.add(jsonObject.getString("date").replace("/","")+"\t"+jsonObject.getString("eventName")+"\t"+jsonObject.getString("location")+"\t"+jsonObject.getString("time"));
                                //System.out.println(result);
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

            return sportsDetailsList;

            //return readJSONFeed(url);

        }

        @Override
        protected void onPostExecute(ArrayList<SportsDetails> result) {
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
