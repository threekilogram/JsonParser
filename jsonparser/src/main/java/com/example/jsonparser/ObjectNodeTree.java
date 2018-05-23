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


    public Node getNode(String key) {

        return mNodeMap.get(key);
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
}
