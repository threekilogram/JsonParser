package com.example.jsonparser;

import android.support.annotation.IntDef;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxio 2018-05-14:9:47
 */
public class JsonParserV1 {

    private static final String TAG = "JsonParserV1";

    /**
     * 用于设置是否开启调试
     */
    private static boolean LOG;


    /**
     * @param printLog : true: 解析时将会输出log
     */
    public static void log(boolean printLog) {

        JsonParserV1.LOG = printLog;
    }


    /**
     * 用于定位元素,可以根据list中的node定位到元素
     */
    private List< Node > mNodeTree = new ArrayList<>();


    /**
     * 定义操作
     */
    private static final int TOKEN_VALUE        = 1;
    private static final int TOKEN_NAME         = 3;
    private static final int TOKEN_BEGIN_OBJECT = 5;
    private static final int TOKEN_END_OBJECT   = 7;
    private static final int TOKEN_BEGIN_ARRAY  = 11;
    private static final int TOKEN_END_ARRAY    = 13;
    /**
     * 记录上一次操作,需要再解析时分析操作
     */
    private int mLastToken;

    /**
     * 记录读取的值,不断复用
     */
    private ValueHolder mValueHolder = new ValueHolder();


    /**
     * 常量,node mType
     */
    public static final int VALUE  = 17;
    public static final int OBJECT = 19;
    public static final int ARRAY  = 21;

