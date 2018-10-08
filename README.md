
## Json 解析

封装 JaonReader,分解它解析步骤达到在解析过程中插入操作的目的

### 引入

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```
	dependencies {
	        implementation 'com.github.threekilogram:JsonParser:3.0.1'
	}
```

## 示例

#### json数据

```
{
    "error": false,
    "results": [
        {
            "_id": "5b6aac809d21226f48f68bf0",
            "createdAt": "2018-08-08T16:40:32.198Z",
            "desc": "功能完备的Drawable工具箱，通过代码构建种类多样的Drawable，摆脱枯燥重复的drawable.xml文件",
            "images": [
                "https://ww1.sinaimg.cn/large/0073sXn7gy1fu3bhjbgc3g30a00hs1kx",
                "https://ww1.sinaimg.cn/large/0073sXn7gy1fu3bhju7n3j31401z4qh6",
                "https://ww1.sinaimg.cn/large/0073sXn7gy1fu3bhklcekg30a00hs18m",
                "https://ww1.sinaimg.cn/large/0073sXn7gy1fu3bhl25zvj31401z417j"
            ],
            "publishedAt": "2018-08-09T00:00:00.0Z",
            "source": "web",
            "type": "Android",
            "url": "https://github.com/duanhong169/DrawableToolbox",
            "used": true,
            "who": "Hong Duan"
        },
        {
            "_id": "5b6ba1409d21226f4e09c771",
            "createdAt": "2018-08-09T10:04:48.564Z",
            "desc": "XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。",
            "publishedAt": "2018-08-09T00:00:00.0Z",
            "source": "chrome",
            "type": "Android",
            "url": "https://github.com/xuexiangjys/XHttp2",
            "used": true,
            "who": "xuexiangjys"
        }
    ]
}
```

#### 解析以上数据

```
//创建
JsonParser builder = new JsonParser();
//传入流
builder.start( new StringReader( GankJson.JSON ) );
//读取第一个boolean值
boolean error = builder.readBoolean( "error", false );
//开始读取json类组成的数组节点
builder.readArray( "results" );
//循环解析json数组中的json类
for( int i = 0; i < 2; i++ ) {
      //因为元素是一个json类,所以需要beginObject()
      builder.beginObject();
      
      //按照json中出现顺序读取所有属性
      String id = builder.readString( "_id" );
      String createdAt = builder.readString( "createdAt" );
      String desc = builder.readString( "desc" );
      
      //读取string数组,注意:该节点在数组中第二个json类中不存在,同样需要写在这里,框架会自动跳过直接读取下一个,不必担心
      List<String> images = builder.readStringArray( "images" );
      
      String publishedAt = builder.readString( "publishedAt" );
      String source = builder.readString( "source" );
      String type = builder.readString( "type" );
      String url = builder.readString( "url" );
      boolean used = builder.readBoolean( "used", true );
      String who = builder.readString( "who" );
      
      //读取完成之后结束类
      builder.endObject();
}
//结束读取json类组成的数组节点
builder.endArray();
//结束整个流的解析
builder.finish();
```

> 以上是解析一个完整的json对象

#### 测试用json数据

```
// 该json包含所有json节点的值类型: string, number, boolean, null, string[], number[], boolean[], json类, json类[]

{
	"name": "菜鸟教程",
	"int": 12,
	"long": 696969696,
	"double": 1589.96,
	"boolean": true,
	"empty": null,
	"stringArray": [
		"Hello",
		"World",
		"Json"
	],
	"intArray": [
		12, 24, 36
	],
	"longArray": [
		11, 22, 33
	],
	"doubleArray": [
		10.0, 11.1, 12.2
	],
	"booleanArray": [
		true, false, true
	],
	"object": {
		"name": "wu xiong",
		"age": 27
	},
	"objectArray": [{
			"name": "sun",
			"age": 30
		},
		{
			"name": "liu",
			"age": 30
		},
		{
			"name": "wang",
			"age": 30
		}
	]
}
```

#### 解析以上json

