package com.isoftinc.taskdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    JSONArray jasonarry;
    RecyclerView rvlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvlist = findViewById(R.id.rv_todolist);
        LoadData();
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("tasks.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void LoadData()
    {
        jasonarry =new JSONArray();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            jasonarry = obj.getJSONArray("tasks");
            loadlist(jasonarry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void loadlist(JSONArray jasonarry) {
        AdapterTodoList Adapter;
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false);
        rvlist.setLayoutManager(layoutManager);
        Adapter=new AdapterTodoList(jasonarry, MainActivity.this);
        rvlist.setAdapter(Adapter);
    }
}