package com.threekilogram.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import com.threekilogram.jsonparser.JsonParser;
import com.threekilogram.wuxio.jsonparserlib.bean.GankBean;
import com.threekilogram.wuxio.jsonparserlib.bean.JSONString;
import com.threekilogram.wuxio.jsonparserlib.json.GankJson;
import com.threekilogram.wuxio.jsonparserlib.json.TestJson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * @author liujin
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      private FrameLayout mRoot;
      private File        mTemp;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );

            initView();
      }

      private void testBigSkip ( ) {

            JsonParser jsonParser = new JsonParser( new StringReader( GankJson.BEAUTY_JSON ) );
            try {
                  jsonParser.start();
                  while( jsonParser.peek() != JsonToken.END_DOCUMENT ) {
                        jsonParser.skipToString( "url" );
                        String url = jsonParser.readString( "url" );
                        Log.e( TAG, "testBigSkip : " + url );
                  }
            } catch(IOException e) {
                  e.printStackTrace();
            }
      }

      private void initView ( ) {

            mRoot = findViewById( R.id.root );
      }

      private void testBuilder ( ) throws IOException {

            //创建
            JsonParser builder = new JsonParser( new StringReader( GankJson.JSON ) );

            //传入流
            builder.start();

            //读取第一个boolean值
            boolean error = builder.readBoolean( "error", false );

            //开始读取json类组成的数组节点
            builder.readArray( "results" );

            //循环解析json数组中的json类
            for( int i = 0; i < 2; i++ ) {

                  //因为元素是一个json类,所以需要beginObject()
                  builder.beginObject();

                  //按照json中出现顺序读取所有属性
                  String id = builder.readString( "_id" );
                  String createdAt = builder.readString( "createdAt" );
                  String desc = builder.readString( "desc" );

                  //读取string数组
                  List<String> images = builder.readStringArray( "images" );

                  String publishedAt = builder.readString( "publishedAt" );
                  String source = builder.readString( "source" );
                  String type = builder.readString( "type" );
                  String url = builder.readString( "url" );
                  boolean used = builder.readBoolean( "used", true );
                  String who = builder.readString( "who" );

                  //读取完成之后结束类
                  builder.endObject();
            }

            //结束读取json类组成的数组节点
            builder.endArray();

            //结束整个流的解析
            builder.finish();
      }

      private void testJson ( ) throws IOException {

            //创建json数据流
            StringReader reader = new StringReader( TestJson.Json );
            //创建解析
            JsonParser parser = new JsonParser( reader );
            //开始解析流
            parser.start();
            //需要按照json中出现顺序解析
            //读取string节点
            String name = parser.readString( "name" );
            //读取int节点
            int anInt = parser.readInt( "int" );
            //读取long节点
            long aLong = parser.readLong( "long" );
            //读取double节点
            double aDouble = parser.readDouble( "double" );
            //读取boolean节点
            boolean aBoolean = parser.readBoolean( "boolean" );
            //读取null节点,
            String empty = parser.readString( "empty" );
            //读取string array 节点
            List<String> stringArray = parser.readStringArray( "stringArray" );
            //读取int array 节点
            int[] intArrays = parser.readIntArray( "intArray" );
            //读取long array 节点
            long[] longArrays = parser.readLongArray( "longArray" );
            //读取double array 节点
            double[] doubleArrays = parser.readDoubleArray( "doubleArray" );
            //读取boolean array 节点
            boolean[] booleanArrays = parser.readBooleanArray( "booleanArray" );
            // 开始读取一个json类
            parser.readObject( "object" );
            // 读取json类,string节点
            String name1 = parser.readString( "name" );
            // 读取json类,int节点
            int age = parser.readInt( "age" );
            // 结束读取json类
            parser.endObject();
            // 开始读取json类组成的数组
            parser.readArray( "objectArray" );
            for( int i = 0; i < 3; i++ ) {
                  parser.beginObject();

                  while( parser.peek() != JsonToken.END_OBJECT ) {

                        String name2 = parser.readString( "name" );
                        log( "name", name2 );
                        int age1 = parser.readInt( "age" );
                        log( "age", age1 );
                  }
                  parser.endObject();
            }
            parser.endArray();
            parser.finish();
      }

      private void testJsonSkip ( ) throws IOException {

            StringReader reader = new StringReader( TestJson.Json );
            JsonParser parser = new JsonParser( reader );

            parser.start();

            //跳转到指定名称的节点
            parser.skipToNode( "skipString" );
            //跳转到"skipString"
            parser.skipToString( "skipString" );
            String string = parser.readString( "skipString" );
            log( "skipString", string );

            parser.skipToNumber( "skipInt" );
            int skipInt = parser.readInt( "skipInt" );
            log( "skipInt", skipInt );

            parser.skipToNumber( "skipLong" );
            long skipLong = parser.readLong( "skipLong" );
            log( "skipLong", skipLong );

            parser.skipToNumber( "skipDouble" );
            double skipDouble = parser.readDouble( "skipDouble" );
            log( "skipDouble", skipDouble );

            parser.skipToBoolean( "skipBoolean" );
            boolean skipBoolean = parser.readBoolean( "skipBoolean" );
            log( "skipBoolean", skipBoolean );

            parser.skipToArray( "skipStringArray" );
            List<String> skipStringArray = parser.readStringArray( "skipStringArray" );
            log( "skipStringArray", skipStringArray );

            parser.skipToArray( "skipIntArray" );
            int[] skipIntArrays = parser.readIntArray( "skipIntArray" );
            log( "skipIntArray", skipIntArrays );

            parser.skipToArray( "skipLongArray" );
            long[] skipLongArrays = parser.readLongArray( "skipLongArray" );
            log( "skipLongArray", skipLongArrays );

            parser.skipToArray( "skipDoubleArray" );
            double[] skipDoubleArrays = parser.readDoubleArray( "skipDoubleArray" );
            log( "skipDoubleArray", skipDoubleArrays );

            parser.skipToArray( "skipBooleanArray" );
            boolean[] skipBooleanArrays = parser.readBooleanArray( "skipBooleanArray" );
            log( "skipBooleanArray", skipBooleanArrays );

            parser.skipToObject( "skipObject" );
            parser.readObject( "skipObject" );
            String name = parser.readString( "name" );
            log( "name", name );
            int age = parser.readInt( "age" );
            log( "age", age );
            parser.endObject();

            parser.skipToArray( "skipObjectArray" );
            parser.readArray( "skipObjectArray" );

            while( parser.hasNext() ) {

                  parser.beginObject();
                  String name1 = parser.readString( "name" );
                  log( "name", name1 );
                  int age1 = parser.readInt( "age" );
                  log( "age", age1 );
                  parser.endObject();
            }

            parser.endArray();
            parser.finish();
      }

      private void log ( String key, Object value ) {

            Log.e( TAG, "log : " + key + " : " + value );
      }

      public void parser ( View view ) {

            Gson gson = new Gson();
            Reader reader = new StringReader( JSONString.TEMP );
            long l = System.currentTimeMillis();
            GankBean gankBean = gson.fromJson( reader, GankBean.class );
            long l1 = System.currentTimeMillis();

            Toast.makeText( this, "耗时 " + ( l1 - l ), Toast.LENGTH_SHORT ).show();
      }

      public void test ( View view ) {

            try {
                  Reader reader = new StringReader( JSONString.TEMP );
                  JsonParser parser = new JsonParser( reader );
                  long l = System.currentTimeMillis();
                  parser.start();
                  parser.finish();
                  long l1 = System.currentTimeMillis();
                  Toast.makeText( this, "耗时 " + ( l1 - l ), Toast.LENGTH_SHORT ).show();
            } catch(FileNotFoundException e) {
                  e.printStackTrace();
            } catch(IOException e) {
                  e.printStackTrace();
            }
      }
}
