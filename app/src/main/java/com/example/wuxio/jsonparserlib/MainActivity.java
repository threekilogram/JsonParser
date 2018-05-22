package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jsonparser.JsonParserV2;
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

        JsonParserV2.debug(true);
        JsonParserV2 jsonParserV2 = new JsonParserV2();
        StringReader reader = new StringReader(TestJson.JSON);
        jsonParserV2.parse(reader);

    }

}
