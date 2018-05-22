package com.example.jsonparser;

/**
 * @author wuxio 2018-05-22:8:39
 */

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.JsonReader;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 节点,用来保存节点值信息
 *
 * @author wuxio
 */
public class Node {

    /**
     * node 的值是number类型时的标记
     */
    static final int VALUE_NUMBER_INT    = 101;
    static final int VALUE_NUMBER_LONG   = 102;
    static final int VALUE_NUMBER_FLOAT  = 103;
    static final int VALUE_NUMBER_DOUBLE = 104;
    /**
     * node 的值是String类型时的标记
     */
    static final int VALUE_STRING        = 105;
    /**
     * node 的值是String类型时的标记
     */
    static final int VALUE_NULL          = 106;
    /**
     * node 的值是boolean类型时的标记
     */
    static final int VALUE_BOOLEAN       = 107;
    /**
     * node 的值是ObjectNode类型时的标记
     */
    static final int NODE_OBJECT         = 11;
    static final int NODE_ARRAY          = 12;

    /**
     * 定义nodeType范围
     */
    @IntDef({
            VALUE_NUMBER_INT,
            VALUE_NUMBER_LONG,
            VALUE_NUMBER_FLOAT,
            VALUE_NUMBER_DOUBLE,
            VALUE_STRING,
            VALUE_NULL,
            VALUE_BOOLEAN,
            NODE_OBJECT,
            NODE_ARRAY})
    @Retention(RetentionPolicy.SOURCE)
    @interface NodeTypes {
    }

    /**
     * 修饰{@link #mType}
     */
    @IntDef({VALUE_NUMBER_INT,
            VALUE_NUMBER_LONG,
            VALUE_NUMBER_FLOAT,
            VALUE_NUMBER_DOUBLE})
    @interface NumberType {

    }

    /**
     * 节点类型,根据json中的key对应的值来区分
     *
     * <br/>
     * 如果key对应的是number类型,该节点类型将是{@link #VALUE_NUMBER_INT},{@link #VALUE_NUMBER_LONG},
     * {@link #VALUE_NUMBER_FLOAT},{@link #VALUE_NUMBER_DOUBLE}其中之一,
     * 具体由{@link JsonParserV2#setNumberType(int)}决定
     *
     * <br/>
     * 如果key对应的是json string值,该节点类型将是{@link #VALUE_STRING}
     *
     * <br/>
     * 如果key对应的是json null值,该节点类型将是{@link #VALUE_NULL}
     *
     * <br/>
     * 如果key对应的是json boolean值,该节点类型将是{@link #NODE_OBJECT}
     *
     * <br/>
     * 如果key对应的是json Object类型,该节点类型将是{@link #NODE_OBJECT}
     *
     * <br/>
     * 如果key对应的是json array类型,该节点类型将是{@link #NODE_ARRAY}
     */
    @NodeTypes
    private int mType;
    /**
     * 节点值在{@link ValueContainer}中的索引
     */
    private int mValueIndex = -1;

    /**
     * 该类管理整个json的值,包括json number值/json Object/ json array /string
     */
    private ValueContainer mValueContainer;


    Node(ValueContainer valueContainer) {

        mValueContainer = valueContainer;
    }


    /**
     * 该节点对应 json Object,将一个{@link ObjectNodeTree} 作为该节点的值
     *
     * @param objectNode 该节点的值
     */
    void asObject(ObjectNodeTree objectNode) {

        mValueIndex = mValueContainer.saveNodeTree(objectNode);
        mType = NODE_OBJECT;
    }


    /**
     * @return 如果该节点对应 jsonObject; 那么返回 {@link ObjectNodeTree} ,如果不是对应jsonObject,返回null
     */
    public ObjectNodeTree getObject() {

        if (mType == NODE_OBJECT) {

            try {

                return (ObjectNodeTree) mValueContainer.getNodeTree(mValueIndex);
            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("Object");
            }

            return null;
        }
    }


    /**
     * 该节点对应 json array ,将一个{@link ArrayNodeTree} 作为该节点的值
     *
     * @param arrayNodeTree 该节点的值
     */
    void asArray(ArrayNodeTree arrayNodeTree) {

        mValueIndex = mValueContainer.saveNodeTree(arrayNodeTree);
        mType = NODE_ARRAY;
    }


