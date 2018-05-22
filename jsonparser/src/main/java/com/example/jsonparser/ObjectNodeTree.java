package com.example.jsonparser;

import android.support.annotation.Nullable;
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
     * 当前 Object 的父节点,如果是对应整个json的ObjectNodeTree那么parent为null
     */
    Node parent;


    @Nullable
    public Node getParent() {

        return parent;
    }


    void addNode(String key, Node node) {

        mNodeMap.put(key, node);
    }


    public Node getNode(String key) {

        return mNodeMap.get(key);
    }
}
