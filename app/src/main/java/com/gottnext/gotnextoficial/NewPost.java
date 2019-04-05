package com.gottnext.gotnextoficial;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NewPost  extends AppCompatActivity {

    EditText _postThink;
    Button _btnSavePost, _btnPhoto;
    RequestQueue _requestQueue;
    String apiUrl = "http://gottnext.com/GotNext/gotnext/post/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpost);

        ActionBar action = getSupportActionBar();
        assert action != null;
        action.setDisplayHomeAsUpEnabled(true);

        _postThink = findViewById(R.id.postThink);
        _btnSavePost = findViewById(R.id.savePost);
        _btnPhoto = findViewById(R.id.btnPhoto);

        _requestQueue = Volley.newRequestQueue(getApplicationContext());
        _btnSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, apiUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<>();
                        parameters.put("description",_postThink.getText().toString());
                        parameters.put("postphoto", "p1.jpg");
                        parameters.put("postdate", "2019-03-20 12:30:23");
                        parameters.put("person", "2");

                        return parameters;
                    }
                };
                _requestQueue.add(request);
            }
        });
    }
}
