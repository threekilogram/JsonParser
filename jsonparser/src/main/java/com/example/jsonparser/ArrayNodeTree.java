package com.example.jsonparser;

import java.util.ArrayList;

/**
 * 对应json中array类型的节点
 *
 * @author wuxio 2018-05-22:16:14
 */
public class ArrayNodeTree implements NodeTree {

    private ArrayList< Node > mNodeList = new ArrayList<>();


    void addNode(Node node) {

        mNodeList.add(node);
    }


    public Node getNode(int index) {

        return mNodeList.get(index);
    }
}
