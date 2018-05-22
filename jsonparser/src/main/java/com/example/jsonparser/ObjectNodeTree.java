package com.example.jsonparser;

import android.support.v4.util.ArrayMap;

/**
 * 对应json中object类型的节点
 *
 * @author wuxio 2018-05-22:16:14
 */
public class ObjectNodeTree implements NodeTree {

    private ArrayMap< String, Node > mNodeMap = new ArrayMap<>();


    void addNode(String key, Node node) {

        mNodeMap.put(key, node);
    }


    public Node getNode(String key) {

        return mNodeMap.get(key);
    }
}
