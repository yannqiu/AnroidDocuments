
# Android面试题库（四大组件篇）

[Android面试大全（四大组件篇）](http://www.jianshu.com/p/9c0d9f561c8e)
[Android面试大全（性能优化篇）](http://www.jianshu.com/p/7c9df5b59a26)
[Android面试大全（异常处理篇）](http://www.jianshu.com/p/18fa6f1a3ed7)
[Android面试大全（开源框架篇）](http://www.jianshu.com/p/05ddce703d12)
[Android面试大全（网络篇）](http://www.jianshu.com/p/c07bec84d904)
[Android面试大全（java篇）](http://www.jianshu.com/p/dba49efea11a)


## 谈谈对Android四大组件的认识

* * *
_这都是基本常识_

*   **Activity**
*   **BrocastReceiver**
*   **ContentProvider**
*   **Service**

* * *

## 1. Activity

_public class Activity extends [ContextThemeWrapper](https://developer.android.google.cn/reference/android/view/ContextThemeWrapper.html)  implements [LayoutInflater.Factory2](https://developer.android.google.cn/reference/android/view/LayoutInflater.Factory2.html),  [Window.Callback](https://developer.android.google.cn/reference/android/view/Window.Callback.html),  [KeyEvent.Callback](https://developer.android.google.cn/reference/android/view/KeyEvent.Callback.html),   [View.OnCreateContextMenuListener](https://developer.android.google.cn/reference/android/view/View.OnCreateContextMenuListener.html),  [ComponentCallbacks2](https://developer.android.google.cn/reference/android/content/ComponentCallbacks2.html)_

![](http://upload-images.jianshu.io/upload_images/2540993-ff352adc516a1cd5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

图1-继承关系

![](http://upload-images.jianshu.io/upload_images/2540993-f398eadb2835cf75.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
图2-activity生命周期

*   使用activity需要在`AndroidManifest.xml`中注册

```
  &lt;application
android:name=".application.FunnyApplication"//注册项目的Application类
      android:allowBackup="true"//可通过adb backup和adb restore来备份和恢复应用程序数据,默认为true；
      android:icon="@mipmap/ic_launcher"//app图标
      android:label="@string/app_name"//app名
      android:supportsRtl="true"//是否支持从右到左布局 api17（4.2时出现）
      android:theme="@style/app_theme"//设置主题
      /&gt;
       &lt;activity
              android:name=".activity.MainActivity"
              android:configChanges="orientation"/&gt;//设置方向
    &lt;/application&gt;
```

*   `onCreate(Bundle)`:在此方法中做一些初始化操作，初始化activity、
*  `setContentView(int)`加载布局、控件的初始化等(`findViewById(int)`)
*   `onStart()`：可见不可操作，显示界面
*   `onResume()`：可见可操作，此时的`activity`处于**栈顶**位置用户可以愉快的玩耍了
*   `onPause()`:可见不可交互，此时界面没有获取焦点（如在activity中弹出dialog、设备休眠也可从`onresume()`进入到 `onPause()`状态）,官方建议在这里保存状态信息（数据），通常用`contentprovider`来保存
*   `onStop()`：不可见,被覆盖或最小化
*   `onDestroy()`：销毁前调用的最后一个方法，可在此做一些解绑、资源回收等操作
*   `onRestart()`:重新返回，下一个调用的是`onStart()`方法
    当activity被强制回收了，在被杀死前会调用[onSaveInstanceState(Bundle)](https://developer.android.google.cn/reference/android/app/Activity.html#onSaveInstanceState(android.os.Bundle))方法，可以在此保存一些有用的信息
    
*   **获取返回值操作**
    Activity:A、B
    在**A**中获取**B**中返回的结果

        *   在**A**中跳转方法使用`startActivityForResult(Intent intent, int requestCode)`或`startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options)`
    *   在**B**中`finish()`前设置`setResult(int resultCode,Intent data);`或`setResult(int resultCode);`根据实际情况进行选择，可通过Intent传递数据，resultcode用于在A中判断使用
    *   在**A**中重写方法,B返回A时会调用此方法，接收B中传递数据

		```
		 protected void onActivityResult ( int  requestCode, int resultCode,  Intent data) {
		        super.onActivityResult(requestCode, resultCode, data);
		    }
		```
		
		*   **data持久化**

        *   保存持久化数据 :SQLite、SharedPreferences、File、Content Provider、网络

    ## 2.BrocastReceiver

    在Android中，Broadcast是一种广泛运用的在应用程序之间传输信息的机制。而BroadcastReceiver是对发送出来的 Broadcast进行过滤接受并响应的一类组件。

    下面将详细的阐述如何发送Broadcast和使用BroadcastReceiver过滤接收的过程：

    　　首先在需要发送信息的地方，把要发送的信息和用于过滤的信息(如Action、Category)装入一个Intent对象，然后通过调用 sendOrderBroadcast()或sendStickyBroadcast()方法，把 Intent对象以广播方式发送出去。

    　　当Intent发送以后，所有已经注册的BroadcastReceiver会检查注册时的IntentFilter是否与发送的Intent相匹配，若匹配则就会调用BroadcastReceiver的onReceive()方法。所以当我们定义一个BroadcastReceiver的时候，都需要实现onReceive()方法。

    　　注册BroadcastReceiver有两种方式:

    　　静态注册：在`AndroidManifest.xml`中用标签注册，并在标签内用标签设置过滤器。

```
receiver android:name = "myRecevice"    //继承BroadcastReceiver，重写onReceiver方法

  &lt; intent-filter 
    　&lt;action
    　　　android:name  = "com.dragon.net"&gt;
    　&lt;/action&gt; //使用过滤器，接收指定action广播
 &lt;/intent-filter&gt;
&lt;/ receiver  &gt;    
```

动态注册：
    一般：在onStart中注册，onStop中取消unregisterReceiver
```
IntentFilter intentFilter = new IntentFilter();
       intentFilter.addAction(String);   //为BroadcastReceiver指定action，使之用于接收同action的广播
        registerReceiver(BroadcastReceiver,intentFilter);//注册
    　　Intent intent = new Intent(actionString);//指定广播Action：
    　　intent.putExtra(  "msg"  ,   "我通过广播发送消息了"  );//通过Intent携带消息 
    　　Context.sendBroadcast(intent );//发送广播消息
```

## 3. Service

  
![](http://upload-images.jianshu.io/upload_images/2540993-4cc0f5af78054ff4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
  service继承关系  
  
*   两种启动方式
bindService、startService两种
startService这种service可以无限地运行下去，必须调用**stopSelf()**方法或者其他组件调用**stopService()**方法来停止它。当service被停止时，系统会销毁它。
bindService:被绑定的service是当其他组件（一个客户）调用**bindService()**来创建的。
客户可以通过一个**IBinder**接口和service进行通信。
客户可以通过 **unbindService()**方法来关闭这种连接。
一个service可以同时和多个客户绑定，当多个客户都解除绑定之后，系统会销毁service。生命周期随它绑定的组件而定
*   生命周期
在下图中，左侧的为startService方式启动Service的生命周期，右侧为bindService方式启动Service时的生命周期 ''

![](http://upload-images.jianshu.io/upload_images/2540993-8bdc1fcdc0cd5b76.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

  两种生命周期  
+  如果service是被开启的，那么它的活动生命周期和整个生命周期一同结束。
+  如果service是被绑定的，它们它的活动生命周期是在**onUnbind()**方法返回后结束。

## 4.ContentProvider
　ContentProvider：为存储和获取数据提供统一的接口。可以在不同的应用程序之间共享数据。Android已经为常见的一些数据提供了默认的ContentProvider
　1、ContentProvider使用表的形式来组织数据
　　 无论数据的来源是什么，ContentProvider都会认为是一种表，然后把数据组织成表格
　　2、ContentProvider提供的方法
　　 query：查询
　　 insert：插入
　　 update：更新
　　 delete：删除
　　 getType：得到数据类型
　　 onCreate：创建数据时调用的回调函数
　　3、每个ContentProvider都有一个公共的URI，这个URI用于表示这个ContentProvider所提供的数据。Android所提供的ContentProvider都存放在android.provider包当中

*   ContentProvider的内部原理
　　自定义一个ContentProvider，来实现内部原理
　　步骤：
　　1、定义一个CONTENT_URI常量(里面的字符串必须是唯一)
　　Public static final Uri CONTENT_URI = Uri.parse("content://com.WangWeiDa.MyContentprovider");
　　如果有子表，URI为：
　　Public static final Uri CONTENT_URI = Uri.parse("content://com.WangWeiDa.MyContentProvider/users");
　　2、定义一个类，继承ContentProvider
　　Public class MyContentProvider extends ContentProvider
　　3、实现ContentProvider的所有方法(query、insert、update、delete、getType、onCreate)

* * *

_正在持续更新中……_