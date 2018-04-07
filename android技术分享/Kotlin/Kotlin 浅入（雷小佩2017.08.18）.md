# Kotlin 浅入
#### 1. Kotlin basic syntax
Kotlin 简洁、与java兼容、开源，以.kt为后缀
由JetBrains公司开发，已正式成为Android官方支持开发语言
##### 1.1包声明

```
package com.runoob.main
import java.util.*
fun test() {}
class Runoob {}
```

kotlin源文件不需要相匹配的目录和包，源文件可以放在任何文件目录。
##### 1.2默认导入
有多个包会默认导入到每个 Kotlin 文件中： 

```
kotlin.*
kotlin.annotation.*
kotlin.collections.*
kotlin.comparisons.* 
kotlin.io.*
```

#### 1.3 函数定义
使用关键字 fun，参数格式为：参数 : 类型 

```
fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
    return a + b
}
```

无返回值的函数(类似Java中的void)：

```
fun printSum(a: Int, b: Int): Unit { 
    print(a + b)
}
```

// 如果是返回 Unit类型，则可以省略(对于public方法也是这样)：

```
public fun printSum(a: Int, b: Int) { 
    print(a + b)
}
```

##### 1.4 lambda(匿名函数)
// 测试
```
fun main(args: Array<String>) {
    val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
    println(sumLambda(1,2))  // 输出 3
}
```

##### 1.5定义常量与变量
**变量定义**：var 关键字 
**var <标识符>** : <类型> = <初始化值>
**不可变变量定义**：val 关键字，只能赋值一次的变量(类似Java中final修饰的变量) 
**val <标识符>** : <类型> = <初始化值>

常量与变量都可以没有初始化值,但是在引用前必须初始化 
编译器支持自动类型判断,即声明时可以不指定类型,由编译器判断。

```
val a: Int = 1
val b = 1       // 系统自动推断变量类型为Int
val c: Int      // 如果不在声明时初始化则必须提供变量类型
c = 1           // 明确赋值
var x = 5        // 系统自动推断变量类型为Int
x += 1           // 变量可修改
```

##### 1.6注释
kotlin 支持单行和多行注释，实例如下：
// 这是一个单行注释
```
/* 这是一个多行的
   块注释。 */
```

##### 1.7 NULL检查机制
Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，字段后加!!像Java一样抛出空异常，另一种字段后加?可不做处理返回值为 null 
//类型后面加?表示可为空

```
var age: String? = "23" 
```

//抛出空指针异常

```
val ages = age!!.toInt()
```

//不做处理返回 null

```
val ages1 = age?.toInt()
```

##### 1.8匿名内部类
使用对象表达式来创建匿名内部类：

```
class Test {
    var v = "成员属性"
    fun setInterFace(test: TestInterFace) {
        test.test()
    }
}
/**
 * 定义接口
 */
interface TestInterFace {
    fun test()
}
fun main(args: Array<String>) {
    var test = Test()

    /**
     * 采用对象表达式来创建接口对象，即匿名内部类的实例。
     */
    test.setInterFace(object : TestInterFace {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })
}
```
##### 1.9 几个问题的补充
1. val fab = findViewById(R.id.fab) as FloatingActionButton（该处强转是非安全的，如果强转不成功，程序会Crash）

2.  Kotlin中使用匿名内部类，如果设置监听EditText的输入内容变化，如下来创建匿名内部类的实例

```
var editText = findViewById(R.id.et_container) as EditText
editText.addTextChangedListener(object : TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d("test","beforeTextChanged")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d("test","onTextChanged:" + s)
    }

    override fun afterTextChanged(s: Editable?) {
        Log.d("test","afterTextChanged:" + s)
    }
})
  ```           
     Kotlin匿名内部类的语法可以参考该网址：http://www.runoob.com/kotlin/kotlin-class-object.html
    ```
    test.setInterFace(object : TestInterFace {
            override fun test() {
                println("对象表达式创建匿名内部类的实例")
    }
    ```

                 
    至于google官方文档给出的点击监听，省略匿名内部类的问题，估计只有特定的API可以。
```
fab.setOnClickListener {
  //进行操作
}

```

**参考资料** https://m.runoob.com/kotlin/kotlin-basic-syntax.html

#### 2.Get Started with Kotlin on Android
如果想要方便的体验使用Kotlin开发Android，建议下载Android Studio3.0预览版，在2.3.2版本上需要下载Kotlin相关插件
##### 2.1新建工程，勾选“Include Kotlin support”

