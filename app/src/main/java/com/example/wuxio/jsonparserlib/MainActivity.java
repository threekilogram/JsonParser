package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jsonparser.JsonParser;
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

        Log.i(TAG, "initView:" + TestJson.JSON);

        JsonParser.debug(true);
        JsonParser jsonParser = new JsonParser();
        StringReader reader = new StringReader(TestJson.JSON00);
        jsonParser.parse(reader);

    }

}
