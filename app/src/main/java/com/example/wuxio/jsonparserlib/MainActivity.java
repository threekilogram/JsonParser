package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParser parser = new JsonParser();

        parser.parse(JsonParser.json02);

        Log.i(TAG, "onCreate:" + "=============================");

        //parser.parse(JsonParser.json02);
    }
}
