package com.example.wuxio.jsonparserlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import com.example.jsonparser.JsonParser;
import com.example.jsonparser.JsonParserTTT;
import com.example.wuxio.jsonparserlib.json.GankJson;
import com.example.wuxio.jsonparserlib.json.TestJson;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * @author liujin
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      private FrameLayout   mRoot;
      private JsonParserTTT mParser;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );

            mParser = new JsonParserTTT();
            initView();

            try {
                  skipToString();
            } catch(IOException e) {
                  e.printStackTrace();
            }
      }

      private void initView ( ) {

            mRoot = (FrameLayout) findViewById( R.id.root );

            //mParser.parse( new StringReader( GankJson.JSON ) );
      }

      private void skipToString ( ) throws IOException {

            StringReader reader = new StringReader( TestJson.Json );

            JsonParser parser = new JsonParser();
            parser.start( reader );
            parser.skipToString( "skipString" );
            parser.peek();
            String skipString = parser.readString( "skipString" );
            log( "skipString", skipString );
            parser.finish();
      }

      private void log ( String key, Object value ) {

            Log.e( TAG, "log : " + key + " : " + value );
      }

      private void skipToObject ( ) throws IOException {

            StringReader reader = new StringReader( TestJson.Json );

            JsonParser parser = new JsonParser();
            parser.start( reader );
            parser.skipToObject( "object" );
            parser.peek();
            parser.readObject( "object" );
            String name = parser.readString( "name" );
            log( "name", name );
            parser.skipToEndObject();
            parser.peek();
            parser.finish();
      }

      private void skipToArray ( ) throws IOException {

            StringReader reader = new StringReader( TestJson.Json );

            JsonParser parser = new JsonParser();
            parser.start( reader );
            parser.skipToArray( "objectArray" );
            parser.peek();
            parser.readArray( "objectArray" );
            for( int i = 0; i < 3; i++ ) {
                  parser.beginObject();
                  String name2 = parser.readString( "name" );
                  log( "name", name2 );
                  int age1 = parser.readInt( "age" );
                  log( "age", age1 );
                  parser.endObject();
            }
            parser.endArray();
            parser.finish();
      }

      private void skipToEndObject ( ) throws IOException {

            StringReader reader = new StringReader( TestJson.Json );

            JsonParser parser = new JsonParser();
            parser.start( reader );
            String name = parser.readString( "name" );
            log( "name", name );
            parser.skipToEndObject();
            Log.e( TAG, "skipToEndObject : end" );
            int anInt = parser.readInt( "int" );
            log( "int", anInt );
            parser.peek();
            parser.finish();
      }

      private void testJson ( ) throws IOException {

            StringReader reader = new StringReader( TestJson.Json );

            JsonParser parser = new JsonParser();
            parser.start( reader );
            String name = parser.readString( "name" );
            log( "name", name );
            int anInt = parser.readInt( "int" );
            log( "int", anInt );
            long aLong = parser.readLong( "long" );
            log( "long", aLong );
            double aDouble = parser.readDouble( "double" );
            log( "double", aDouble );
            boolean aBoolean = parser.readBoolean( "boolean" );
            log( "boolean", aBoolean );
            String empty = parser.readString( "empty" );
            log( "empty", empty );
            List<String> stringArray = parser.readStringArray( "stringArray" );
            log( "stringArray", stringArray );
            int[] intArrays = parser.readIntArray( "intArray" );
            log( "intArray", intArrays[ 0 ] );
            long[] longArrays = parser.readLongArray( "longArray" );
            log( "longArray", longArrays[ 0 ] );
            double[] doubleArrays = parser.readDoubleArray( "doubleArray" );
            log( "doubleArray", doubleArrays[ 0 ] );
            boolean[] booleanArrays = parser.readBooleanArray( "booleanArray" );
            log( "booleanArray", booleanArrays[ 1 ] );
            parser.readObject( "object" );
            String name1 = parser.readString( "name" );
            log( "name", name1 );
            int age = parser.readInt( "age" );
            log( "age", age );
            parser.endObject();
            parser.readArray( "objectArray" );
            for( int i = 0; i < 3; i++ ) {
                  parser.beginObject();
                  String name2 = parser.readString( "name" );
                  log( "name", name2 );
                  int age1 = parser.readInt( "age" );
                  log( "age", age1 );
                  parser.endObject();
            }
            parser.endArray();
            parser.finish();
      }

      private void testBuilder ( ) throws IOException {

            JsonParser builder = new JsonParser();

            builder.start( new StringReader( GankJson.JSON ) );
            boolean error = builder.readBoolean( "error", false );
            log( "error", error );
            builder.readArray( "results" );

            for( int i = 0; i < 2; i++ ) {

                  builder.beginObject();
                  String id = builder.readString( "_id" );
                  log( "_id", id );
                  String createdAt = builder.readString( "createdAt" );
                  log( "createAt", createdAt );
                  String desc = builder.readString( "desc" );
                  log( "desc", desc );
                  List<String> images = builder.readStringArray( "images" );
                  log( "images", images );
                  String publishedAt = builder.readString( "publishedAt" );
                  log( "publishedAt", publishedAt );
                  String source = builder.readString( "source" );
                  log( "source", source );
                  String type = builder.readString( "type" );
                  log( "type", type );
                  String url = builder.readString( "url" );
                  log( "url", url );
                  boolean used = builder.readBoolean( "used", true );
                  log( "used", used );
                  String who = builder.readString( "who" );
                  log( "who", who );
                  builder.endObject();
            }
            builder.endArray();
            builder.finish();
      }
}
