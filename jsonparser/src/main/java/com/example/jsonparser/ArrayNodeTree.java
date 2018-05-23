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

    public int size(){

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
}
