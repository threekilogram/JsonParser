package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jsonparser.JsonParser;
import com.example.jsonparser.ObjectNodeTree;
import com.example.wuxio.jsonparserlib.json.TestJson;

import java.io.StringReader;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        JsonParser jsonParser = new JsonParser();
        StringReader reader = new StringReader(TestJson.JSON);

        Log.i(TAG, "initView:" + TestJson.JSON);

        ObjectNodeTree tree = jsonParser.parse(reader);
        String name = tree.getString("name");
        Log.i(TAG, "initView:" + name);

    }
}
