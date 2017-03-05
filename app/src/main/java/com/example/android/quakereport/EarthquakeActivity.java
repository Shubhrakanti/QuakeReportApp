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

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ArrayList<Earthquake> earthquakes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10");

    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>>{

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            ArrayList<Earthquake> returnList = null;
            try {
                URL passIn = createUrl(urls[0]);
                String jsonResponse = QueryUtils.makeHttpRequest(passIn);
                if (jsonResponse.equals("")){
                    return null;
                }
                returnList = QueryUtils.extractEarthquakes(jsonResponse);

            } catch (Exception e){
                Log.d("MAINACTIVITY ASYNCTASK","ERROR");
            }
            if(returnList != null){
                return returnList;
            }
            else return null;

        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Problem building the URL ", e);
            }
            return url;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> result) {
            if(result == null){
                return;
            }

            earthquakes = result;

            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(getApplicationContext(), earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(earthquakeAdapter);
        }
    }
}
