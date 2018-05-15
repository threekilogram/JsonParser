package com.example.jsonparser;

import android.support.annotation.IntDef;
import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxio 2018-05-14:9:47
 */
public class JsonParser {

    /**
     * 用于定位元素
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
     * 记录上一次操作
     */
    private int mLastToken;

    /**
     * 记录读取的值
     */
    private ValueHolder mValueHolder = new ValueHolder();


    /**
     * 常量,node type
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


    private JsonParser(OnParseListener onParseListener) {

        mOnParseListener = onParseListener;
    }


    public void parse(Reader reader) {

        JsonReader jsonReader = new JsonReader(reader);

        try {

            JsonToken peek = jsonReader.peek();
            while (peek != JsonToken.END_DOCUMENT) {

                switch (peek) {

                    case BEGIN_OBJECT:

                        jsonReader.beginObject();

                        if (mLastToken == TOKEN_NAME) {

                            mNodeTree.get(mNodeTree.size() - 1).type = OBJECT;

                        }

                        mLastToken = TOKEN_BEGIN_OBJECT;
                        break;

                    case BEGIN_ARRAY:
                        jsonReader.beginArray();

                        if (mLastToken == TOKEN_NAME) {
                            Node node = mNodeTree.get(mNodeTree.size() - 1);
                            node.type = ARRAY;
                        }

                        mLastToken = TOKEN_BEGIN_ARRAY;
                        break;

                    case NAME:
                        String currentNodeName = jsonReader.nextName();

                        if (mLastToken == TOKEN_BEGIN_OBJECT) {

                            mNodeTree.add(new Node());

                            int size = mNodeTree.size();
                            if (size >= 2) {
                                Node node = mNodeTree.get(size - 2);
                                if (node.type == ARRAY) {
                                    node.index += 1;
                                }
                            }
                        }
                        mNodeTree.get(mNodeTree.size() - 1).name = currentNodeName;

                        mLastToken = TOKEN_NAME;
                        break;

                    /* consume */

                    case NULL:
                        jsonReader.nextNull();
                        setNullValue(mValueHolder);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case STRING:
                        String string = jsonReader.nextString();
                        setStringValue(mValueHolder, string);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case BOOLEAN:
                        boolean booleanValue = jsonReader.nextBoolean();
                        setBooleanValue(mValueHolder, booleanValue);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case NUMBER:
                        double doubleValue = jsonReader.nextDouble();
                        setNumberValue(mValueHolder, doubleValue);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case END_ARRAY:
                        jsonReader.endArray();

                        if (mLastToken == TOKEN_VALUE) {

                            mNodeTree.get(mNodeTree.size() - 1).index = -1;
                        }

                        mLastToken = TOKEN_END_ARRAY;
                        break;

                    case END_OBJECT:
                        jsonReader.endObject();

                        mNodeTree.remove(mNodeTree.size() - 1);

                        mLastToken = TOKEN_END_OBJECT;
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


    private void parseToValue() {

        Node node = mNodeTree.get(mNodeTree.size() - 1);

        if (mLastToken == TOKEN_NAME) {

            node.type = VALUE;

        } else if (mLastToken == TOKEN_VALUE || mLastToken == TOKEN_BEGIN_ARRAY) {

            node.index += 1;
        }

        mOnParseListener.onParseTo(mNodeTree, node.name, mValueHolder);
    }

    //============================ node ============================

    /**
     * 节点,用来定位元素位置
     */
    private static class Node {

        public String name;
        @NodeTypes
        public int    type;
        private int index = -1;


        /**
         * @return array index, or -1 if node is not {@link #ARRAY} type
         */
        public int getIndex() {

            if (type == ARRAY) {

                return index;
            }
            return -1;
        }


        @Override
        public String toString() {

            return "Node{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
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
         * 监听解析过程,当解析到一个value时回调该方法
         *
         * @param nodes       节点信息,用来定位元素位置
         * @param key         节点key
         * @param valueHolder 节点value
         */
        void onParseTo(List< Node > nodes, String key, ValueHolder valueHolder);

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


    public static JsonParser create(OnParseListener onParseListener) {

        return new JsonParser(onParseListener);
    }
}
