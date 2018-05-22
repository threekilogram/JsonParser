package com.example.jsonparser;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.IOException;
import java.io.Reader;

/**
 * @author wuxio 2018-05-14:9:47
 */
public class JsonParserV2 {

    //============================ debug 操作 ============================

    private static final String TAG = "JsonParser";

    /**
     * 用于设置是否开启调试
     */
    static  boolean        DEBUG;
    private ValueContainer mValueContainer;


    /**
     * @param ifDebug : true: 解析时将会输出log
     */
    public static void debug(boolean ifDebug) {

        JsonParserV2.DEBUG = ifDebug;
    }

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
    /**
     * 记录上一次操作,需要再解析时分析操作
     */
    private int mLastToken;

    //============================ construct ============================


    public JsonParserV2() {

        mNumberType = Node.VALUE_NUMBER_DOUBLE;
    }

    //============================ 配置使用什么类型保存数字类型 ============================

    private int mNumberType;


    public void setNumberType(@Node.NumberType int numberType) {

        this.mNumberType = numberType;
    }

    //============================ 当node读取数字类型的值失败的时候返回的值 ============================

    /**
     * 当node读取数字类型的值失败的时候返回的值,{@link Node#getInt()}
     */
    public static int errorNumber = -120;


    public static void setErrorNumber(int errorNumber) {

        errorNumber = errorNumber;
    }

    //============================ valueContainer ============================

    //============================ Node ============================

    private Node mCurrentNode;

    //============================ 解析Json ============================


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

        jsonReader.setLenient(lenient);

        try {

            JsonToken peek = jsonReader.peek();
            while (peek != JsonToken.END_DOCUMENT) {

                int lastToken = mLastToken;

                switch (peek) {

                    case BEGIN_OBJECT:

                        /* BEGIN_OBJECT 主要工作: 1.初始化; 2.为当前的节点添加Object子节点 */

                        jsonReader.beginObject();

                        if (lastToken == 0) {

                            /* 开始解析 */

                            if (DEBUG) {
                                Log.i(TAG, "BEGIN_JSON");
                            }

                            /* 初始化一个json value容器,用来管理json的key对应的值 */

                            mValueContainer = new ValueContainer();

                            /* 初始化一个node,她的类型是类型是NODE_OBJECT
                            对应的值是ObjectNodeTree,该ObjectNodeTree对应整个Json */

                            mCurrentNode = new Node(mValueContainer);
                            ObjectNodeTree nodeTree = new ObjectNodeTree();
                            mCurrentNode.asObject(nodeTree);
                        }

                        if (DEBUG) {
                            Log.i(TAG, "BEGIN_OBJECT");
                        }

                        if (lastToken == TOKEN_NAME) {

                            /* 上一个操作是TOKEN_NAME,现在是BEGIN_OBJECT,说明需要一个与当前node同级的object node */

                        }

                        mLastToken = TOKEN_BEGIN_OBJECT;
                        break;

                    case BEGIN_ARRAY:

                        jsonReader.beginArray();
                        if (DEBUG) {
                            Log.i(TAG, "BEGIN_ARRAY_NewArrayRequire :");
                        }

                        mLastToken = TOKEN_BEGIN_ARRAY;
                        break;

                    case NAME:

                        String currentNodeName = jsonReader.nextName();
                        if (DEBUG) {
                            Log.i(TAG, "parseToName: " + currentNodeName);
                        }

                        if (lastToken == TOKEN_BEGIN_OBJECT) {

                            /* 从 BEGIN_OBJECT 转到 NAME, 说明 BEGIN_OBJECT 创建的节点需要添加子节点 */
                            ObjectNodeTree nodeTree = mCurrentNode.getObject();
                            Node node = new Node(mValueContainer);
                            nodeTree.addNode(currentNodeName, node);

                            /* name 肯定对应一个值/一个json object/一个json数组,
                            所以需要将当前节点指向新创建的node,以便后续对该节点操作 */

                            mCurrentNode = node;
                        }

                        mLastToken = TOKEN_NAME;
                        break;

                    /* consume */

                    case NULL:

                        jsonReader.nextNull();
                        if (DEBUG) {
                            Log.i(TAG, "parseToValue: null");
                        }

                        mCurrentNode.asNull();

                        mLastToken = TOKEN_VALUE;
                        break;

                    case STRING:

                        String string = jsonReader.nextString();
                        if (DEBUG) {
                            Log.i(TAG, "parseToValue:String: " + string);
                        }

                        mCurrentNode.asString(string);

                        mLastToken = TOKEN_VALUE;
                        break;

                    case BOOLEAN:

                        boolean booleanValue = jsonReader.nextBoolean();
                        if (DEBUG) {
                            Log.i(TAG, "parseToValue:boolean: " + booleanValue);
                        }

                        mCurrentNode.asBoolean(booleanValue);

                        mLastToken = TOKEN_VALUE;
                        break;

                    case NUMBER:

                        jsonReader.nextDouble();
                        if (DEBUG) {
                            Log.i(TAG, "parseToValue:number: ");
                        }

                        mLastToken = TOKEN_VALUE;
                        break;

                    case END_ARRAY:

                        jsonReader.endArray();
                        if (DEBUG) {
                            Log.i(TAG, "END_ARRAY");
                        }

                        mLastToken = TOKEN_END_ARRAY;
                        break;

                    case END_OBJECT:

                        jsonReader.endObject();
                        if (DEBUG) {
                            Log.i(TAG, "END_OBJECT");
                        }

                        mLastToken = TOKEN_END_OBJECT;
                        break;

                    default:
                        break;
                }

                peek = jsonReader.peek();
            }

        } catch (IOException e) {

            if (DEBUG) {

                throw new RuntimeException(e);
            } else {

                e.printStackTrace();
            }

        } finally {

            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (DEBUG) {
            Log.i(TAG, "parse:" + "PARSE_TO_END");
        }
    }
}
