package com.example.wuxio.jsonparserlib;

import com.google.gson.Gson;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.jsonparser.JsonParser;
import com.example.jsonparser.ObjectNodeTree;
import com.example.objectbus.bus.BusStation;
import com.example.objectbus.bus.ObjectBus;
import com.example.objectbus.message.Messengers;
import com.example.objectbus.message.OnMessageReceiveListener;
import com.example.wuxio.jsonparserlib.bean.GankBean;
import com.example.wuxio.jsonparserlib.json.GankJson;

import java.io.File;
import java.io.StringReader;
import java.util.Locale;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity implements OnMessageReceiveListener {

    private static final String TAG = "MainActivity";

    protected TextView       mText00;
    protected TextView       mText01;
    protected LinearLayout   mContainer;
    protected NavigationView mNavigationView;
    protected DrawerLayout   mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        sampleForParserUse();
        initView();
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


    private void initView() {

        mText00 = findViewById(R.id.text00);
        mText01 = findViewById(R.id.text01);
        mContainer = findViewById(R.id.container);
        mNavigationView = findViewById(R.id.navigationView);
        mDrawer = findViewById(R.id.drawer);

        mNavigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.menu00:
                    testGson();
                    break;

                case R.id.menu01:
                    testFastJson();
                    break;

                case R.id.menu02:
                    testParser();
                    break;
                default:
                    break;
            }

            closeDrawer();
            return true;
        });
    }


    @Override
    public void onReceive(int what, Object extra) {

        if (what == 7) {

            int i = ((Integer) extra);
            String text = String.format(Locale.CHINA, "%d%%", i);
            mText00.setText(text);

        } else if (what == 9) {

            int i = ((Integer) extra);
            String text = String.format(Locale.CHINA, "耗时 : %d", i);
            mText01.setText(text);
        }
    }


    private void testGson() {

        ObjectBus bus = BusStation.callNewBus();

        bus.toUnder(new Runnable() {
            @Override
            public void run() {

                Gson gson = new Gson();

                long start = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++) {

                    GankBean bean = gson.fromJson(new StringReader(GankJson.JSON), GankBean.class);

                    if (i % 100 == 0) {

                        int j = i / 100;
                        Messengers.send(7, Integer.valueOf(j), MainActivity.this);
                    }
                }
                long end = System.currentTimeMillis();
                int cast = (int) (end - start);
                Messengers.send(9, Integer.valueOf(cast), MainActivity.this);

                BusStation.recycle(bus);
            }
        }).run();

    }


    private void testParser() {

        ObjectBus bus = BusStation.callNewBus();

        bus.toUnder(new Runnable() {
            @Override
            public void run() {

                JsonParser parser = new JsonParser();

                File trace = getExternalFilesDir("trace");

                long start = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++) {

                    StringReader reader = new StringReader(GankJson.JSON);
                    parser.parse(reader);

                    if (i % 100 == 0) {

                        int j = i / 100;
                        Messengers.send(7, Integer.valueOf(j), MainActivity.this);
                    }
                }
                long end = System.currentTimeMillis();
                int cast = (int) (end - start);

                Messengers.send(9, Integer.valueOf(cast), MainActivity.this);

                BusStation.recycle(bus);
            }
        }).run();

    }


    private void testFastJson() {

        ObjectBus bus = BusStation.callNewBus();

        bus.toUnder(new Runnable() {
            @Override
            public void run() {

                long start = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++) {

                    Object parse = JSONObject.parse(GankJson.JSON);

                    if (i % 100 == 0) {

                        int j = i / 100;
                        Messengers.send(7, Integer.valueOf(j), MainActivity.this);
                    }
                }
                long end = System.currentTimeMillis();
                int cast = (int) (end - start);
                Messengers.send(9, Integer.valueOf(cast), MainActivity.this);

                BusStation.recycle(bus);
            }
        }).run();

    }


    private void closeDrawer() {

        mDrawer.closeDrawer(Gravity.START);
    }
}
