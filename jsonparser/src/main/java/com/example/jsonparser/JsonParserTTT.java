package com.example.jsonparser;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import java.io.IOException;
import java.io.Reader;

/**
 * @author wuxio 2018-05-14:9:47
 */
public class JsonParserTTT {

      private static final String TAG = JsonParserTTT.class.getSimpleName();

      //============================ 上一次操作 ============================

      /**
       * 定义操作
       */
      private static final int TOKEN_VALUE        = 1;
      private static final int TOKEN_NAME         = 3;
      private static final int TOKEN_BEGIN_OBJECT = 5;
      private static final int TOKEN_END_OBJECT   = 7;
      private static final int TOKEN_BEGIN_ARRAY  = 11;
      private static final int TOKEN_END_ARRAY    = 13;

      //============================ construct ============================

      public JsonParserTTT ( ) {

      }

      //============================ 解析Json ============================

      /**
       * @param reader 解析json
       */
      public void parse ( Reader reader ) {

            parse( reader, false );
      }

      private void parse ( Reader reader, boolean lenient ) {

            JsonReader jsonReader = new JsonReader( reader );

            jsonReader.setLenient( lenient );

            try {

                  JsonToken peek = jsonReader.peek();
                  while( peek != JsonToken.END_DOCUMENT ) {

                        switch( peek ) {

                              case BEGIN_OBJECT:

                                    jsonReader.beginObject();
                                    Log.e( TAG, "parse : BEGIN_OBJECT" );
                                    break;

                              case BEGIN_ARRAY:

                                    jsonReader.beginArray();
                                    Log.e( TAG, "parse : BEGIN_ARRAY" );
                                    break;

                              case NAME:

                                    String nextName = jsonReader.nextName();
                                    Log.e( TAG, "parse : node: " + nextName );
                                    break;

                              /* read to a value */

                              case NULL:

                                    jsonReader.nextNull();
                                    Log.e( TAG, "parse : value: " + null );
                                    break;

                              case STRING:
                                    String string = jsonReader.nextString();
                                    Log.e( TAG, "parse : value:" + string );
                                    break;

                              case BOOLEAN:

                                    boolean booleanValue = jsonReader.nextBoolean();
                                    Log.e( TAG, "parse : value:" + booleanValue );
                                    break;

                              case NUMBER:
                                    break;

                              case END_ARRAY:
                                    jsonReader.endArray();
                                    Log.e( TAG, "parse : END_ARRAY" );
                                    break;

                              case END_OBJECT:
                                    jsonReader.endObject();
                                    Log.e( TAG, "parse : endObject" );
                                    break;

                              default:
                                    break;
                        }

                        peek = jsonReader.peek();
                  }
            } catch(IOException e) {

                  e.printStackTrace();
            } finally {

                  try {
                        jsonReader.close();
                  } catch(IOException e) {
                        e.printStackTrace();
                  }
            }
      }

      /**
       * @param reader 解析格式不标准的json
       */
      public void parseLenient ( Reader reader ) {

            parse( reader, true );
      }

      private String lastTokenText ( int lastToken ) {

            String result = null;

            switch( lastToken ) {
                  case TOKEN_BEGIN_OBJECT:
                        result = "BEGIN_OBJECT";
                        break;

                  case TOKEN_END_OBJECT:
                        result = "END_OBJECT";
                        break;

                  case TOKEN_BEGIN_ARRAY:
                        result = "BEGIN_ARRAY";
                        break;

                  case TOKEN_END_ARRAY:
                        result = "END_ARRAY";
                        break;

                  case TOKEN_NAME:
                        result = "NAME";
                        break;

                  case TOKEN_VALUE:
                        result = "VALUE";
                        break;

                  default:
                        result = "NO_TOKEN";
                        break;
            }

            return result;
      }
}