![新建Kotlin工程.png](http://upload-images.jianshu.io/upload_images/1996162-5ecc7aa2de08c6a8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 2.2创建Activity，Source Language选择“Kotlin”  

![创建Activity.png](http://upload-images.jianshu.io/upload_images/1996162-89bf1c11dca73cb2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 2.3现有jave类代码直接转Kotlin代码
使用**Code**->**Convert Java File to Kotlin File**
 
![现有jave类代码直接转Kotlin代码.png](http://upload-images.jianshu.io/upload_images/1996162-bab84b8e544953d0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

但是并不能保证转换成的Kotlin可以直接运行，如果有需要改动的地方，在转的时候会有如下提示：
![转换成的Kotlin提示.png](http://upload-images.jianshu.io/upload_images/1996162-ae49933a731d5f86.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



##### 3. Use Android APIs with Kotlin

```
Declare Activity in Kotlin
class MyActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity)
  }
}
```

**Declare Activity in Java**

```
public class MyActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity);
  }
}
On-click listener in Kotlin
val fab = findViewById(R.id.fab) as FloatingActionButton（该处强转是非安全的）
fab.setOnClickListener {
  ...
}
On-click listener in Java
FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
fab.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View view) {
    ...
  }
});
```

**注意：**只有click listener的监听可以这样省掉匿名内部类的实例化

#### 4.小Demo
实现EditText输入图片和文字
 
![实现EditText输入图片和文字.png](http://upload-images.jianshu.io/upload_images/1996162-a27297157b1d7766.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


Java代码部分

```
public class MainActivity extends AppCompatActivity {
    private EditText mEtContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtContainer = (EditText)findViewById(R.id.et_container);
        Button btnInsert = (Button)findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int start = mEtContainer.getSelectionStart();
                Bitmap bitmap = createBitmap(new ItemView().getItemView("Insert", MainActivity.this));
                if(bitmap != null){
                    ImageSpan imageSpan = new ImageSpan(MainActivity.this, bitmap);
                    SpannableString spannableString = new SpannableString("Insert");
                    spannableString.setSpan(imageSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Editable editable = mEtContainer.getText();
                    editable.insert(start, spannableString);
                    mEtContainer.setText(editable);
                    mEtContainer.setSelection(start + spannableString.length());
                } else {
                    System.out.print("bitmap == null");
                }
            }
        });
    }
    private Bitmap createBitmap(View view){
        Bitmap bitmap = null;
        if(view != null){
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            view.layout(0, 0, view.getMeasuredWidth(),view.getMeasuredHeight());
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            bitmap = view.getDrawingCache();
        }
        return bitmap;
    }
```

使用Kotlin写getItemView方法

```
public class ItemView {
    public fun getItemView(name: String, context: Context): View {
        val validateItem = View.inflate(context, R.layout.layout_edit_containter_time_item, null)
        val tvTime = validateItem.findViewById(R.id.tv_item_time) as TextView
        tvTime.text = name
        return validateItem
    }
}
```

如果设置监听EditText的输入内容变化，如下来创建匿名内部类的实例

```
var editText = findViewById(R.id.et_container) as EditText
editText.addTextChangedListener(object : TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d("test","beforeTextChanged")
    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d("test","onTextChanged:" + s)
    }
    override fun afterTextChanged(s: Editable?) {
        Log.d("test","afterTextChanged:" + s)
    }
})
```

#### 5. Resources to Learn Kotlin
##### 5.1文档
+   **Kotlinlang.org:** The official Kotlin website. Indcludes everything from a guide to basic syntax to the Kotlin standard library reference.
+   **Kotlin Koans Online:** A collection of exercises in an online IDE to help you learn the Kotlin syntax.
##### 5.2视频
+   **YouTube:** This search for "Kotlin on Android" provides a variety of high quality technical talks.
+   **O'Reilly course:** An 8-hour Kotlin course, "Introduction to Kotlin Programming," by Hadi Hariri, a developer at JetBrains. Requires subscription; 10-day free trial available.
+   **Treehouse course:** "Kotlin for Java Developers" teaches Kotlin with an emphasis on Android. Requires subscription; 7-day free trial available.
+   **Udemy course:** "Kotlin for Beginners" teaches Kotlin from scratch. Requires subscription; new student discount available.
##### 5.3书籍
+   **Kotlin in Action:** By Dmitry Jemerov and Svetlana Isakova, Kotlin developers at JetBrains.
+   **Kotlin for Android Developers:** By Antonio Leiva. One of the first books about Kotlin.
##### 5.4社交工具
+   **@kotlin:** The official Kotlin Twitter account.
+   **Kotlin Community:** A list of offline events and groups from kotlinlang.org.
+   **Kotlin Slack:** A Slack chat community for Kotlin users.
+   **Talking Kotlin:** A bi-monthly podcast on Kotlin and more.