    /**
     * @return 如果该节点对应 json Array; 那么返回 {@link ArrayNodeTree} ,如果不是对应json Array,返回null
     */
    public ArrayNodeTree getArray() {

        if (mType == NODE_ARRAY) {

            try {

                return (ArrayNodeTree) mValueContainer.getNodeTree(mValueIndex);
            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("Array");
            }

            return null;
        }
    }


    /**
     * 该节点对应的值为string ,将一个{@code value} 作为该节点的值
     *
     * @param value 该节点的值
     */
    void asString(String value) {

        mValueIndex = mValueContainer.saveStringValue(value);
        mType = VALUE_STRING;
    }


    /**
     * @return 如果该节点对应 string; 那么返回该值 ,如果不是,返回null
     */
    public String getString() {

        if (mType == VALUE_STRING) {

            try {

                return mValueContainer.getStringValue(mValueIndex);

            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("String");
            }

            return null;
        }
    }


    /**
     * 设置当前节点为null节点,对应 json 中 值为null
     */
    void asNull() {

        mValueIndex = -1;
        mType = VALUE_NULL;
    }


    /**
     * @return true:节点值为null
     */
    public boolean isNull() {

        return mType == VALUE_NULL;

    }


    /**
     * 该节点对应的值为boolean类型 ,将一个{@code value} 作为该节点的值
     *
     * @param value 该节点的值
     */
    void asBoolean(boolean value) {

        if (value) {

            mValueIndex = -1;
        } else {

            mValueIndex = -2;
        }

        mType = VALUE_BOOLEAN;
    }


