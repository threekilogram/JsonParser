package com.example.wuxio.jsonparserlib;

import android.support.annotation.IntDef;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxio 2018-05-14:9:47
 */
public class JsonParser {


    public static final String json = "{ \n" +
            "\t\"name\":\"runoob\", \n" +
            "\t\"alexa\":10000, \n" +
            "\t\"site\":null,\n" +
            "\t\"person\":{\n" +
            "\t\t\"name\":\"wuxiongc\",\n" +
            "\t\t\"age\":27,\n" +
            "\t\t\"job\":null\n" +
            "\t},\n" +
            "\t\"marryed\":false,\n" +
            "\t\"customer\":{\n" +
            "\t\t\"name\":\"king\",\n" +
            "\t\t\"count\":28,\n" +
            "\t\t\"first\":{\n" +
            "\t\t\t\"name\":\"take 1\"\n" +
            "\t\t},\n" +
            "\t\t\"second\":{\n" +
            "\t\t\t\"name\":\"take 2\",\n" +
            "\t\t\t\"age\":25\n" +
            "\t\t}\n" +
            "\t},\n" +
            "\t\"favorate\":[\n" +
            "\t\t\"google\",\n" +
            "\t\t\"runoob\",\n" +
            "\t\t\"taobao\"\n" +
            "\t],\n" +
            "\t\"sites\": [\n" +
            "        { \"name\":\"Google\", \"info\":[ \"Android\", \"Google 搜索\", \"Google 翻译\" ] },\n" +
            "        { \"name\":\"Runoob\", \"info\":[ \"菜鸟教程\", \"菜鸟工具\", \"菜鸟微信\" ] },\n" +
            "        { \"name\":\"Taobao\", \"info\":[ \"淘宝\", \"网购\" ] }\n" +
            "    ]\n" +
            "}";


