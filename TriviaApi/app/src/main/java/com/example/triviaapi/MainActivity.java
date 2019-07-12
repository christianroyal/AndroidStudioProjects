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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.triviaapi.model.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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


        volleyRequest(10);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    public void volleyRequest(int count) {
        //set up Url
        String baseUrl = "https://opentdb.com/api.php";
        String query = "?amount=10&type=boolean" +count;
        String url = baseUrl + query;

        //Declare RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //Declare JSONArrayRequest or JSONObjectRequest [=Array {=Object...
        //Then init either structure.....
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d(TAG, "onResponse: reponse code is " + response.getInt("response_code"));

                            // Get the array of results from the response
                            JSONArray jsonArray = response.getJSONArray("results");

                            // This creates the type of data we are expecting back from the json
                            Type listType = new TypeToken<ArrayList<Question>>(){}.getType();
                            // Gson converts the json to the type we specified above
                            List<Question> questions = new Gson().fromJson(jsonArray.toString(), listType);
                           //forming the questions
                            questions.get(0).getQuestion();
                            questions.get(1).getQuestion();
                            questions.get(2).getQuestion();
                            questions.get(3).getQuestion();
                            questions.get(4).getQuestion();
                            questions.get(5).getQuestion();
                            questions.get(6).getQuestion();
                            questions.get(7).getQuestion();
                            questions.get(8).getQuestion();
                            questions.get(9).getQuestion();

                            //populating the answers
                            questions.get(0).getCorrectAnswer();
                            questions.get(1).getCorrectAnswer();
                            questions.get(2).getCorrectAnswer();
                            questions.get(3).getCorrectAnswer();
                            questions.get(4).getCorrectAnswer();
                            questions.get(5).getCorrectAnswer();
                            questions.get(6).getCorrectAnswer();
                            questions.get(7).getCorrectAnswer();
                            questions.get(8).getCorrectAnswer();
                            questions.get(9).getCorrectAnswer();

                            //populating wrong answers
                            questions.get(0).getIncorrectAnswers();
                            questions.get(1).getIncorrectAnswers();
                            questions.get(2).getIncorrectAnswers();
                            questions.get(3).getIncorrectAnswers();
                            questions.get(4).getIncorrectAnswers();
                            questions.get(5).getIncorrectAnswers();
                            questions.get(6).getIncorrectAnswers();
                            questions.get(7).getIncorrectAnswers();
                            questions.get(8).getIncorrectAnswers();
                            questions.get(9).getIncorrectAnswers();




                            Log.d(TAG, "onResponse: " + questions.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Step 4 Pass the request object from Step 3 into Requestqueue object from step 2
        requestQueue.add(request);


    }

    private void loadRecyclerview(List<String> strings) {
        triviaAdapter = new TriviaAdapter(strings, MainActivity.this);
        recyclerView.setAdapter(triviaAdapter);


    }


    @Override
    public void triviaclicked(String url) {

    }
}
