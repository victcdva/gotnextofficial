package com.gottnext.gotnextoficial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadPost extends AsyncTask<String,Void,ArrayList<PostPerson>> {

    //constant
    private static final String API_URL = "http://gottnext.com/GotNext/gotnext/post/";
    private static final String KEY_POST = "posts";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "postphoto";
    private static final String KEY_PERSON = "person";
    private static final String KEY_NAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_PERSONIMAGE = "personPhoto";
    //atributes
    private Activity activity;
    private ProgressDialog progress;
    //constructor
    public LoadPost(Activity activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute(){
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
    }
    @Override
    protected ArrayList<PostPerson> doInBackground(String... params){
        ArrayList<PostPerson> list = new ArrayList<PostPerson>();
        //variables
        InputStream data = null;
        String result = "";
        JSONObject resultJson = null;
        boolean error = false;

        //connect to api
        URL url;
        HttpURLConnection connection = null;
        try{
            url = new URL(API_URL);
            connection = (HttpURLConnection)url.openConnection();
            data = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(data));
            String line = "";
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            result = sb.toString();
            Log.d("result",result);
        }
        catch (MalformedURLException ex){
            error = true;
            Log.e("Error",ex.getMessage());
        }
        catch (IOException ex){
            error = true;
            Log.e("Error",ex.getMessage());
        }
        finally {
            if (connection != null) connection.disconnect();
        }

        //parse data to Json
        if(!error){
            try{
                resultJson = new JSONObject(result);
            }catch (JSONException ex){
                error = true;
                Log.e("Error",ex.getMessage());
            }
        }

        //create object and populate list
        if(!error){
            try{
                JSONArray array = resultJson.getJSONArray(KEY_POST);

                for (int i=0;i < array.length(); i++){
                    JSONObject item = array.getJSONObject(i);

                    // read Keys
                    String postImage = item.getString(KEY_IMAGE);
                    String postText = item.getString(KEY_DESCRIPTION);

                    //Person
                    JSONObject person = new JSONObject(item.getString(KEY_PERSON));
                    String personImage = person.getString(KEY_PERSONIMAGE);
                    String namePerson = person.getString(KEY_NAME);
                    String lastNamePerson = person.getString(KEY_LASTNAME);

                    //create object
                    PostPerson pp = new PostPerson(postImage, postText, personImage, namePerson, lastNamePerson);
                    //add object to list
                    list.add(pp);
                }
            }catch (JSONException ex){
                error = true;
                Log.e("Error",ex.getMessage());
            }
        }

        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<PostPerson> list){
        //list view
        ListView listPost = (ListView)this.activity.findViewById(R.id.ListPostHome);
        //adapter
        PostAdapter adapter = new PostAdapter(this.activity,list);
        //bind list to adapter
        listPost.setAdapter(adapter);
        //stop progress dialog
        progress.dismiss();
    }

}