    /**
     * @return 如果该节点对应 boolean 值; 那么返回该值 ,如果不是,返回false,
     *
     * <br/>
     * 需要自己调用时明确该节点对应的是boolean值,否则容易读取错误的 false 值
     */
    public boolean getBoolean() {

        if (mType == VALUE_BOOLEAN) {

            return mValueIndex == -1;
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("boolean");
            }

            return false;
        }
    }


    /**
     * 简化方法,用于{@link JsonParserV2}读取并设置number值给节点
     *
     * @param numberType 用于判断如何保存number
     * @param jsonReader reader
     * @throws IOException exception
     */
    void asNumber(@NumberType int numberType, JsonReader jsonReader) throws IOException {

        switch (numberType) {

            case VALUE_NUMBER_INT:
                int i = jsonReader.nextInt();
                asInt(i);
                break;

            case VALUE_NUMBER_LONG:
                long j = jsonReader.nextLong();
                asLong(j);
                break;

            case VALUE_NUMBER_FLOAT:
                float k = (float) jsonReader.nextDouble();
                asFloat(k);
                break;

            case VALUE_NUMBER_DOUBLE:
                double l = jsonReader.nextDouble();
                asDouble(l);
                break;

            default:
                double m = jsonReader.nextDouble();
                asDouble(m);
                break;
        }
    }


    /**
     * 该节点对应的值为json number类型 ,将该值保存为int类型
     *
     * @param value 该节点的值
     */
    void asInt(int value) {

        mValueIndex = mValueContainer.saveIntValue(value);
        mType = VALUE_NUMBER_INT;
    }


    /**
     * 如果该节点对应 json number 类型; 那么返回该值,如果类型不对或者没有该值将会返回{@link JsonParserV2#errorNumber}
     *
     * @return 该节点对应的 int 值
     */
    public int getInt() {

        if (mType == VALUE_NUMBER_INT) {

            try {

                return mValueContainer.getIntValue(mValueIndex);
            } catch (Exception e) {

                return JsonParserV2.errorNumber;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("boolean");
            }

            return JsonParserV2.errorNumber;
        }
    }


    /**
     * 该节点对应的值为json number类型 ,将该值保存为long类型
     *
     * @param value 该节点的值
     */
    void asLong(long value) {

        mValueIndex = mValueContainer.saveLongValue(value);
        mType = VALUE_NUMBER_LONG;
    }


    /**
     * 如果该节点对应 json number 类型; 那么返回该值,如果类型不对或者没有该值将会返回{@link JsonParserV2#errorNumber}
     *
     * @return 该节点对应的 long 值
     */
    public long getLong() {

        if (mType == VALUE_NUMBER_LONG) {

            try {

                return mValueContainer.getLongValue(mValueIndex);
            } catch (Exception e) {

                return JsonParserV2.errorNumber;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("long");
            }

            return JsonParserV2.errorNumber;
        }
    }


    /**
     * 该节点对应的值为json number类型 ,将该值保存为float类型
     *
     * @param value 该节点的值
     */
    void asFloat(float value) {

        mValueIndex = mValueContainer.saveFloatValue(value);
        mType = VALUE_NUMBER_FLOAT;
    }


    /**
     * 如果该节点对应 json number 类型; 那么返回该值,如果类型不对或者没有该值将会返回{@link JsonParserV2#errorNumber}
     *
     * @return 该节点对应的 float 值
     */
    public float getFloat() {

        if (mType == VALUE_NUMBER_FLOAT) {

            try {

                return mValueContainer.getFloatValue(mValueIndex);
            } catch (Exception e) {

                return JsonParserV2.errorNumber;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("float");
            }

            return JsonParserV2.errorNumber;
        }
    }


    /**
     * 该节点对应的值为json number类型 ,将该值保存为double类型,如果类型不对将会返回{@link JsonParserV2#errorNumber}
     *
     * @param value 该节点的值
     */
    void asDouble(double value) {

        mValueIndex = mValueContainer.saveDoubleValue(value);
        mType = VALUE_NUMBER_DOUBLE;
    }


    /**
     * 如果该节点对应 json number 类型; 那么返回该值,如果类型不对或者没有该值将会返回{@link JsonParserV2#errorNumber}
     *
     * @return 该节点对应的 double 值
     */
    public double getDouble() {

        if (mType == VALUE_NUMBER_DOUBLE) {

            try {

                return mValueContainer.getDoubleValue(mValueIndex);

            } catch (Exception e) {

                return JsonParserV2.errorNumber;
            }
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("double");
            }

            return JsonParserV2.errorNumber;
        }
    }


    /**
     * 用于 debug 时,抛出异常
     */
    private void throwTypeNotMatchException(String requireType) {

        throw new RuntimeException("Type not Match Exception : current node mType is " +
                getTypeString() +
                " you required is " + requireType);

    }


    @Override
    public String toString() {

        String typeText = getTypeString();

        return " node: " + " mType: " + typeText + " " + getNodeValue();
    }


    @NonNull
    private String getTypeString() {

        String typeText = null;

        switch (mType) {
            case NODE_OBJECT:
                typeText = "Object";
                break;
            case NODE_ARRAY:
                typeText = "Array";
                break;
            case VALUE_NULL:
                typeText = "null";
                break;
            case VALUE_BOOLEAN:
                typeText = "boolean";
                break;
            case VALUE_STRING:
                typeText = "string";
                break;
            case VALUE_NUMBER_DOUBLE:
                typeText = "double";
                break;
            case VALUE_NUMBER_FLOAT:
                typeText = "float";
                break;
            case VALUE_NUMBER_INT:
                typeText = "int";
                break;
            case VALUE_NUMBER_LONG:
                typeText = "long";
                break;
            default:
                typeText = "Undefined Type";
                break;
        }
        return typeText;
    }


    private String getNodeValue() {

        String valueInString = null;

        switch (mType) {
            case NODE_OBJECT:
                valueInString = getObject().toString();
                break;
            case NODE_ARRAY:
                valueInString = getArray().toString();
                break;
            case VALUE_NULL:
                valueInString = "null";
                break;
            case VALUE_BOOLEAN:
                valueInString = String.valueOf(getBoolean());
                break;
            case VALUE_STRING:
                valueInString = getString();
                break;
            case VALUE_NUMBER_DOUBLE:
                valueInString = String.valueOf(getDouble());
                break;
            case VALUE_NUMBER_FLOAT:
                valueInString = String.valueOf(getFloat());
                break;
            case VALUE_NUMBER_INT:
                valueInString = String.valueOf(getInt());
                break;
            case VALUE_NUMBER_LONG:
                valueInString = String.valueOf(getLong());
                ;
                break;
            default:
                valueInString = "Undefined Value";
                break;
        }

        return valueInString;
    }
}