    public static final String json02 = "{\n" +
            "    \"status\": \"0\",\n" +
            "    \"msg\": \"ok\",\n" +
            "    \"result\": {\n" +
            "        \"city\": \"安顺\",\n" +
            "        \"cityid\": \"111\",\n" +
            "        \"citycode\": \"101260301\",\n" +
            "        \"date\": \"2018-05-15\",\n" +
            "        \"week\": \"星期二\",\n" +
            "        \"weather\": \"晴\",\n" +
            "        \"temp\": \"20\",\n" +
            "        \"temphigh\": \"27\",\n" +
            "        \"templow\": \"18\",\n" +
            "        \"img\": \"0\",\n" +
            "        \"humidity\": \"71\",\n" +
            "        \"pressure\": \"848\",\n" +
            "        \"windspeed\": \"9.1\",\n" +
            "        \"winddirect\": \"南风\",\n" +
            "        \"windpower\": \"3级\",\n" +
            "        \"updatetime\": \"2018-05-15 08:35:00\",\n" +
            "        \"index\": [\n" +
            "            {\n" +
            "                \"iname\": \"空调指数\",\n" +
            "                \"ivalue\": \"较少开启\",\n" +
            "                \"detail\": \"您将感到很舒适，一般不需要开启空调。\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"iname\": \"运动指数\",\n" +
            "                \"ivalue\": \"较适宜\",\n" +
            "                \"detail\": \"天气较好，户外运动请注意防晒。推荐您进行室内运动。\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"iname\": \"紫外线指数\",\n" +
            "                \"ivalue\": \"强\",\n" +
            "                \"detail\": \"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"iname\": \"感冒指数\",\n" +
            "                \"ivalue\": \"少发\",\n" +
            "                \"detail\": \"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"iname\": \"洗车指数\",\n" +
            "                \"ivalue\": \"较适宜\",\n" +
            "                \"detail\": \"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"iname\": \"空气污染扩散指数\",\n" +
            "                \"ivalue\": \"中\",\n" +
            "                \"detail\": \"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"iname\": \"穿衣指数\",\n" +
            "                \"ivalue\": \"舒适\",\n" +
            "                \"detail\": \"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"aqi\": {\n" +
            "            \"so2\": \"2\",\n" +
            "            \"so224\": \"3\",\n" +
            "            \"no2\": \"3\",\n" +
            "            \"no224\": \"7\",\n" +
            "            \"co\": \"0.530\",\n" +
            "            \"co24\": \"0.550\",\n" +
            "            \"o3\": \"90\",\n" +
            "            \"o38\": \"94\",\n" +
            "            \"o324\": \"97\",\n" +
            "            \"pm10\": \"37\",\n" +
            "            \"pm1024\": \"34\",\n" +
            "            \"pm2_5\": \"20\",\n" +
            "            \"pm2_524\": \"20\",\n" +
            "            \"iso2\": \"1\",\n" +
            "            \"ino2\": \"2\",\n" +
            "            \"ico\": \"6\",\n" +
            "            \"io3\": \"29\",\n" +
            "            \"io38\": \"47\",\n" +
            "            \"ipm10\": \"37\",\n" +
            "            \"ipm2_5\": \"29\",\n" +
            "            \"aqi\": \"47\",\n" +
            "            \"primarypollutant\": \"O3\",\n" +
            "            \"quality\": \"优\",\n" +
            "            \"timepoint\": \"2018-05-15 05:00:00\",\n" +
            "            \"aqiinfo\": {\n" +
            "                \"level\": \"一级\",\n" +
            "                \"color\": \"#00e400\",\n" +
            "                \"affect\": \"空气质量令人满意，基本无空气污染\",\n" +
            "                \"measure\": \"各类人群可正常活动\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"daily\": [\n" +
            "            {\n" +
            "                \"date\": \"2018-05-15\",\n" +
            "                \"week\": \"星期二\",\n" +
            "                \"sunrise\": \"06:11\",\n" +
            "                \"sunset\": \"19:35\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"多云\",\n" +
            "                    \"templow\": \"18\",\n" +
            "                    \"img\": \"1\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"晴\",\n" +
            "                    \"temphigh\": \"27\",\n" +
            "                    \"img\": \"0\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2018-05-16\",\n" +
            "                \"week\": \"星期三\",\n" +
            "                \"sunrise\": \"06:10\",\n" +
            "                \"sunset\": \"19:36\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"多云\",\n" +
            "                    \"templow\": \"19\",\n" +
            "                    \"img\": \"1\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"晴\",\n" +
            "                    \"temphigh\": \"28\",\n" +
            "                    \"img\": \"0\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2018-05-17\",\n" +
            "                \"week\": \"星期四\",\n" +
            "                \"sunrise\": \"06:10\",\n" +
            "                \"sunset\": \"19:36\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"多云\",\n" +
            "                    \"templow\": \"20\",\n" +
            "                    \"img\": \"1\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"晴\",\n" +
            "                    \"temphigh\": \"28\",\n" +
            "                    \"img\": \"0\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2018-05-18\",\n" +
            "                \"week\": \"星期五\",\n" +
            "                \"sunrise\": \"06:09\",\n" +
            "                \"sunset\": \"19:37\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"雷阵雨\",\n" +
            "                    \"templow\": \"19\",\n" +
            "                    \"img\": \"4\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"多云\",\n" +
            "                    \"temphigh\": \"28\",\n" +
            "                    \"img\": \"1\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2018-05-19\",\n" +
            "                \"week\": \"星期六\",\n" +
            "                \"sunrise\": \"06:09\",\n" +
            "                \"sunset\": \"19:37\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"雷阵雨\",\n" +
            "                    \"templow\": \"19\",\n" +
            "                    \"img\": \"4\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"多云\",\n" +
            "                    \"temphigh\": \"26\",\n" +
            "                    \"img\": \"1\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2018-05-20\",\n" +
            "                \"week\": \"星期日\",\n" +
            "                \"sunrise\": \"06:08\",\n" +
            "                \"sunset\": \"19:38\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"雷阵雨\",\n" +
            "                    \"templow\": \"20\",\n" +
            "                    \"img\": \"4\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"阵雨\",\n" +
            "                    \"temphigh\": \"26\",\n" +
            "                    \"img\": \"3\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2018-05-21\",\n" +
            "                \"week\": \"星期一\",\n" +
            "                \"sunrise\": \"06:08\",\n" +
            "                \"sunset\": \"19:38\",\n" +
            "                \"night\": {\n" +
            "                    \"weather\": \"大雨\",\n" +
            "                    \"templow\": \"19\",\n" +
            "                    \"img\": \"9\",\n" +
            "                    \"winddirect\": \"持续无风向\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                },\n" +
            "                \"day\": {\n" +
            "                    \"weather\": \"阵雨\",\n" +
            "                    \"temphigh\": \"27\",\n" +
            "                    \"img\": \"3\",\n" +
            "                    \"winddirect\": \"南风\",\n" +
            "                    \"windpower\": \"微风\"\n" +
            "                }\n" +
            "            }\n" +
            "        ],\n" +
            "        \"hourly\": [\n" +
            "            {\n" +
            "                \"time\": \"8:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"20\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"9:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"22\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"10:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"23\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"11:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"24\",\n" +
            "                \"img\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"12:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"25\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"13:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"26\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"14:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"27\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"15:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"27\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"16:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"27\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"17:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"26\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"18:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"25\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"19:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"24\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"20:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"22\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"21:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"21\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"22:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"21\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"23:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"21\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"0:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"20\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"1:00\",\n" +
            "                \"weather\": \"晴\",\n" +
            "                \"temp\": \"20\",\n" +
            "                \"img\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"2:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"20\",\n" +
            "                \"img\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"3:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"19\",\n" +
            "                \"img\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"4:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"19\",\n" +
            "                \"img\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"5:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"19\",\n" +
            "                \"img\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"6:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"18\",\n" +
            "                \"img\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": \"7:00\",\n" +
            "                \"weather\": \"多云\",\n" +
            "                \"temp\": \"18\",\n" +
            "                \"img\": \"1\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    private static final String TAG = "JsonParser";


