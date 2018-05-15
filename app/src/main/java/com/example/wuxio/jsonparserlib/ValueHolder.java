package com.example.wuxio.jsonparserlib;

import android.util.JsonToken;

/**
 * @author wuxio 2018-05-14:17:57
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
class ValueHolder {

    private String  stringValue;
    private double  doubleValue;
    private boolean booleanValue;
    private boolean isNull = true;
    private JsonToken mJsonToken;


    void init() {

        stringValue = null;
        doubleValue = 0;
        booleanValue = false;
        isNull = true;
    }


    public void setJsonToken(JsonToken jsonToken) {

        mJsonToken = jsonToken;
    }


    public void setStringValue(String stringValue) {

        isNull = false;
        this.stringValue = stringValue;
    }


    public void setBooleanValue(boolean booleanValue) {

        isNull = false;
        this.booleanValue = booleanValue;
    }


    public void setDoubleValue(double doubleValue) {

        isNull = false;
        this.doubleValue = doubleValue;
    }


    public void setNull() {

        isNull = true;
    }


    public boolean isNull() {

        return isNull;
    }


    public String stringValue() {

        return stringValue;
    }


    public int intValle() {

        return (int) doubleValue;
    }


    public long longValue() {

        return (long) doubleValue;
    }


    public double doubleValue() {

        return doubleValue;
    }


    public float floatValue() {

        return (float) doubleValue;
    }


    public boolean booleanValue() {

        return booleanValue;
    }


    public String value() {

        switch (mJsonToken) {

            case NULL:
                return null;

            case STRING:
                return stringValue;

            case BOOLEAN:
                return String.valueOf(booleanValue);

            case NUMBER:
                return String.valueOf(doubleValue);

            default:
                break;
        }

        return null;
    }

}
