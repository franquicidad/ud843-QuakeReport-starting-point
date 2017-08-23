/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private EarthquakeAdapter adapter;


    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
         adapter = new EarthquakeAdapter(
                this, R.layout.earthquake_activity, new ArrayList<Earthquake>());

        earthquakeListView.setAdapter(adapter);

        new EarthquakeAsyncTask().execute(SAMPLE_JSON_URL);

    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void,List<Earthquake>>{

        @Override
        protected List<Earthquake> doInBackground(String... urls) {

            URL url=QueryUtils.createUrl(urls[0]);
            try {
                String jsonResponce= QueryUtils.makeHttpRequest(url);
                ArrayList<Earthquake> earthquakes=QueryUtils.extractEarthquakes(jsonResponce);
                return earthquakes;
            } catch (IOException e) {
                Log.e(LOG_TAG,"No problemo",e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Earthquake> data) {
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id== R.id.action_settings){
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
