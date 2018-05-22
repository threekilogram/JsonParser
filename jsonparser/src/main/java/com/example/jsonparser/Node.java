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

    @IntDef({VALUE_NUMBER_INT,
            VALUE_NUMBER_LONG,
            VALUE_NUMBER_FLOAT,
            VALUE_NUMBER_DOUBLE})
    @interface NumberType {

    }

    @NodeTypes
    int type;
    int mValueIndex = -1;
    NodeTree parent;

    private ValueContainer mValueContainer;


    void saveObject(ObjectNodeTree objectNode) {

        mValueIndex = mValueContainer.saveNodeTree(objectNode);
        type = NODE_OBJECT;
    }


    public ObjectNodeTree getObject() {

        if (type == NODE_OBJECT) {

            return (ObjectNodeTree) mValueContainer.getNodeTree(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("Object");
            }

            return null;
        }
    }


    void saveArray(ArrayNodeTree arrayNodeTree) {

        mValueIndex = mValueContainer.saveNodeTree(arrayNodeTree);
        type = NODE_ARRAY;
    }


    public ArrayNodeTree getArray() {

        if (type == NODE_ARRAY) {

            return (ArrayNodeTree) mValueContainer.getNodeTree(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("Array");
            }

            return null;
        }
    }


    void saveString(String value) {

        mValueIndex = mValueContainer.saveStringValue(value);
        type = VALUE_STRING;
    }


    public String getString() {

        if (type == VALUE_STRING) {

            return mValueContainer.getStringValue(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("String");
            }

            return null;
        }
    }


    void setNull() {

        mValueIndex = -1;
        type = VALUE_NULL;
    }


    public boolean isNull() {

        return type == VALUE_NULL;

    }


    void setBooleanValue(boolean value) {

        if (value) {

            mValueIndex = -1;
        } else {

            mValueIndex = -2;
        }

        type = VALUE_BOOLEAN;
    }


    public boolean getBoolean() {

        if (type == VALUE_BOOLEAN) {

            return mValueIndex == -1;
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("boolean");
            }

            return false;
        }
    }


    void saveNumber(@NumberType int numberType, JsonReader jsonReader) throws IOException {

        switch (numberType) {

            case VALUE_NUMBER_INT:
                int i = jsonReader.nextInt();
                saveIntValue(i);
                break;

            case VALUE_NUMBER_LONG:
                long j = jsonReader.nextLong();
                saveLongValue(j);
                break;

            case VALUE_NUMBER_FLOAT:
                float k = (float) jsonReader.nextDouble();
                saveFloatValue(k);
                break;

            case VALUE_NUMBER_DOUBLE:
                double l = jsonReader.nextDouble();
                saveDoubleValue(l);
                break;

            default:
                double m = jsonReader.nextDouble();
                saveDoubleValue(m);
                break;
        }
    }


    void saveIntValue(int value) {

        mValueIndex = mValueContainer.saveIntValue(value);
        type = VALUE_NUMBER_INT;
    }


    public int getInt() {

        if (type == VALUE_NUMBER_INT) {

            return mValueContainer.getIntValue(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("boolean");
            }

            return JsonParserV2.errorNumber;
        }
    }


    void saveLongValue(long value) {

        mValueIndex = mValueContainer.saveLongValue(value);
        type = VALUE_NUMBER_LONG;
    }


    public long getLong() {

        if (type == VALUE_NUMBER_LONG) {

            return mValueContainer.getLongValue(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("long");
            }

            return JsonParserV2.errorNumber;
        }
    }


    void saveFloatValue(float value) {

        mValueIndex = mValueContainer.saveFloatValue(value);
        type = VALUE_NUMBER_FLOAT;
    }


    public float getFloat() {

        if (type == VALUE_NUMBER_FLOAT) {

            return mValueContainer.getFloatValue(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("float");
            }

            return JsonParserV2.errorNumber;
        }
    }


    void saveDoubleValue(double value) {

        mValueIndex = mValueContainer.saveDoubleValue(value);
        type = VALUE_NUMBER_DOUBLE;
    }


    public double getDouble() {

        if (type == VALUE_NUMBER_DOUBLE) {

            return mValueContainer.getDoubleValue(mValueIndex);
        } else {

            if (JsonParserV2.DEBUG) {
                throwTypeNotMatchException("double");
            }

            return JsonParserV2.errorNumber;
        }
    }


    private void throwTypeNotMatchException(String requireType) {

        throw new RuntimeException("Type not Match Exception : current node type is " +
                getTypeString() +
                " you required is " + requireType);

    }


    @Override
    public String toString() {

        String typeText = getTypeString();

        return " node: " + " type: " + typeText + " " + getNodeValue();
    }


    @NonNull
    private String getTypeString() {

        String typeText = null;

        switch (type) {
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

        switch (type) {
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