    /**
     * 定义nodeType范围
     */
    @IntDef({VALUE, OBJECT, ARRAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NodeTypes {
    }

    /**
     * 标记array是什么类型,true:array元素全是object,false:array元素全部是值类型
     */
    private boolean isObjectArray;


    /**
     * 使用{@link #create(OnParseListener)}创建,不要自己直接创建
     *
     * @param onParseListener 监听过程
     */
    private JsonParserV1(OnParseListener onParseListener) {

        mOnParseListener = onParseListener;
    }


    /**
     * @param reader 解析json
     */
    public void parse(Reader reader) {

        parse(reader, false);
    }


    /**
     * @param reader 解析格式不标准的json
     */
    public void parseLenient(Reader reader) {

        parse(reader, true);
    }


    private void parse(Reader reader, boolean lenient) {

        JsonReader jsonReader = new JsonReader(reader);

        if (lenient) {
            jsonReader.setLenient(true);
        }

        try {

            JsonToken peek = jsonReader.peek();
            while (peek != JsonToken.END_DOCUMENT) {

                Node node = null;

                switch (peek) {

                    case BEGIN_OBJECT:

                        jsonReader.beginObject();

                        if (mLastToken == 0) {

                            mOnParseListener.onBegin();

                            if (LOG) {
                                Log.i(TAG, "BEGIN_PARSE");
                            }

                        } else if (mLastToken == TOKEN_NAME) {

                            node = mNodeTree.get(mNodeTree.size() - 1);
                            node.type = OBJECT;

                        } else if (mLastToken == TOKEN_BEGIN_ARRAY || mLastToken == TOKEN_END_OBJECT) {

                            isObjectArray = true;

                            int size = mNodeTree.size();
                            if (size > 0) {
                                node = mNodeTree.get(size - 1);
                                if (node.type == ARRAY) {
                                    node.index += 1;
                                }
                            }
                        }

                        if (node != null) {
                            int type = node.type;
                            if (type == OBJECT) {

                                mOnParseListener.onNewObjectRequire(mNodeTree);

                                if (LOG) {
                                    Log.i(TAG, "parse:" + "NewObjectRequire :" + mNodeTree);
                                }

                            } else if (type == ARRAY) {

                                mOnParseListener.onNewArrayElementRequire(mNodeTree);

                                if (LOG) {
                                    Log.i(TAG, "parse:" + "NewArrayElementRequire :" + mNodeTree);
                                }

                            }
                        }

                        mLastToken = TOKEN_BEGIN_OBJECT;

                        if (LOG) {
                            Log.i(TAG, "BEGIN_OBJECT");
                        }

                        break;

                    case BEGIN_ARRAY:
                        jsonReader.beginArray();

                        if (mLastToken == TOKEN_NAME) {
                            node = mNodeTree.get(mNodeTree.size() - 1);
                            node.type = ARRAY;
                        }

                        mLastToken = TOKEN_BEGIN_ARRAY;

                        mOnParseListener.onNewArrayRequire(mNodeTree);

                        if (LOG) {
                            Log.i(TAG, "BEGIN_ARRAY_NewArrayRequire :" + mNodeTree);
                        }

                        break;

                    case NAME:
                        String currentNodeName = jsonReader.nextName();

                        if (mLastToken == TOKEN_BEGIN_OBJECT) {

                            mNodeTree.add(new Node());

                        }

                        mNodeTree.get(mNodeTree.size() - 1).name = currentNodeName;

                        mLastToken = TOKEN_NAME;

                        if (LOG) {
                            Log.i(TAG, "parseToName: " + currentNodeName);
                        }

                        break;

                    /* consume */

                    case NULL:
                        jsonReader.nextNull();
                        setNullValue(mValueHolder);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;

                        if (LOG) {
                            Log.i(TAG, "parseToValue: "
                                    + mNodeTree
                                    + " key: " + mNodeTree.get(mNodeTree.size() - 1).name
                                    + " value: " + null
                            );
                        }

                        break;

                    case STRING:
                        String string = jsonReader.nextString();
                        setStringValue(mValueHolder, string);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;

                        if (LOG) {
                            Log.i(TAG, "parseToValue: "
                                    + mNodeTree
                                    + " key: " + mNodeTree.get(mNodeTree.size() - 1).name
                                    + " value: " + string
                            );
                        }

                        break;

                    case BOOLEAN:
                        boolean booleanValue = jsonReader.nextBoolean();
                        setBooleanValue(mValueHolder, booleanValue);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;

                        if (LOG) {
                            Log.i(TAG, "parseToValue: "
                                    + mNodeTree
                                    + " key: " + mNodeTree.get(mNodeTree.size() - 1).name
                                    + " value: " + booleanValue
                            );
                        }

                        break;

                    case NUMBER:
                        double doubleValue = jsonReader.nextDouble();
                        setNumberValue(mValueHolder, doubleValue);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;

                        if (LOG) {
                            Log.i(TAG, "parseToValue: "
                                    + mNodeTree
                                    + " key: " + mNodeTree.get(mNodeTree.size() - 1).name
                                    + " value: " + doubleValue
                            );
                        }

                        break;

                    case END_ARRAY:
                        jsonReader.endArray();

                        mNodeTree.get(mNodeTree.size() - 1).index = -1;

                        isObjectArray = false;

                        mLastToken = TOKEN_END_ARRAY;

                        if (LOG) {
                            Log.i(TAG, "END_ARRAY");
                        }

                        break;

                    case END_OBJECT:
                        jsonReader.endObject();

                        mNodeTree.remove(mNodeTree.size() - 1);

                        mLastToken = TOKEN_END_OBJECT;

                        if (LOG) {
                            Log.i(TAG, "END_OBJECT");
                        }

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

        mOnParseListener.onFinished();
        if (LOG) {
            Log.i(TAG, "parse:" + "PARSE_TO_END");
        }
    }


    private void parseToValue() {

        Node node = mNodeTree.get(mNodeTree.size() - 1);

        if (mLastToken == TOKEN_NAME) {

            node.type = VALUE;

        } else if (mLastToken == TOKEN_VALUE || mLastToken == TOKEN_BEGIN_ARRAY) {
            node.index += 1;
            mOnParseListener.onNewArrayElementRequire(mNodeTree);
        }

        mOnParseListener.onParseTo(mNodeTree, node.name, mValueHolder);
    }

    //============================ node ============================

    /**
     * 节点,用来定位元素位置
     */
    public static class Node {

        public String name;
        @NodeTypes
        public int    type;
        private int index = -1;


        /**
         * @return array index, or -1 if node is not {@link #ARRAY} mType
         */
        public int getIndex() {

            if (type == ARRAY) {

                return index;
            }
            return -1;
        }


        @Override
        public String toString() {

            String typeName = null;

            if (type == VALUE) {

                typeName = "VALUE_NUMBER";

            } else if (type == OBJECT) {

                typeName = "OBJECT";

            } else if (type == ARRAY) {

                typeName = "ARRAY";

            }

            return "Node{" +
                    "mName='" + name + '\'' +
                    ", mType=" + typeName +
                    ", index=" + index +
                    '}';
        }
    }

    //============================ 监听 ============================

    private OnParseListener mOnParseListener;

    /**
     * 监听解析过程
     */
    public interface OnParseListener {

        /**
         * 开始解析
         */
        default void onBegin() {

        }

        /**
         * 监听解析过程,当解析到一个value时回调该方法
         *
         * @param nodes       节点信息,用来定位元素位置
         * @param key         节点key
         * @param valueHolder 节点value
         */
        void onParseTo(List< Node > nodes, String key, ValueHolder valueHolder);


        /**
         * 当新解析到一个Object对象时回调该方法
         *
         * @param nodes object位置
         */
        default void onNewObjectRequire(List< Node > nodes) {

        }

        /**
         * 当解析到一个新的数组时,回调该方法
         *
         * @param nodes object 位置
         */
        default void onNewArrayRequire(List< Node > nodes) {

        }

        /**
         * 当新解析到一个Object对象时回调该方法
         *
         * @param nodes object位置
         */
        default void onNewArrayElementRequire(List< Node > nodes) {

        }

        /**
         * 解析完成
         */
        default void onFinished() {

        }
    }

    //============================ set Holder value ============================


    private static void setStringValue(ValueHolder valueHolder, String value) {

        valueHolder.init();
        valueHolder.setStringValue(value);
        valueHolder.setJsonToken(JsonToken.STRING);

    }


    private static void setNullValue(ValueHolder valueHolder) {

        valueHolder.init();
        valueHolder.setNull();
        valueHolder.setJsonToken(JsonToken.NULL);

    }


    private static void setNumberValue(ValueHolder valueHolder, double value) {

        valueHolder.init();
        valueHolder.setDoubleValue(value);
        valueHolder.setJsonToken(JsonToken.NUMBER);

    }


    private static void setBooleanValue(ValueHolder valueHolder, boolean value) {

        valueHolder.init();
        valueHolder.setBooleanValue(value);
        valueHolder.setJsonToken(JsonToken.BOOLEAN);

    }

    //============================ 工具方法 ============================


    public static JsonParserV1 create(OnParseListener onParseListener) {

        return new JsonParserV1(onParseListener);
    }
}
