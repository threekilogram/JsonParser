package com.example.wuxio.jsonparserlib;

import com.google.gson.Gson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.jsonparser.JsonParser;
import com.example.wuxio.jsonparserlib.bean.WeatherBean;
import com.example.wuxio.jsonparserlib.json.WeatherJson;
import com.example.wuxio.jsonparserlib.listener.WeatherParseListener;

import java.io.StringReader;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    protected FrameLayout    mContainer;
    protected NavigationView mNavigationView;
    protected DrawerLayout   mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        mContainer = findViewById(R.id.container);
        mNavigationView = findViewById(R.id.navigationView);
        mDrawer = findViewById(R.id.drawer);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {


                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.menu00:
                                testParser();
                                break;
                            case R.id.menu01:
                                testGson();
                                break;
                            case R.id.menu02:
                                break;
                            default:
                                break;
                        }

                        return true;
                    }
                });
    }


    private void testParser() {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                long start = System.currentTimeMillis();
                for (int i = 0; i < 100000; i++) {
                    JsonParser parser = JsonParser.create(new WeatherParseListener());
                    StringReader reader = new StringReader(WeatherJson.JSON);
                    parser.parse(reader);
                }
                long end = System.currentTimeMillis();
                Log.i(TAG, "testParser:" + (end - start));
            }
        };

        new Thread(runnable).start();
    }


    private void testGson() {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                Gson gson = new Gson();

                long start = System.currentTimeMillis();
                for (int i = 0; i < 100000; i++) {
                    StringReader reader = new StringReader(WeatherJson.JSON);
                    WeatherBean bean = gson.fromJson(reader, WeatherBean.class);
                }
                long end = System.currentTimeMillis();
                Log.i(TAG, "testGson:" + (end - start));
            }
        };

        new Thread(runnable).start();
    }

}
