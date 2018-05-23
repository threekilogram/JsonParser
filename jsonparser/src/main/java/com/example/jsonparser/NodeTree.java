package com.example.jsonparser;

import java.io.Reader;

/**
 * 标记接口
 *
 * @author wuxio 2018-05-22:8:38
 */
interface NodeTree {

    /**
     * 返回指向tree的node,主要用于{@link JsonParser#parse(Reader)},用户使用没有效果
     *
     * @return node 指向tree的node
     */
    Node nodeLinkedToTree();

}
