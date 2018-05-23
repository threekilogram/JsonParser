package com.example.jsonparser;

import java.util.ArrayList;

/**
 * 对应json中array类型的节点
 *
 * @author wuxio 2018-05-22:16:14
 */
public class ArrayNodeTree implements NodeTree {

    private ArrayList< Node > mNodeList = new ArrayList<>();


    /**
     * 当前 array 对应的node
     */
    Node node;


    void addNode(Node node) {

        mNodeList.add(node);
    }


    public Node getNode(int index) {

        return mNodeList.get(index);
    }


    public int size() {

        return mNodeList.size();
    }


    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        int size = mNodeList.size();
        for (int i = 0; i < size; i++) {

            Node node = mNodeList.get(i);
            builder.append("index: ").append(i).append("; value: ").append(node.nodeValue()).append("; ");
        }

        return builder.toString();
    }


    @Override
    public Node nodeLinkedToTree() {

        return node;
    }

    //============================ 属性读取方法 ============================


    /**
     * 读取json中key对应的string
     *
     * @param index son array 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是string返回null
     */
    public String getString(int index) {

        try {

            return mNodeList.get(index).getString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中index对应的值是否是null,true是null
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json null 返回false
     */
    public boolean isNull(int index) {

        try {

            return mNodeList.get(index).isNull();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断json中是否存在该index
     *
     * @param index 需要验证的index
     * @return true : 存在该index; false : 不存在
     */
    public boolean valueExist(int index) {

        return mNodeList.get(index) != null;
    }


    /**
     * 返回该index对应的值的string形式
     *
     * @param index json index
     * @return index 对应的 value 的 string 形式
     */
    public String getValue(int index) {

        try {

            return mNodeList.get(index).nodeValue();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中index对应的boolean类型的值
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json boolean 返回 false
     */
    public boolean getBoolean(int index) {

        try {

            return mNodeList.get(index).getBoolean();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    /**
     * 读取json中index对应的json object对象
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json object 返回 null
     */
    public ObjectNodeTree getObject(int index) {

        try {

            return mNodeList.get(index).getObject();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中index对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是int类型,使用该方法直接读取,
     * 如果是其他类型将会转为int返回
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public int getInt(int index) {

        Node node = mNodeList.get(index);
        return node.numberInt();
    }


    /**
     * 读取json中index对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是long类型,使用该方法直接读取,
     * 如果是其他类型将会转为long返回
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public long getLong(int index) {

        Node node = mNodeList.get(index);
        return node.numberLong();
    }


    /**
     * 读取json中index对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是float类型,使用该方法直接读取,
     * 如果是其他类型将会转为float返回
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public float getFloat(int index) {

        Node node = mNodeList.get(index);
        return node.numberFloat();
    }


    /**
     * 读取json中index对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是double类型,使用该方法直接读取,
     * 如果是其他类型将会转为double返回
     *
     * @param index index ,json 中的index
     * @return index 对应的值,如果json中没有该index,或者该index对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public double getDouble(int index) {

        Node node = mNodeList.get(index);
        return node.numberDouble();
    }
}
