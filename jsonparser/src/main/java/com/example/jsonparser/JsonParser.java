package com.example.jsonparser;

import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过{@link JsonReader}各个步骤的分解,达到解析json的目的
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-08-12
 * @time: 10:42
 */
public class JsonParser {

      /**
       * 解析器
       */
      private JsonReader mJsonReader;
      /**
       * 没有消耗的node的名字
       */
      private String     mNotConsumedNodeName;

      public JsonParser ( ) { }

      public JsonToken peek ( ) throws IOException {

            return mJsonReader.peek();
      }

      /**
       * 开始解析
       *
       * @param reader 读取json的reader
       */
      public void start ( Reader reader ) throws IOException {

            mJsonReader = new JsonReader( reader );
            beginObject();
      }

      /**
       * 开始解析一个json类,和{@link #endObject()}配合使用
       */
      public void beginObject ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.BEGIN_OBJECT ) {

                  mJsonReader.beginObject();
            }

            mNotConsumedNodeName = null;
      }

      /**
       * 结束解析
       */
      public void finish ( ) throws IOException {

            JsonReader jsonReader = mJsonReader;

            JsonToken peek = jsonReader.peek();
            while( peek != JsonToken.END_DOCUMENT ) {

                  switch( peek ) {

                        case BEGIN_OBJECT:
                              jsonReader.beginObject();
                              break;

                        case BEGIN_ARRAY:
                              jsonReader.beginArray();
                              break;

                        case NAME:
                              jsonReader.nextName();
                              break;

                        case NULL:
                              jsonReader.nextNull();
                              break;

                        case STRING:
                              jsonReader.nextString();
                              break;

                        case BOOLEAN:
                              jsonReader.nextBoolean();
                              break;

                        case NUMBER:
                              jsonReader.nextDouble();
                              break;

                        case END_ARRAY:
                              jsonReader.endArray();
                              break;

                        case END_OBJECT:
                              jsonReader.endObject();
                              break;

                        default:
                              break;
                  }

                  peek = jsonReader.peek();
            }

            try {

                  mJsonReader.close();
                  mJsonReader = null;
            } catch(IOException e) {

                  throw e;
            }
      }

      /**
       * 直接跳到节点位置,然后可以使用{@link #readObject(String)}读取
       *
       * @param nodeName 节点名
       */
      public void skipToObject ( String nodeName ) throws IOException {

            skipToNode( nodeName, JsonToken.BEGIN_OBJECT );
      }

      /**
       * 工具方法
       */
      private void skipToNode ( final String nodeName, final JsonToken tokenWhich )
          throws IOException {

            JsonReader jsonReader = mJsonReader;

            JsonToken peek = jsonReader.peek();
            while( peek != JsonToken.END_DOCUMENT ) {

                  switch( peek ) {

                        case BEGIN_OBJECT:
                              jsonReader.beginObject();
                              break;

                        case BEGIN_ARRAY:
                              jsonReader.beginArray();
                              break;

                        case NAME:
                              String name = jsonReader.nextName();
                              if( nodeName.equals( name ) ) {

                                    JsonToken token = jsonReader.peek();
                                    if( token == tokenWhich ) {
                                          mNotConsumedNodeName = name;
                                          return;
                                    }
                              }
                              break;

                        case NULL:
                              jsonReader.nextNull();
                              break;

                        case STRING:
                              jsonReader.nextString();
                              break;

                        case BOOLEAN:
                              jsonReader.nextBoolean();
                              break;

                        case NUMBER:
                              jsonReader.nextDouble();
                              break;

                        case END_ARRAY:
                              jsonReader.endArray();
                              break;

                        case END_OBJECT:
                              jsonReader.endObject();
                              break;

                        default:
                              break;
                  }

                  peek = jsonReader.peek();
            }
      }

      /**
       * 开始解析一个json object类型,和{@link #endObject()}配合使用
       *
       * @param nodeName node name
       */
      public void readObject ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();
                  if( nodeName.equals( name ) ) {

                        mJsonReader.beginObject();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_OBJECT ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        mJsonReader.beginObject();
                  }
            }
      }

      /**
       * 结束一个json类的解析
       */
      public void endObject ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.END_OBJECT ) {

                  mJsonReader.endObject();
            }

            mNotConsumedNodeName = null;
      }

      /**
       * 直接结束一个json类的解析
       */
      public void skipToEndObject ( ) throws IOException {

            JsonReader jsonReader = mJsonReader;

            JsonToken peek = jsonReader.peek();
            int count = 1;
            while( peek != JsonToken.END_DOCUMENT ) {

                  switch( peek ) {

                        case BEGIN_OBJECT:
                              jsonReader.beginObject();
                              count++;
                              break;

                        case BEGIN_ARRAY:
                              jsonReader.beginArray();
                              break;

                        case NAME:
                              jsonReader.nextName();
                              break;

                        case NULL:
                              jsonReader.nextNull();
                              break;

                        case STRING:
                              jsonReader.nextString();
                              break;

                        case BOOLEAN:
                              jsonReader.nextBoolean();
                              break;

                        case NUMBER:
                              jsonReader.nextDouble();
                              break;

                        case END_ARRAY:
                              jsonReader.endArray();
                              break;

                        case END_OBJECT:
                              jsonReader.endObject();
                              count--;
                              if( count == 0 ) {
                                    return;
                              }
                              break;

                        default:
                              break;
                  }

                  peek = jsonReader.peek();
            }

            mNotConsumedNodeName = null;
      }

      /**
       * 直接跳到节点位置,之后可以开始
       * {@link #readArray(String)}
       * {@link #readStringArray(String)}
       * {@link #readBooleanArray(String)}
       * {@link #readDoubleArray(String)}
       * {@link #readIntArray(String)}
       * {@link #readLongArray(String)}
       * 解析Json数组
       *
       * @param nodeName 节点名称
       */
      public void skipToArray ( String nodeName ) throws IOException {

            skipToNode( nodeName, JsonToken.BEGIN_ARRAY );
      }

      /**
       * 开始解析 Json 类数组,一般后面使用{@link #beginObject()}开始循环解析,和{@link #endArray()}配合使用
       *
       * @param nodeName 节点名称
       */
      public void readArray ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        mJsonReader.beginArray();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_ARRAY ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        mJsonReader.beginArray();
                  }
            }
      }

      /**
       * 结束json数组
       */
      public void endArray ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.END_ARRAY ) {

                  mJsonReader.endArray();
            }
      }

      /**
       * 直接跳到节点位置,然后可以使用{@link #readString(String)}读取
       *
       * @param nodeName 节点名
       */
      public void skipToString ( String nodeName ) throws IOException {

            skipToNode( nodeName, JsonToken.STRING );
      }

      /**
       * 读取该节点对应的string
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回null
       */
      public String readString ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( nodeName.equals( name ) ) {

                        return getString();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.STRING ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getString();
                  }
            }
            return null;
      }

      @Nullable
      private String getString ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NULL ) {

                  mJsonReader.nextNull();
                  return null;
            } else {

                  return mJsonReader.nextString();
            }
      }

      /**
       * 读取该节点对应的json string[]
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回null
       */
      public List<String> readStringArray ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getStrings();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_ARRAY ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getStrings();
                  }
            }

            return null;
      }

      /**
       * 读取string[]值
       */
      @Nullable
      private List<String> getStrings ( ) throws IOException {

            JsonToken peek;
            peek = mJsonReader.peek();
            if( peek == JsonToken.NULL ) {

                  mJsonReader.nextNull();
                  return null;
            } else {

                  mJsonReader.beginArray();

                  JsonToken token = mJsonReader.peek();
                  List<String> result = new ArrayList<>();
                  while( token != JsonToken.END_ARRAY ) {
                        String value = mJsonReader.nextString();
                        result.add( value );
                        token = mJsonReader.peek();
                  }

                  mJsonReader.endArray();

                  return result;
            }
      }

      /**
       * 直接跳到节点位置,然后可以使用
       * {@link #readInt(String)}
       * {@link #readLong(String)}
       * {@link #readDouble(String)}
       * 读取
       *
       * @param nodeName 节点名
       */
      public void skipToNumber ( String nodeName ) throws IOException {

            skipToNode( nodeName, JsonToken.NUMBER );
      }

      /**
       * 读取该节点对应的int,如果没有该节点那么返回0作为结果
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorCode}
       */

      public int readInt ( String nodeName ) throws IOException {

            return readInt( nodeName, 0 );
      }

      /**
       * 读取该节点对应的double
       *
       * @param nodeName 节点名称
       * @param errorCode 如果没有该节点那么返回该值作为结果
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorCode}
       */
      public int readInt ( String nodeName, int errorCode ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getInt( errorCode );
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.NUMBER ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getInt( errorCode );
                  }
            }

            return errorCode;
      }

      /**
       * 读取int
       */
      private int getInt ( int errorCode ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NULL ) {

                  mJsonReader.nextNull();
                  return errorCode;
            } else {

                  return mJsonReader.nextInt();
            }
      }

      /**
       * 读取该节点对应的int[]
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回null
       */
      public int[] readIntArray ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getInts();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_ARRAY ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getInts();
                  }
            }

            return null;
      }

      /**
       * 读取int[]值
       */
      private int[] getInts ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  mJsonReader.nextNull();
                  return null;
            }

            List<Integer> integers = new ArrayList<>();

            mJsonReader.beginArray();

            JsonToken token = mJsonReader.peek();
            while( token != JsonToken.END_ARRAY ) {
                  int value = mJsonReader.nextInt();
                  integers.add( value );
                  token = mJsonReader.peek();
            }

            mJsonReader.endArray();

            final int size = integers.size();
            int[] result = new int[ size ];
            for( int i = 0; i < size; i++ ) {
                  result[ i ] = integers.get( i );
            }

            return result;
      }

      /**
       * 读取该节点对应的double,如果没有该节点那么返回0作为结果
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorCode}
       */
      public long readLong ( String nodeName ) throws IOException {

            return readLong( nodeName, 0 );
      }

      /**
       * 读取该节点对应的double
       *
       * @param nodeName 节点名称
       * @param errorCode 如果没有该节点那么返回该值作为结果
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorCode}
       */
      public long readLong ( String nodeName, int errorCode ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getLong( errorCode );
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.NUMBER ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getLong( errorCode );
                  }
            }

            return errorCode;
      }

      /**
       * 读取一个long节点的值
       *
       * @param errorCode 如果没有该节点返回该值
       *
       * @return 节点的值
       */
      private long getLong ( int errorCode ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            if( peek == JsonToken.NULL ) {
                  mJsonReader.nextNull();
                  return errorCode;
            } else {

                  return mJsonReader.nextLong();
            }
      }

      /**
       * 读取该节点对应的long[]
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回null
       */
      public long[] readLongArray ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getLongs();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_ARRAY ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getLongs();
                  }
            }

            return null;
      }

      /**
       * 读取一个long[]节点的值
       *
       * @return 一个long[]节点的值
       */
      private long[] getLongs ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            if( peek == JsonToken.NULL ) {
                  mJsonReader.nextNull();
                  return null;
            } else {

                  List<Long> longs = new ArrayList<>();

                  mJsonReader.beginArray();

                  JsonToken token = mJsonReader.peek();
                  while( token != JsonToken.END_ARRAY ) {
                        long value = mJsonReader.nextLong();
                        longs.add( value );
                        token = mJsonReader.peek();
                  }

                  mJsonReader.endArray();

                  final int size = longs.size();
                  long[] result = new long[ size ];
                  for( int i = 0; i < size; i++ ) {

                        result[ i ] = longs.get( i );
                  }

                  return result;
            }
      }

      /**
       * 读取该节点对应的double,如果没有该节点那么返回0作为结果
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorCode}
       */
      public double readDouble ( String nodeName ) throws IOException {

            return readDouble( nodeName, 0 );
      }

      /**
       * 读取该节点对应的double
       *
       * @param nodeName 节点名称
       * @param errorCode 如果没有该节点那么返回该值作为结果
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorCode}
       */
      public double readDouble ( String nodeName, int errorCode ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getDouble( errorCode );
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.NUMBER ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getDouble( errorCode );
                  }
            }

            return errorCode;
      }

      /**
       * 一个double节点的值
       *
       * @param errorCode 如果没有该节点返回该值
       */
      private double getDouble ( int errorCode ) throws IOException {

            JsonToken peek;
            peek = mJsonReader.peek();

            if( peek == JsonToken.NULL ) {
                  mJsonReader.nextNull();
                  return errorCode;
            } else {

                  return mJsonReader.nextDouble();
            }
      }

      /**
       * 读取该节点对应的double[]
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回null
       */
      public double[] readDoubleArray ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getDoubles();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_ARRAY ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getDoubles();
                  }
            }

            return null;
      }

      /**
       * @return 一个double[]节点的值
       */
      private double[] getDoubles ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NULL ) {

                  mJsonReader.nextNull();
                  return null;
            } else {

                  List<Double> doubles = new ArrayList<>();

                  mJsonReader.beginArray();

                  JsonToken token = mJsonReader.peek();
                  while( token != JsonToken.END_ARRAY ) {
                        double value = mJsonReader.nextDouble();
                        doubles.add( value );
                        token = mJsonReader.peek();
                  }

                  mJsonReader.endArray();

                  final int size = doubles.size();
                  double[] result = new double[ size ];
                  for( int i = 0; i < size; i++ ) {

                        result[ i ] = doubles.get( i );
                  }

                  return result;
            }
      }

      /**
       * 直接跳到节点位置,然后可以使用{@link #readBoolean(String)}读取
       *
       * @param nodeName 节点名
       */
      public void skipToBoolean ( String nodeName ) throws IOException {

            skipToNode( nodeName, JsonToken.BOOLEAN );
      }

      /**
       * 读取一个boolean节点的值
       *
       * @param nodeName 节点名
       *
       * @return 节点值
       */
      public boolean readBoolean ( String nodeName ) throws IOException {

            return readBoolean( nodeName, false );
      }

      /**
       * 读取该节点对应的double
       *
       * @param nodeName 节点名称
       * @param errorValue 如果没有该节点那么返回该值作为结果
       *
       * @return 节点值, 如果没有该节点返回用户设置的{@code errorValue}
       */
      public boolean readBoolean ( String nodeName, boolean errorValue ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( name.equals( nodeName ) ) {

                        return getBoolean( errorValue );
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BOOLEAN ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getBoolean( errorValue );
                  }
            }

            return errorValue;
      }

      /**
       * @param errorValue 当没有该节点时返回该值
       *
       * @return 一个节点对应的boolean值
       */
      private boolean getBoolean ( boolean errorValue ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NULL ) {

                  mJsonReader.nextNull();
                  return errorValue;
            } else {

                  return mJsonReader.nextBoolean();
            }
      }

      /**
       * 读取该节点对应的double[]
       *
       * @param nodeName 节点名称
       *
       * @return 节点值, 如果没有该节点返回null
       */
      public boolean[] readBooleanArray ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();

                  if( nodeName.equals( name ) ) {

                        return getBooleans();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( peek == JsonToken.BEGIN_ARRAY ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        return getBooleans();
                  }
            }

            return null;
      }

      /**
       * @return 一个boolean[]节点的值
       */
      private boolean[] getBooleans ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();

            if( peek == JsonToken.NULL ) {

                  mJsonReader.nextNull();
                  return null;
            } else {

                  List<Boolean> booleans = new ArrayList<>();

                  mJsonReader.beginArray();

                  JsonToken token = mJsonReader.peek();
                  while( token != JsonToken.END_ARRAY ) {
                        boolean value = mJsonReader.nextBoolean();
                        booleans.add( value );
                        token = mJsonReader.peek();
                  }

                  mJsonReader.endArray();

                  final int size = booleans.size();
                  boolean[] result = new boolean[ size ];
                  for( int i = 0; i < size; i++ ) {

                        result[ i ] = booleans.get( i );
                  }

                  return result;
            }
      }

      /**
       * 跳过一个节点的解析,如果不需要解析该节点直接跳过
       *
       * @param nodeName 节点名称
       */
      public void skipValue ( String nodeName ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            if( peek == JsonToken.NAME ) {

                  String name = mJsonReader.nextName();
                  if( nodeName.equals( name ) ) {

                        mJsonReader.skipValue();
                  } else {

                        mNotConsumedNodeName = name;
                  }
            } else if( isValueToken( peek ) ) {

                  if( nodeName.equals( mNotConsumedNodeName ) ) {

                        mJsonReader.skipValue();
                  }
            }
      }

      /**
       * 判断当前节点是否对应一个value
       *
       * @param token token
       *
       * @return true : 对应 json value
       */
      private boolean isValueToken ( JsonToken token ) {

            return token == JsonToken.STRING
                || token == JsonToken.NUMBER
                || token == JsonToken.BOOLEAN
                || token == JsonToken.NULL
                || token == JsonToken.BEGIN_ARRAY
                || token == JsonToken.BEGIN_OBJECT;
      }

      /**
       * 当读取 json 类数组时,可以使用这个方法,在开始解析数组后{@link #readArray(String)},判断是否有更多元素
       *
       * @return true: 还有json类元素没有读完
       */
      public boolean hasNext ( ) throws IOException {

            JsonToken peek = mJsonReader.peek();
            return peek == JsonToken.BEGIN_OBJECT;
      }
}
