package com.example.aashiqmohamed.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;

    RequestQueue requestQueue;

    String BaseURL = "https://api.openweathermap.org/data/2.5/weather?q=";
    String API_KEY = "&appid=0a1c8a6c5be6c21acd681c93eaaa9f3c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String city = editText.getText().toString();
                String url = BaseURL + city + API_KEY;

                final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String info = response.getString("weather");

                            JSONArray array = new JSONArray(info);
                            for (int i=0; i<array.length();i++){

                                JSONObject datas = array.getJSONObject(i);
                                String myWeather = datas.getString("main");
                                textView.setText(myWeather);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                });

                requestQueue.add(request);

            }
        });


    }
}


//0a1c8a6c5be6c21acd681c93eaaa9f3c  API KEY

//https://api.openweathermap.org/data/2.5/weather?q=chennai&appid=0a1c8a6c5be6c21acd681c93eaaa9f3c