package com.example.triviaapi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
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


public class MainActivity extends AppCompatActivity implements TriviaAdapter.OnQuestionClicked, View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private TriviaAdapter triviaAdapter;
    Button btnTrue, btnFalse;
    private TextView tvQuestion;
    Integer score=0;
    int currentQuestion = 0;

    private List<Question> questions = new ArrayList<>();
    private TextView tv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivia_activity_main);
        tvQuestion = findViewById(R.id.question_container);
        btnTrue = findViewById(R.id.btn_true);
        btnTrue.setOnClickListener(this);
        btnFalse = findViewById(R.id.btn_false);
        btnFalse.setOnClickListener(this);
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        volleyRequest(10, "boolean");
        //volleyRequest2(10, "multiple");

        Toast.makeText(this, "Compiling Data", Toast.LENGTH_SHORT).show();

    }

    public void volleyRequest(int count, String type) {
        //set up Url
        String baseUrl = "https://opentdb.com/api.php";
        String query1 = "?amount=" + count;
        String query2 = "&type=" + type;

        String url = baseUrl + query1 + query2;

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
                            Type listType = new TypeToken<ArrayList<Question>>() {
                            }.getType();
                            // Gson converts the json to the type we specified above
                            questions.addAll(new Gson().fromJson(jsonArray.toString(), listType));
                            //forming the questions
                            //loadRecyclerview(questions);

                            if (questions.isEmpty()) {
                                finish();
                            } else {
                                loadQuestion();
                            }


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


    public void volleyRequest2(int count, String type) {
        //set up Url
        String baseUrl = "https://opentdb.com/api.php";
        String query1 = "?amount=10" + count;
        String query2 = "&type=" + type;
        String url = baseUrl + query1 + query2;

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
                        List<Question> questionList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                Log.d(TAG, "onResponse: reponse code is " + response.getInt("response_code"));

                                // Get the array of results from the response
                                JSONArray jsonArray = response.getJSONArray("results");

                                Log.d(TAG, "onResponse: " + jsonArray.toString());

                                // This creates the type of data we are expecting back from the json
                                Type listType = new TypeToken<ArrayList<Question>>() {
                                }.getType();
                                // Gson converts the json to the type we specified above
                                List<Question> questions = new Gson().fromJson(jsonArray.toString(), listType);

                                loadRecyclerview(questions);

                                Log.d(TAG, "onResponse: " + questions.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        loadRecyclerview(questionList);

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

    private void loadRecyclerview(List<Question> strings) {
        triviaAdapter = new TriviaAdapter(strings, MainActivity.this);
        recyclerView.setAdapter(triviaAdapter);


    }


    @Override
    public void questionClicked(Question question) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_true:
                validateAnswer("True");
                break;
            case R.id.btn_false:
                validateAnswer("False");
                break;
        }

    }

    private void validateAnswer(String selectedChoice) {
        String correctAnswer = questions.get(currentQuestion).getCorrectAnswer();
        if (correctAnswer.equals(selectedChoice)) {
            tvQuestion.setBackgroundColor(Color.GREEN);
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
            score= score + 1;

            // if correct do something
        } else {
            tvQuestion.setBackgroundColor(Color.RED);
            Toast.makeText(this, "InCorrect", Toast.LENGTH_LONG).show();

        }
        currentQuestion++;
        loadQuestion();
        tvQuestion.setBackgroundColor(Color.WHITE);
    }

    private void loadQuestion() {
        if (currentQuestion<questions.size()) {


            String question = questions.get(currentQuestion).getQuestion();
            tvQuestion.setText(Html.fromHtml(question));
        } else {

            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
            startActivity(intent);


        }

    }
}
