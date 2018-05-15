package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jsonparser.JsonParser;
import com.example.jsonparser.ValueHolder;
import com.example.wuxio.jsonparserlib.json.TestJson;

import java.io.StringReader;
import java.util.List;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParser.log(false);
        JsonParser.create(new JsonParser.OnParseListener() {
            @Override
            public void onParseTo(List< JsonParser.Node > nodes, String key, ValueHolder valueHolder) {

                Log.e(TAG, "onParseTo: " + nodes + " " + key + " " + valueHolder.value());
            }


            @Override
            public void onNewArrayRequire(List< JsonParser.Node > nodes) {

                Log.e(TAG, "onNewArrayRequire: " + nodes);
            }


            @Override
            public void onNewArrayElementRequire(List< JsonParser.Node > nodes) {

                Log.e(TAG, "onNewArrayElementRequire: " + nodes);
            }


            @Override
            public void onNewObjectRequire(List< JsonParser.Node > nodes) {

                Log.e(TAG, "onNewObjectRequire: " + nodes);
            }
        }).parse(new StringReader(TestJson.JSON));
    }
}
