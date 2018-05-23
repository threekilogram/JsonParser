package com.example.wuxio.jsonparserlib.json;

/**
 * @author wuxio 2018-05-15:11:51
 */
public class TestJson {

    public static final String JSON00 = "{\n" +
            "        \"name\": \"runoob\",\n" +
            "        \"alexa\": 10000,\n" +
            "\t\t\"marryed\": false,\n" +
            "\t\t\"ehco\":null\t\t\n" +
            "}";

    public static final String JSON01 = "{\n" +
            "\t\"name\": \"runoob\",\n" +
            "\t\"alexa\": 10000,\n" +
            "\t\"sites\": {\n" +
            "\t\t\"site1\": \"www.runoob.com\",\n" +
            "\t\t\"site2\": \"m.runoob.com\",\n" +
            "\t\t\"site3\": \"c.runoob.com\"\n" +
            "\t},\n" +
            "\t\"sites2\": {\n" +
            "\t\t\"site1\": \"www.runoob.com\",\n" +
            "\t\t\"site2\": \"m.runoob.com\",\n" +
            "\t\t\"site3\": \"c.runoob.com\"\n" +
            "\t}\n" +
            "}";

    public static final String JSON02 = "{\n" +
            "        \"name\": \"runoob\",\n" +
            "        \"alexa\": 10000,\n" +
            "        \"sites\": {\n" +
            "            \"site1\": \"www.runoob.com\",\n" +
            "            \"site2\": \"m.runoob.com\",\n" +
            "            \"site3\": \"c.runoob.com\"\n" +
            "        },\n" +
            "\t\t\"sites2\": {\n" +
            "            \"site1\": \"www.runoob.com\",\n" +
            "            \"site2\": \"m.runoob.com\",\n" +
            "            \"site3\": \"c.runoob.com\"\n" +
            "        },\n" +
            "\t\t\"sites3\": {\n" +
            "            \"site3_1\": {\n" +
            "\t\t\t\t\"site1\": \"www.runoob.com\",\n" +
            "\t\t\t\t\"site2\": \"m.runoob.com\",\n" +
            "\t\t\t\t\"site3\": \"c.runoob.com\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"sites3_2\": {\n" +
            "\t\t\t\t\"site1\": \"www.runoob.com\",\n" +
            "\t\t\t\t\"site2\": \"m.runoob.com\",\n" +
            "\t\t\t\t\"site3\": \"c.runoob.com\"\n" +
            "\t\t\t}\n" +
            "        }\n" +
            "}";

    public static final String JSON03 = "{\n" +
            "\t\"favorate\": [\n" +
            "\t\t\"android\",\n" +
            "\t\t\"java\",\n" +
            "\t\t\"kotlin\"\n" +
            "\t],\n" +
            "\t\"ages\": [\n" +
            "\t\t12,\n" +
            "\t\t18,\n" +
            "\t\t20,\n" +
            "\t\t24,\n" +
            "\t\t27\n" +
            "\t]\n" +
            "}";

    public static final String JSON04 = "{\n" +
            "\t\"favorate\": [\n" +
            "\t\t\"android\",\n" +
            "\t\t\"java\",\n" +
            "\t\t\"kotlin\"\n" +
            "\t],\n" +
            "\t\"ages\": [\n" +
            "\t\t12,\n" +
            "\t\t18,\n" +
            "\t\t20,\n" +
            "\t\t24,\n" +
            "\t\t27\n" +
            "\t],\n" +
            "\t\"groups\": [{\n" +
            "\t\t\t\"group\": 1,\n" +
            "\t\t\t\"count\": 24\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"group\": 2,\n" +
            "\t\t\t\"count\": 36\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"group\": 3,\n" +
            "\t\t\t\"count\": 48\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

    public static final String JSON = "{\n" +
            "\t\"name\": \"runoob\",\n" +
            "\t\"alexa\": 10000,\n" +
            "\t\"sites\": {\n" +
            "\t\t\"site1\": \"www.runoob.com\",\n" +
            "\t\t\"site2\": \"m.runoob.com\",\n" +
            "\t\t\"site3\": \"c.runoob.com\"\n" +
            "\t},\n" +
            "\t\"favorate\": [\n" +
            "\t\t\"android\",\n" +
            "\t\t\"java\",\n" +
            "\t\t\"kotlin\"\n" +
            "\t],\n" +
            "\t\"ages\": [\n" +
            "\t\t12,\n" +
            "\t\t18,\n" +
            "\t\t20,\n" +
            "\t\t24,\n" +
            "\t\t27\n" +
            "\t],\n" +
            "\t\"groups\": [{\n" +
            "\t\t\t\"group\": 1,\n" +
            "\t\t\t\"count\": 24\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"group\": 2,\n" +
            "\t\t\t\"count\": 36\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"group\": 3,\n" +
            "\t\t\t\"count\": 48\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"siteCollect\": {\n" +
            "\t\t\"name\": \"网站\",\n" +
            "\t\t\"num\": 3,\n" +
            "\t\t\"sites\": [\n" +
            "\t\t\t\"Google\",\n" +
            "\t\t\t\"Runoob\",\n" +
            "\t\t\t\"Taobao\"\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t\"totals\": [{\n" +
            "\t\t\t\"name\": \"hello\",\n" +
            "\t\t\t\"count\": 24,\n" +
            "\t\t\t\"sites\": [\n" +
            "\t\t\t\t\"baidu\",\n" +
            "\t\t\t\t\"alibaba\",\n" +
            "\t\t\t\t\"tencent\"\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"world\",\n" +
            "\t\t\t\"count\": 36,\n" +
            "\t\t\t\"sites\": [\n" +
            "\t\t\t\t\"meituan\",\n" +
            "\t\t\t\t\"jingdong\",\n" +
            "\t\t\t\t\"lagou\"\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"java\",\n" +
            "\t\t\t\"count\": 48,\n" +
            "\t\t\t\"sites\": [\n" +
            "\t\t\t\t\"twitter\",\n" +
            "\t\t\t\t\"facebook\",\n" +
            "\t\t\t\t\"alphabet\"\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"anotherTotals\": [{\n" +
            "\t\t\t\"name\": \"hello\",\n" +
            "\t\t\t\"sites\": [\n" +
            "\t\t\t\t\"baidu\",\n" +
            "\t\t\t\t\"alibaba\",\n" +
            "\t\t\t\t\"tencent\"\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"world\",\n" +
            "\t\t\t\"sites\": [\n" +
            "\t\t\t\t\"meituan\",\n" +
            "\t\t\t\t\"jingdong\",\n" +
            "\t\t\t\t\"lagou\"\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"java\",\n" +
            "\t\t\t\"sites\": [\n" +
            "\t\t\t\t\"twitter\",\n" +
            "\t\t\t\t\"facebook\",\n" +
            "\t\t\t\t\"alphabet\"\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

}
