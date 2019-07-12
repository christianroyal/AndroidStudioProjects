package com.example.triviaapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TriviaAdapter.OnTrivaAnswerClicked {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private TriviaAdapter triviaAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        volleyRequest(44);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    public void volleyRequest(int count){
       //set up Url
        String baseUrl = "https://opentdb.com/api.php";
        String query = "?amount=" + count;
        String url = baseUrl + query;

        //Declare RequestQueue
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);

        //Declare JSONArrayRequest or JSONObjectRequest [=Array {=Object...
        //Then init either structure.....
        JsonArrayRequest request= new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<String>v= new ArrayList<>();
                        for (int i=0; i< response.length();i++) {try {
                            v.add(response.get(i).toString());
                            Log.d(TAG, "onResponse: "+response.get(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        }loadRecyclerview(v);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+ error.getLocalizedMessage());

                    }
                }

        );

        //Step 4 Pass the request object from Step 3 into Requestqueue object from step 2
        requestQueue.add(request);



    }

    private void loadRecyclerview(List<String> strings) {
        triviaAdapter = new TriviaAdapter(strings,MainActivity.this);
        recyclerView.setAdapter(triviaAdapter);


    }


    @Override
    public void triviaclicked(String url) {

    }
}