    private String mCurrentNodeName;
    private List< Node > mNodeTree = new ArrayList<>();

    private static final int TOKEN_VALUE        = 1;
    private static final int TOKEN_NAME         = 3;
    private static final int TOKEN_BEGIN_OBJECT = 5;
    private static final int TOKEN_END_OBJECT   = 7;
    private static final int TOKEN_BEGIN_ARRAY  = 11;
    private static final int TOKEN_END_ARRAY    = 13;
    private int mLastToken;

    private ValueHolder mValueHolder = new ValueHolder();


    public static final int VALUE  = 17;
    public static final int OBJECT = 19;
    public static final int ARRAY  = 21;

    @IntDef({VALUE, OBJECT, ARRAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NodeTypes {
    }

    private boolean isArrayAllObject = false;


    public void parse(String json) {

        StringReader stringReader = new StringReader(json);
        JsonReader jsonReader = new JsonReader(stringReader);
        //jsonReader.setLenient(true);

        try {

            JsonToken peek = jsonReader.peek();
            int size;
            while (peek != JsonToken.END_DOCUMENT) {

                switch (peek) {

                    /* start end */

                    case BEGIN_ARRAY:
                        jsonReader.beginArray();

                        if (mLastToken == TOKEN_NAME) {
                            Node node = mNodeTree.get(mNodeTree.size() - 1);
                            node.type = ARRAY;
                        }

                        mLastToken = TOKEN_BEGIN_ARRAY;
                        Log.i(TAG, "parse:" + "BEGIN_ARRAY");
                        break;

                    case BEGIN_OBJECT:
                        jsonReader.beginObject();

                        if (mLastToken == 0) {

                            /* json begin parse */

                        } else if (mLastToken == TOKEN_NAME) {

                            /* last is TOKEN_NAME,current is TOKEN_BEGIN_OBJECT, means last node is OBJECT
                            type */

                            mNodeTree.get(mNodeTree.size() - 1).type = OBJECT;

                        } else if (mLastToken == TOKEN_BEGIN_ARRAY) {

                            /* beginArray --> beginObject means array elements are all Object */
                            isArrayAllObject = true;

                        }

                        Log.i(TAG, "parse:" + "BEGIN_OBJECT");
                        mLastToken = TOKEN_BEGIN_OBJECT;
                        break;

                    /* name */

                    case NAME:
                        mCurrentNodeName = jsonReader.nextName();

                        if (mLastToken == TOKEN_BEGIN_OBJECT) {

                            /* last is beginObject,current is TOKEN_NAME, means need add a new node */

                            mNodeTree.add(new Node());

                            if (isArrayAllObject) {
                                Node node = mNodeTree.get(mNodeTree.size() - 2);
                                if (node.type == ARRAY) {
                                    node.index += 1;
                                }
                            }

                        } else if (mLastToken == TOKEN_VALUE) {

                            /* last is TOKEN_VALUE,current is TOKEN_NAME, means need change last Node name */

                        } else if (mLastToken == TOKEN_END_OBJECT) {

                            /* last is TOKEN_END_OBJECT,current is TOKEN_NAME, means parse finished a
                            ObjectNode, current parse to a node level is equals to ObjectNode*/

                        } else if (mLastToken == TOKEN_END_ARRAY) {

                            /* array end ,new node parse to, change ths last node name */

                        }
                        mNodeTree.get(mNodeTree.size() - 1).name = mCurrentNodeName;

                        mLastToken = TOKEN_NAME;
                        Log.i(TAG, "parse:" + "NAME: " + mCurrentNodeName);
                        break;

                    /* consume */

                    case NULL:
                        jsonReader.nextNull();
                        setNullValue(mValueHolder);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case STRING:
                        String string = jsonReader.nextString();
                        setStringValue(mValueHolder, string);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case BOOLEAN:
                        boolean booleanValue = jsonReader.nextBoolean();
                        setBooleanValue(mValueHolder, booleanValue);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case NUMBER:
                        double doubleValue = jsonReader.nextDouble();
                        setNumberValue(mValueHolder, doubleValue);
                        parseToValue();
                        mLastToken = TOKEN_VALUE;
                        break;

                    case END_OBJECT:
                        jsonReader.endObject();

                        /* every begin-name will add a node, so every end object remove a node */
                        mNodeTree.remove(mNodeTree.size() - 1);

                        mLastToken = TOKEN_END_OBJECT;
                        Log.i(TAG, "parse:" + "END_OBJECT");
                        break;

                    case END_ARRAY:
                        jsonReader.endArray();

                        if (mLastToken == TOKEN_VALUE) {

                            /* lsat is TOKEN_VALUE, current is END_ARRAY, means array elements are all
                            VALUE type */
                            mNodeTree.get(mNodeTree.size() - 1).index = -1;
                        }

                        mLastToken = TOKEN_END_ARRAY;
                        Log.i(TAG, "parse:" + "END_ARRAY");
                        break;

                    default:
                        break;
                }

                peek = jsonReader.peek();
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void parseToValue() {

        if (mLastToken == TOKEN_NAME) {
            parseToValueNode();
        }

        if (mLastToken == TOKEN_VALUE || mLastToken == TOKEN_BEGIN_ARRAY) {
            Node node = mNodeTree.get(mNodeTree.size() - 1);
            node.index += 1;
            Log.i(TAG, "NodeTree:" + mNodeTree +
                    " name: " + node.name +
                    " value: " + mValueHolder.value());
        }
    }


    private static class Node {

        public String name;
        @NodeTypes
        public int    type;
        private int index = -1;


        /**
         * @return array index, or -1 if node is not {@link #ARRAY} type
         */
        public int getIndex() {

            if (type == ARRAY) {

                return index;
            }
            return -1;
        }


        @Override
        public String toString() {

            return "Node{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", index=" + index +
                    '}';
        }
    }


    private void parseToValueNode() {

        Node node = mNodeTree.get(mNodeTree.size() - 1);
        node.type = VALUE;
        Log.i(TAG, "NodeTree:" + mNodeTree +
                " name: " + node.name +
                " value: " + mValueHolder.value());
    }


    private static boolean isParseToArrayValueNode(Node theLastNode) {

        return theLastNode.type == ARRAY;
    }


    private void removeLastNode() {

    }


    private static void setStringValue(ValueHolder valueHolder, String value) {

        valueHolder.init();
        valueHolder.setStringValue(value);
        valueHolder.setJsonToken(JsonToken.STRING);

    }


    private static void setNullValue(ValueHolder valueHolder) {

        valueHolder.init();
        valueHolder.setNull();
        valueHolder.setJsonToken(JsonToken.NULL);

    }


    private static void setNumberValue(ValueHolder valueHolder, double value) {

        valueHolder.init();
        valueHolder.setDoubleValue(value);
        valueHolder.setJsonToken(JsonToken.NUMBER);

    }


    private static void setBooleanValue(ValueHolder valueHolder, boolean value) {

        valueHolder.init();
        valueHolder.setBooleanValue(value);
        valueHolder.setJsonToken(JsonToken.BOOLEAN);

    }


    private void notifyListener() {

        Log.e(TAG, "notifyListener:" + mCurrentNodeName + " " + mValueHolder.value());
    }

}
