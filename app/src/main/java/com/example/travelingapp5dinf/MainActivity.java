package com.example.travelingapp5dinf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtOut;

    RequestQueue queue;
    String url;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtOut = findViewById(R.id.textViewOut);

        queue = Volley.newRequestQueue(this);
        url = "https://run.mocky.io/v3/94baa47d-5333-4020-8953-a5915caac7b1";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        toast.makeText(getApplicationContext(),
                                "Response: " + response, Toast.LENGTH_LONG).show();

                        try{
                            JSONObject myJsonObject = new JSONObject(response);
                            txtOut.setText(myJsonObject.getString("Users"));
                            //totalRecoveredWorld.setText(myJsonObject.getString("age"));
                            //totalDeathsWorld.setText(myJsonObject.getString("city"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtOut.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}