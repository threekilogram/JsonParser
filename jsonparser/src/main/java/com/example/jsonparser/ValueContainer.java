package com.example.jsonparser;

/**
 * @author wuxio 2018-05-22:8:52
 */
class ValueContainer {

    private static final int ARRAY_SIZE = 16;

    //============================ 保存json Object/array value,NodeTree类型 ============================

    private NodeTree[] mNodeTrees;
    private int        mNodeTreesIndex;


    public int saveNodeTree(NodeTree value) {

        NodeTree[] trees = mNodeTrees;

        if (trees == null) {
            mNodeTrees = new NodeTree[ARRAY_SIZE];
            trees = mNodeTrees;
        }

        int index = mNodeTreesIndex;

        if (trees.length == index) {

            growNodeTreeArray();
            trees = mNodeTrees;
        }

        trees[index] = value;

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

        NodeTree[] trees = mNodeTrees;

        int length = trees.length;
        int i = length / ARRAY_SIZE + 1;
        NodeTree[] newObjectArray = new NodeTree[i * ARRAY_SIZE];
        System.arraycopy(trees, 0, newObjectArray, 0, length);

        mNodeTrees = newObjectArray;
    }

    //============================ 保存json String value ============================

    private String[] mStringValues;
    private int      mStringValueIndex;


    public int saveStringValue(String value) {

        String[] values = mStringValues;

        if (values == null) {
            mStringValues = new String[ARRAY_SIZE];
            values = mStringValues;
        }

        int index = mStringValueIndex;

        if (values.length == index) {

            growStringArray();
            values = mStringValues;
        }

        values[index] = value;

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

        String[] values = mStringValues;

        int length = values.length;
        int i = length / ARRAY_SIZE + 1;
        String[] newObjectArray = new String[i * ARRAY_SIZE];
        System.arraycopy(values, 0, newObjectArray, 0, length);

        mStringValues = newObjectArray;
    }

    /* 使用哪种类型保存由JsonParser.numberType 决定*/

    //============================ 保存json Number value 为int类型 ============================

    private int[] mIntValues;
    private int   mIntValueIndex;


    public int saveIntValue(int value) {

        int[] values = mIntValues;

        if (values == null) {
            mIntValues = new int[ARRAY_SIZE];
            values = mIntValues;
        }

        int index = mIntValueIndex;

        if (values.length == index) {

            growIntArray();
            values = mIntValues;
        }

        values[index] = value;

        mIntValueIndex = index + 1;

        return index;
    }


    public int getIntValue(int index) {

        try {
            return mIntValues[index];
        } catch (Exception e) {

            return JsonParser.errorNumber;
        }
    }


    private void growIntArray() {

        int[] values = mIntValues;

        int length = values.length;
        int i = length / ARRAY_SIZE + 1;
        int[] newIntArray = new int[i * ARRAY_SIZE];
        System.arraycopy(values, 0, newIntArray, 0, length);

        mIntValues = newIntArray;
    }

    //============================ 保存json Number value 为 long 类型 ============================

    private long[] mLongValues;
    private int    mLongValueIndex;


    public int saveLongValue(long value) {

        long[] values = mLongValues;

        if (values == null) {

            mLongValues = new long[ARRAY_SIZE];
            values = mLongValues;
        }

        int index = mLongValueIndex;

        if (mLongValues.length == index) {

            growLongArray();
            values = mLongValues;
        }

        values[index] = value;

        mLongValueIndex = index + 1;

        return index;
    }


    public long getLongValue(int index) {

        try {

            return mLongValues[index];

        } catch (Exception e) {

            return JsonParser.errorNumber;
        }
    }


    private void growLongArray() {

        long[] values = mLongValues;

        int length = values.length;
        int i = length / ARRAY_SIZE + 1;
        long[] newLongArray = new long[i * ARRAY_SIZE];
        System.arraycopy(values, 0, newLongArray, 0, length);

        mLongValues = newLongArray;
    }

    //============================ 保存json Number value 为 float 类型 ============================

    private float[] mFloatValues;
    private int     mFloatValueIndex;


    public int saveFloatValue(float value) {

        float[] values = mFloatValues;

        if (values == null) {
            mFloatValues = new float[ARRAY_SIZE];
            values = mFloatValues;
        }

        int index = mFloatValueIndex;

        if (values.length == index) {

            growFloatArray();
            values = mFloatValues;
        }

        values[index] = value;

        mFloatValueIndex = index + 1;

        return index;
    }


    public float getFloatValue(int index) {

        try {

            return mFloatValues[index];

        } catch (Exception e) {

            return JsonParser.errorNumber;
        }
    }


    private void growFloatArray() {

        float[] values = mFloatValues;

        int length = values.length;
        int i = length / ARRAY_SIZE + 1;
        float[] newFloatArray = new float[i * ARRAY_SIZE];
        System.arraycopy(values, 0, newFloatArray, 0, length);

        mFloatValues = newFloatArray;
    }

    //============================ 保存json Number value 为 double 类型 ============================

    private double[] mDoubleValues;
    private int      mDoubleValueIndex;


    public int saveDoubleValue(double value) {

        double[] values = mDoubleValues;

        if (values == null) {

            mDoubleValues = new double[ARRAY_SIZE];
            values = mDoubleValues;
        }

        int index = mDoubleValueIndex;

        if (values.length == index) {

            growDoubleArray();
            values = mDoubleValues;
        }

        values[index] = value;

        mDoubleValueIndex = index + 1;

        return index;
    }


    public double getDoubleValue(int index) {

        try {

            return mDoubleValues[index];

        } catch (Exception e) {

            return JsonParser.errorNumber;
        }
    }


    private void growDoubleArray() {

        double[] values = mDoubleValues;

        int length = values.length;
        int i = length / ARRAY_SIZE + 1;
        double[] newDoubleArray = new double[i * ARRAY_SIZE];
        System.arraycopy(values, 0, newDoubleArray, 0, length);

        mDoubleValues = newDoubleArray;
    }
}
