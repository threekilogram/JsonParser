package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jsonparser.JsonParser;
import com.example.jsonparser.ObjectNodeTree;
import com.example.wuxio.jsonparserlib.json.GankJson;

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
        sampleForParserUse();
    }


    private void sampleForParserUse() {

        JsonParser jsonParser = new JsonParser();
        StringReader reader = new StringReader(GankJson.JSON);

        Log.i(TAG, "sampleForParserUse:" + GankJson.JSON);

        ObjectNodeTree jsonTree = jsonParser.parse(reader);

        boolean error = jsonTree.getBoolean("error");
        Log.i(TAG, "error : " + error);

        String desc = jsonTree.getArray("results").getObject(0).getString("desc");
        Log.i(TAG, "results[0].desc : " + desc);

        String images_1 = jsonTree.getArray("results").getObject(0).getArray("images").getString(1);

        Log.i(TAG, "results[0].images[1] : " + images_1);
    }
}