```
//创建json数据流
StringReader reader = new StringReader( TestJson.Json );
//创建解析
JsonParser parser = new JsonParser();
//开始解析流
parser.start( reader );
//需要按照json中出现顺序解析
//读取string节点
String name = parser.readString( "name" );
//读取int节点
int anInt = parser.readInt( "int" );
//读取long节点
long aLong = parser.readLong( "long" );
//读取double节点
double aDouble = parser.readDouble( "double" );
//读取boolean节点
boolean aBoolean = parser.readBoolean( "boolean" );
//读取null节点,
String empty = parser.readString( "empty" );
//读取string array 节点
List<String> stringArray = parser.readStringArray( "stringArray" );
//读取int array 节点
int[] intArrays = parser.readIntArray( "intArray" );
//读取long array 节点
long[] longArrays = parser.readLongArray( "longArray" );
//读取double array 节点
double[] doubleArrays = parser.readDoubleArray( "doubleArray" );
//读取boolean array 节点
boolean[] booleanArrays = parser.readBooleanArray( "booleanArray" );
// 开始读取一个json类
parser.readObject( "object" );
// 读取json类,string节点
String name1 = parser.readString( "name" );
// 读取json类,int节点
int age = parser.readInt( "age" );
// 结束读取json类
parser.endObject();
// 开始读取json类组成的数组
parser.readArray( "objectArray" );
for( int i = 0; i < 3; i++ ) {
      parser.beginObject();
      // 使用如下解析方式可以忽略节点的出现顺序,但是效率不如按照顺序读取高
      while( parser.peek() != JsonToken.END_OBJECT ) {
            String name2 = parser.readString( "name" );
            log( "name", name2 );
            int age1 = parser.readInt( "age" );
            log( "age", age1 );
      }
      parser.endObject();
}
//结束解析数组
parser.endArray();
//结束解析流
parser.finish();
```

#### json数据

```
{
	"name": "菜鸟教程",
	"int": 12,
	"long": 696969696,
	"double": 1589.96,
	"boolean": true,
	"empty": null,
	"stringArray": [
		"Hello",
		"World",
		"Json"
	],
	"intArray": [
		12, 24, 36
	],
	"longArray": [
		11, 22, 33
	],
	"doubleArray": [
		10.0, 11.1, 12.2
	],
	"booleanArray": [
		true, false, true
	],
	"object": {
		"name": "wu xiong",
		"age": 27
	},
	"objectArray": [{
			"name": "sun",
			"age": 30
		},
		{
			"name": "liu",
			"age": 30
		},
		{
			"name": "wang",
			"age": 30
		}
	],
	"skipString": "skip to there",
	"skipInt": 912,
	"skipLong": 99999999999,
	"skipDouble": 999.111,
	"skipBoolean": true,
	"skipStringArray": [
		"skip Hello",
		"skip World",
		"skip Json"
	],
	"skipIntArray": [
		912, 924, 936
	],
	"skipLongArray": [
		911, 922, 933
	],
	"skipDoubleArray": [
		910.0, 911.1, 912.2
	],
	"skipBooleanArray": [
		true, true, true
	],
	"skipObject": {
		"name": "wu xiong",
		"age": 27
	},
	"skipObjectArray": [{
			"name": "skip sun",
			"age": 30
		},
		{
			"name": "skip liu",
			"age": 30
		},
		{
			"name": "skip wang",
			"age": 30
		}
	]
}
```

#### 解析

> 以上的解析方式都是全部属性解析，如果不需要全部解析可以使用带`skip`的方法直接跳转到一个节点读取属性



```
//跳转到指定名称的节点
parser.skipToNode( "skipString" );
```

或者更具体一点跳转到指定类型的节点

```
//跳转到"skipString"节点,并且该节点是string
parser.skipToString( "skipString" );

//跳转到"skipInt",并且该节点是int
parser.skipToNumber( "skipInt" );

//跳转到"skipBoolean",,并且该节点是boolean
parser.skipToBoolean( "skipBoolean" );

//跳转到"skipStringArray",并且该节点是array
parser.skipToArray( "skipStringArray" );

//跳转到"skipObject",并且该节点是json object
parser.skipToObject( "skipObject" );
```