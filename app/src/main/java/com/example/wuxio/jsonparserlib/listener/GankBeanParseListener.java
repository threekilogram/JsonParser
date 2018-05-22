package com.example.wuxio.jsonparserlib.listener;

import android.util.Log;

import com.example.jsonparser.JsonParserV1;
import com.example.jsonparser.ValueHolder;
import com.example.wuxio.jsonparserlib.bean.GankBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxio 2018-05-21:9:46
 */
public class GankBeanParseListener implements JsonParserV1.OnParseListener {


    private static final String TAG = "GankBeanParseListener";
    private GankBean mGankBean;


    @Override
    public void onBegin() {

        mGankBean = new GankBean();
    }


    @Override
    public void onNewArrayRequire(List< JsonParserV1.Node > nodes) {

        if ("results".equals(nodes.get(0).name)) {
            mGankBean.setResults(new ArrayList< GankBean.ResultsBean >());
        }
    }


    @Override
    public void onNewArrayElementRequire(List< JsonParserV1.Node > nodes) {

        if ("results".equals(nodes.get(0).name)) {
            mGankBean.getResults().add(new GankBean.ResultsBean());
        }
    }


    @Override
    public void onParseTo(List< JsonParserV1.Node > nodes, String key, ValueHolder valueHolder) {

        if ("error".equals(nodes.get(0).name)) {

            mGankBean.setError(valueHolder.booleanValue());

        } else if ("results".equals(nodes.get(0).name)) {

            Log.i(TAG, "onParseTo:" + nodes+" "+key+" "+valueHolder.value());

            List< GankBean.ResultsBean > results = mGankBean.getResults();

            JsonParserV1.Node node = nodes.get(nodes.size() - 2);
            int index = node.getIndex();
            Log.i(TAG, "onParseTo:" + index);

            //GankBean.ResultsBean bean = results.get(index);

//
//            switch (key) {
//
//                case "_id":
//                    bean.set_id(valueHolder.value());
//                    break;
//                case "createdAt":
//                    bean.setCreatedAt(valueHolder.value());
//                    break;
//                case "desc":
//                    break;
//                case "publishedAt":
//                    bean.setPublishedAt(valueHolder.value());
//                    break;
//                case "mType":
//                    bean.setType(valueHolder.value());
//                    break;
//                case "url":
//                    bean.setUrl(valueHolder.value());
//                    break;
//                case "used":
//                    bean.setUsed(valueHolder.booleanValue());
//                    break;
//                case "who":
//                    bean.setWho(valueHolder.value());
//                    break;
//                default:
//                    break;
//            }

        }
    }
}
