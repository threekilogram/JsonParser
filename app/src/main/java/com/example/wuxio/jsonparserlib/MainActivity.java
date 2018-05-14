package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jsonparser.JsonParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParser parser = new JsonParser();
        parser.parse(JsonParser.json);

    }
}
