package com.example.wuxio.jsonparserlib;

import android.app.Application;

import com.example.objectbus.BusConfig;

/**
 * @author wuxio 2018-05-15:15:30
 */
public class App extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        BusConfig.init();
    }
}
