package com.example.jsonparser;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author wuxio 2018-05-14:9:47
 */
public class JsonParser {


    public static final String json = "{\n" +
            "    \"error\": false,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"_id\": \"5a967b41421aa91071b838f7\",\n" +
            "            \"createdAt\": \"2018-02-28T17:49:53.265Z\",\n" +
            "            \"desc\": \"MusicLibrary-一个丰富的音频播放SDK\",\n" +
            "            \"publishedAt\": \"2018-03-12T08:44:50.326Z\",\n" +
            "            \"source\": \"web\",\n" +
            "            \"type\": \"Android\",\n" +
            "            \"url\": \"https://github.com/lizixian18/MusicLibrary\",\n" +
            "            \"used\": true,\n" +
            "            \"who\": \"lizixian\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"_id\": \"5a9cf1f5421aa9103fff20b1\",\n" +
            "            \"createdAt\": \"2018-03-05T15:29:57.435Z\",\n" +
            "            \"desc\": \"DirectSelect Dropdown is a selection widget with an ethereal, " +
            "full-screen modal popup displaying the available choices when the widget is interact with.\"," +
            "\n" +
            "            \"images\": [\n" +
            "                \"http://img.gank.io/90db2f35-2e9d-4d75-b5a9-53ee1719b57b\"\n" +
            "            ],\n" +
            "            \"publishedAt\": \"2018-03-12T08:44:50.326Z\",\n" +
            "            \"source\": \"web\",\n" +
            "            \"type\": \"Android\",\n" +
            "            \"url\": \"https://github.com/Ramotion/direct-select-android\",\n" +
            "            \"used\": true,\n" +
            "            \"who\": \"Alex Mikhnev\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"_id\": \"5a9f4968421aa910426a1890\",\n" +
            "            \"createdAt\": \"2018-03-07T10:07:36.87Z\",\n" +
            "            \"desc\": \"TextPathView是一个把文字转化为路径动画然后展现出来的自定义控件\",\n" +
            "            \"images\": [\n" +
            "                \"http://img.gank.io/bc3987dc-eb33-451f-a901-3e65bd7b666b\"\n" +
            "            ],\n" +
            "            \"publishedAt\": \"2018-03-12T08:44:50.326Z\",\n" +
            "            \"source\": \"chrome\",\n" +
            "            \"type\": \"Android\",\n" +
            "            \"url\": \"https://github.com/totond/TextPathView\",\n" +
            "            \"used\": true,\n" +
            "            \"who\": \"Jason\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"_id\": \"5aa24192421aa9103fff20c3\",\n" +
            "            \"createdAt\": \"2018-03-09T16:10:58.75Z\",\n" +
            "            \"desc\": \"VirtualXposed：不用 Root，不解锁 BootLoader 也不用刷机就使用 Xposed 模块的APP\",\n" +
            "            \"publishedAt\": \"2018-03-12T08:44:50.326Z\",\n" +
            "            \"source\": \"web\",\n" +
            "            \"type\": \"Android\",\n" +
            "            \"url\": \"https://github.com/android-hacker/VirtualXposed\",\n" +
            "            \"used\": true,\n" +
            "            \"who\": \"weishu\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"_id\": \"5a7c42c8421aa90d24a065d4\",\n" +
            "            \"createdAt\": \"2018-02-08T20:30:00.798Z\",\n" +
            "            \"desc\": \"一个动画效果的播放控件，播放，暂停，停止之间的动画切换\",\n" +
            "            \"images\": [\n" +
            "                \"http://img.gank.io/c1ee3231-648d-4449-a455-04a13731b2e1\"\n" +
            "            ],\n" +
            "            \"publishedAt\": \"2018-02-22T08:24:35.209Z\",\n" +
            "            \"source\": \"web\",\n" +
            "            \"type\": \"Android\",\n" +
            "            \"url\": \"https://github.com/SwiftyWang/AnimatePlayButton\",\n" +
            "            \"used\": true,\n" +
            "            \"who\": null\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    private static final String TAG = "JsonParser";


    public void parse(String json) {

        StringReader stringReader = new StringReader(json);

        JsonReader jsonReader = new JsonReader(stringReader);

        try {

            JsonToken peek = jsonReader.peek();

            while (peek != JsonToken.END_DOCUMENT) {

                switch (peek) {

                    case BEGIN_OBJECT:
                        jsonReader.beginObject();
                        break;

                    case END_OBJECT:
                        jsonReader.endObject();
                        break;

                    case BEGIN_ARRAY:
                        jsonReader.beginArray();
                        break;

                    case END_ARRAY:
                        jsonReader.endArray();
                        break;

                    case NAME:
                        String name = jsonReader.nextName();
                        Log.i(TAG, "parse:name: " + name);
                        break;

                    case NULL:
                        jsonReader.nextNull();
                        Log.i(TAG, "parse:" + "null");
                        break;

                    case STRING:
                        String s = jsonReader.nextString();
                        Log.i(TAG, "parse:" + s);
                        break;

                    case BOOLEAN:
                        boolean nextBoolean = jsonReader.nextBoolean();
                        Log.i(TAG, "parse:" + nextBoolean);
                        break;

                    case NUMBER:
                        double v = jsonReader.nextDouble();
                        Log.i(TAG, "parse:" + v);
                        break;

                    default:
                        break;
                }

                peek = jsonReader.peek();
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static interface ParseListener {

        /**
         * parse to a int value
         *
         * @param name  name
         * @param value value
         */
        void parseTo(String name, String value);


        /**
         * parse to a null value
         *
         * @param name name
         */
        void parseTo(String name);

        /**
         * parse to a int value
         *
         * @param name  name
         * @param value value
         */
        void parseTo(String name, double value);
    }
}
