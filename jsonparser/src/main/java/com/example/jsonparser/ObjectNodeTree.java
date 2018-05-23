package com.example.jsonparser;

import android.support.v4.util.ArrayMap;

/**
 * 对应json中object类型的节点
 *
 * @author wuxio 2018-05-22:16:14
 */
public class ObjectNodeTree implements NodeTree {

    /**
     * 保存json object中key和key对应的节点
     */
    private ArrayMap< String, Node > mNodeMap = new ArrayMap<>();

    /**
     * 当前 Object 对应的node,如果是对应整个json的ObjectNodeTree那么为null
     */
    Node node;


    void addNode(String key, Node node) {

        mNodeMap.put(key, node);
    }


    @Override
    public String toString() {

        int size = mNodeMap.size();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Object: ");

        for (int i = 0; i < size; i++) {
            String key = mNodeMap.keyAt(i);
            Node node = mNodeMap.get(key);
            stringBuilder.append(key).append(" : ").append(node.nodeValue()).append("; ");
        }

        return stringBuilder.toString();
    }


    @Override
    public Node nodeLinkedToTree() {

        return node;
    }

    //============================ 属性读取方法 ============================


    /**
     * 读取json中key对应的string
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是string返回null
     */
    public String getString(String key) {

        try {

            return mNodeMap.get(key).getString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中key对应的值是否是null,true是null
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json null 返回false
     */
    public boolean isNull(String key) {

        try {

            return mNodeMap.get(key).isNull();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断json中是否存在该key
     *
     * @param key 需要验证的key
     * @return true : 存在该key; false : 不存在
     */
    public boolean valueExist(String key) {

        return mNodeMap.get(key) != null;
    }


    /**
     * 返回该key对应的值的string形式
     *
     * @param key json key
     * @return key 对应的 value 的 string 形式
     */
    public String getValue(String key) {

        try {

            return mNodeMap.get(key).nodeValue();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中key对应的boolean类型的值
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json boolean 返回 false
     */
    public boolean getBoolean(String key) {

        try {

            return mNodeMap.get(key).getBoolean();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    /**
     * 读取json中key对应的json object对象
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json object 返回 null
     */
    public ObjectNodeTree getObject(String key) {

        try {

            return mNodeMap.get(key).getObject();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中key对应的json Array 对象
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json array 返回 null
     */
    public ArrayNodeTree getArray(String key) {

        try {

            return mNodeMap.get(key).getArray();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取json中key对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是int类型,使用该方法直接读取,
     * 如果是其他类型将会转为int返回
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public int getInt(String key) {

        Node node = mNodeMap.get(key);
        return node.numberInt();
    }


    /**
     * 读取json中key对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是long类型,使用该方法直接读取,
     * 如果是其他类型将会转为long返回
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public long getLong(String key) {

        Node node = mNodeMap.get(key);
        return node.numberLong();
    }


    /**
     * 读取json中key对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是float类型,使用该方法直接读取,
     * 如果是其他类型将会转为float返回
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public float getFloat(String key) {

        Node node = mNodeMap.get(key);
        return node.numberFloat();
    }


    /**
     * 读取json中key对应的 json number 类型的值,
     * 如果{@link JsonParser#setNumberType(int)}配置的是double类型,使用该方法直接读取,
     * 如果是其他类型将会转为double返回
     *
     * @param key key ,json 中的key
     * @return key 对应的值,如果json中没有该key,或者该key对应的不是 json number 返回 {@link JsonParser#errorNumber}
     */
    public double getDouble(String key) {

        Node node = mNodeMap.get(key);
        return node.numberDouble();
    }
}
