package com.example.jsonparser;

/**
 * @author wuxio 2018-05-22:8:52
 */
class ValueContainer {

    private static final int ARRAY_SIZE = 8;

    //============================ 保存json Object/array value,NodeTree类型 ============================

    private NodeTree[] mNodeTrees;
    private int        mNodeTreesIndex;


    public int saveNodeTree(NodeTree value) {

        if (mNodeTrees == null) {
            mNodeTrees = new NodeTree[ARRAY_SIZE];
        }

        int index = mNodeTreesIndex;

        if (mNodeTrees.length == index) {

            growNodeTreeArray();
        }

        mNodeTrees[index] = value;

        mNodeTreesIndex = index + 1;

        return index;
    }


    public NodeTree getNodeTree(int index) {

        try {
            return mNodeTrees[index];
        } catch (Exception e) {
            return null;
        }
    }


    private void growNodeTreeArray() {

        int length = mNodeTrees.length;
        int i = length / ARRAY_SIZE + 1;
        NodeTree[] newObjectArray = new NodeTree[i * ARRAY_SIZE];
        System.arraycopy(mNodeTrees, 0, newObjectArray, 0, length);
        mNodeTrees = newObjectArray;
    }

    //============================ 保存json String value ============================

    private String[] mStringValues;
    private int      mStringValueIndex;


    public int saveStringValue(String value) {

        if (mStringValues == null) {
            mStringValues = new String[ARRAY_SIZE];
        }

        int index = mStringValueIndex;

        if (mStringValues.length == index) {

            growStringArray();
        }

        mStringValues[index] = value;

        mStringValueIndex = index + 1;

        return index;
    }


    public String getStringValue(int index) {

        try {
            return mStringValues[index];
        } catch (Exception e) {
            return null;
        }
    }


    private void growStringArray() {

        int length = mStringValues.length;
        int i = length / ARRAY_SIZE + 1;
        String[] newObjectArray = new String[i * ARRAY_SIZE];
        System.arraycopy(mStringValues, 0, newObjectArray, 0, length);
        mStringValues = newObjectArray;
    }

    /* 使用哪种类型保存由JsonParser.numberType 决定*/

    //============================ 保存json Number value 为int类型 ============================

    private int[] mIntValues;
    private int   mIntValueIndex;


    public int saveIntValue(int value) {

        if (mIntValues == null) {
            mIntValues = new int[ARRAY_SIZE];
        }

        int index = mIntValueIndex;

        if (mIntValues.length == index) {

            growIntArray();
        }

        mIntValues[index] = value;

        mIntValueIndex = index + 1;

        return index;
    }


    public int getIntValue(int index) {

        try {
            return mIntValues[index];
        } catch (Exception e) {

            return JsonParserV2.errorNumber;
        }
    }


    private void growIntArray() {

        int length = mIntValues.length;
        int i = length / ARRAY_SIZE + 1;
        int[] newIntArray = new int[i * ARRAY_SIZE];
        System.arraycopy(mIntValues, 0, newIntArray, 0, length);
        mIntValues = newIntArray;
    }

    //============================ 保存json Number value 为 long 类型 ============================

    private long[] mLongValues;
    private int    mLongValueIndex;


    public int saveLongValue(long value) {

        if (mLongValues == null) {
            mLongValues = new long[ARRAY_SIZE];
        }

        int index = mLongValueIndex;

        if (mLongValues.length == index) {

            growLongArray();
        }

        mLongValues[index] = value;

        mLongValueIndex = index + 1;

        return index;
    }


    public long getLongValue(int index) {

        try {
            return mLongValues[index];
        } catch (Exception e) {

            return JsonParserV2.errorNumber;
        }
    }


    private void growLongArray() {

        int length = mLongValues.length;
        int i = length / ARRAY_SIZE + 1;
        long[] newLongArray = new long[i * ARRAY_SIZE];
        System.arraycopy(mLongValues, 0, newLongArray, 0, length);
        mLongValues = newLongArray;
    }

    //============================ 保存json Number value 为 float 类型 ============================

    private float[] mFloatValues;
    private int     mFloatValueIndex;


    public int saveFloatValue(float value) {

        if (mFloatValues == null) {
            mFloatValues = new float[ARRAY_SIZE];
        }

        int index = mFloatValueIndex;

        if (mFloatValues.length == index) {

            growFloatArray();
        }

        mFloatValues[index] = value;

        mFloatValueIndex = index + 1;

        return index;
    }


    public float getFloatValue(int index) {

        try {
            return mFloatValues[index];
        } catch (Exception e) {

            return JsonParserV2.errorNumber;
        }
    }


    private void growFloatArray() {

        int length = mFloatValues.length;
        int i = length / ARRAY_SIZE + 1;
        float[] newFloatArray = new float[i * ARRAY_SIZE];
        System.arraycopy(mFloatValues, 0, newFloatArray, 0, length);
        mFloatValues = newFloatArray;
    }

    //============================ 保存json Number value 为 double 类型 ============================

    private double[] mDoubleValues;
    private int      mDoubleValueIndex;


    public int saveDoubleValue(double value) {

        if (mDoubleValues == null) {
            mDoubleValues = new double[ARRAY_SIZE];
        }

        int index = mDoubleValueIndex;

        if (mDoubleValues.length == index) {

            growDoubleArray();
        }

        mDoubleValues[index] = value;

        mDoubleValueIndex = index + 1;

        return index;
    }


    public double getDoubleValue(int index) {

        try {
            return mDoubleValues[index];
        } catch (Exception e) {

            return JsonParserV2.errorNumber;
        }
    }


    private void growDoubleArray() {

        int length = mDoubleValues.length;
        int i = length / ARRAY_SIZE + 1;
        double[] newDoubleArray = new double[i * ARRAY_SIZE];
        System.arraycopy(mDoubleValues, 0, newDoubleArray, 0, length);
        mDoubleValues = newDoubleArray;
    }
}
