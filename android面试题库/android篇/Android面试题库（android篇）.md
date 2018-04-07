#7、Android面试题库（android篇）
* **什么是ANR 如何避免它？**
答：在Android 上，如果你的应用程序有一段时间响应不够灵敏，系统会向用户显示一个对话框，这个对话框称作应用程序无响应（ANR：Application Not Responding）对话框。用户可以选择让程序继续运行，但是，他们在使用你的应用程序时，并不希望每次都要处理这个对话框。因此，在程序里对响应性能的设计很重要，这样，系统不会显示ANR 给用户。
    不同的组件发生ANR 的时间不一样，主线程（Activity、Service）是5 秒，BroadCastReceiver 是10 秒。
    **解决方案：**
    将所有耗时操作，比如访问网络，Socket 通信，查询大量SQL 语句，复杂逻辑计算等都放在子线程中去，然后通过handler.sendMessage、runonUITread、AsyncTask 等方式更新UI。无论如何都要确保用户界面操作的流畅度。
    如果耗时操作需要让用户等待，那么可以在界面上显示进度条。

*  View的绘制流程；自定义View如何考虑机型适配；自定义View的事件分发机制；View和ViewGroup分别有哪些事件分发相关的回调方法；自定义View如何提供获取View属性的接口；

*   Art和Dalvik对比；虚拟机原理，如何自己设计一个虚拟机(内存管理，类加载，双亲委派)；JVM内存模型及类加载机制；内存对象的循环引用及避免；

*   ddms 和 traceView的区别；

*   内存回收机制与GC算法(各种算法的优缺点以及应用场景)；GC原理时机以及GC对象；内存泄露场景及解决方法；

*  四大组件及生命周期；ContentProvider的权限管理(读写分离，权限控制-精确到表级，URL控制)；Activity的四种启动模式对比；Activity状态保存于恢复

* 什么是AIDL 以及如何使用；

*   请解释下在单线程模型中Message、Handler、Message Queue、Looper之间的关系；

* Fragment生命周期；Fragment状态保存；

* startActivityForResult是哪个类的方法，在什么情况下使用，如果在Adapter中使用应该如何解耦；

* AsyncTask原理及不足；ntentService原理；

* Activity 怎么和Service 绑定，怎么在Activity 中启动自己对应的Service；

*  请描述一下Service 的生命周期；

* AstncTask+HttpClient与AsyncHttpClient有什么区别；

* 如何保证一个后台服务不被杀死；比较省电的方式是什么；

* 如何通过广播拦截和abort一条短信；广播是否可以请求网络；广播引起anr的时间限制；

* 进程间通信，AIDL；

* 事件分发中的onTouch 和onTouchEvent 有什么区别，又该如何使用？

* 说说ContentProvider、ContentResolver、ContentObserver 之间的关系；

* 请介绍下ContentProvider 是如何实现数据共享的；

* Handler机制及底层实现；

* Binder机制及底层实现;

*  ListView 中图片错位的问题是如何产生的；

*   在manifest 和代码中如何注册和使用BroadcastReceiver；

*  说说Activity、Intent、Service 是什么关系；

* ApplicationContext和ActivityContext的区别；

* 一张Bitmap所占内存以及内存占用的计算；

* Serializable 和Parcelable 的区别；

* 请描述一下BroadcastReceiver；

* 请描述一下Android 的事件分发机制；

*   请介绍一下NDK；

*  什么是NDK库，如何在jni中注册native函数，有几种注册方式；

*   AsyncTask 如何使用；
   

*   对于应用更新这块是如何做的？(灰度，强制更新，分区域更新)；

*  混合开发，RN，weex，H5，小程序(做Android的了解一些前端js等还是很有好处的)；

*   什么情况下会导致内存泄露；

*   如何对Android 应用进行性能分析以及优化；

*   说一款你认为当前比较火的应用并设计(直播APP)；

*   OOM的避免异常及解决方法；

*   屏幕适配的处理技巧都有哪些；

*   两个Activity 之间跳转时必然会执行的是哪几个方法？答：一般情况下比如说有两个activity,分别叫A,B,当在A 里面激活B 组件的时候, A 会调用onPause()方法,然后B 调用onCreate() ,onStart(), onResume()。
    这个时候B 覆盖了窗体, A 会调用onStop()方法. 如果B 是个透明的,或者是对话框的样式, 就不会调用A 的onStop()方法。


##面试题：Service

![](http://upload-images.jianshu.io/upload_images/1685558-5b9c3263c0af2ea2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* 知道Service吗，它有几种启动方式？它们的区别？Service的onCreate回调函数可以做耗时的操作吗？如果需要做耗时的操作，你会怎么做？
是否知道IntentService，在什么场景下使用IntentService？

Service是一个专门在后台处理长时间任务的Android组件，它没有UI。它有两种启动方式，startService和bindService。
startService只是启动Service，启动它的组件（如Activity）和Service并没有关联，只有当Service调用stopSelf或者其他组件调用stopService服务才会终止。
bindService方法启动Service，其他组件可以通过回调获取Service的代理对象和Service交互，而这两方也进行了绑定，当启动方销毁时，Service也会自动进行unBind操作，当发现所有绑定都进行了unBind时才会销毁Service。
当然还有其他的区别，如两种调用对Service生命周期函数影响，面试官也可以就这个问题展开一下。

Service的onCreate是在主线程（ActivityThread）中调用的，耗时操作会阻塞UI

* 场景：如果一个应用要从网络上下载MP3文件，并在Activity上展示进度条，这个Activity要求是可以转屏的。那么在转屏时Actvitiy会重启，如何保证下载的进度条能正确展示进度呢？
没有Service概念的人，一般想出来的方案如下：

在转屏前将进度缓存，转屏后再读出来。
使用android:configChanges设置，让转屏时Activity不销毁和重建。
针对第1个方案，我会继续问他将进度值存在哪里？ 转屏的过程中，我们知道Activity的重建算是比较耗时的，会可能会有几百毫秒以上，那么这时候下载线程仍然在工作，进度肯定和保存时的进度不一致了，如何处理这个问题呢？

第2个方案，大家可以自己展开思考，实际的项目中可能会需要额外做一些事情来处理ContentView的横竖布局的问题。

如果使用Service来解决这个问题，看似是比较完美的，不过就会涉及Activity（UI）和Service的交互问题


##面试题： 用广播来更新UI界面好吗？
更新界面也分很多种情况，如果不是频繁地刷新，使用广播来做也是可以的。但对于较频繁地刷新动作，建议还是不要使用这种方式。广播的发送和接收是有一定的代价的，它的传输是通过Binder进程间通信机制来实现的（细心人会发现Intent是实现了Parcelable接口的），那么系统定会为了广播能顺利传递做一些进程间通信的准备。

除此之外，还可能有其他的因素让广播发送和到达是不准时的（或者说接收是会延时）。曾经看到有人在论坛上抱怨发几个广播都卡，Google的工程师是怎么混饭吃的。

这种情况可能吗？很可能，而且很容易发生。我们要先了解Android的ActivityManagerService有一个专门的消息队列来接收发送出来的广播，sendBroadcast执行完后就立即返回，但这时发送来的广播只是被放入到队列，并不一定马上被处理。当处理到当前广播时，又会把这个广播分发给注册的广播接收分发器ReceiverDispatcher，ReceiverDispatcher最后又把广播交给接Receiver所在的线程的消息队列去处理（就是你熟悉的UI线程的Message Queue）。

整个过程从发送--ActivityManagerService--ReceiverDispatcher进行了两次Binder进程间通信，最后还要交到UI的消息队列，如果基中有一个消息的处理阻塞了UI，当然也会延迟你的onReceive的执行。
* BroadcastReceiver的生命周期？
Receiver也是有生命周期的，而且很短，当它的onReceive方法执行完成后，它的生命周期就结束了。这时BroadcastReceiver已经不处于active状态，被系统杀掉的概率极高，也就是说如果你在onReceive去开线程进行异步操作或者打开Dialog都有可能在没达到你要的结果时进程就被系统杀掉。因为这个进程可能只有这个Receiver这个组件在运行，当Receiver也执行完后就是一个empty进程，是最容易被系统杀掉的。替代的方案是用Notificaiton或者Service（这种情况当然不能用bindService）。

做为Android四大组件之一的，广播被很多人所熟知，可算是一种非常方便的解耦组件的手段。常用的方式是直接调用Context的接口（sendBroadcast & sendOrderBroadcast）发送两类型的广播：

Normal broadcasts无序广播，会异步的发送给所有的Receiver，接收到广播的顺序是不确定的，有可能是同时。
Ordered broadcasts有序广播，广播会先发送给优先级高(android:priority)的Receiver，而且这个Receiver有权决定是继续发送到下一个Receiver或者是直接终止广播。

* 除了上面的两种广播外，还有其他类型的广播吗？

可以使用sendStickyBroadcast发送Sticky类型的广播。Sticky简单说就是，在发送广播时Reciever还没有被注册，但它注册后还是可以收到在它之前发送的那条广播。

* 有时候基于数据安全考虑，我们想发送广播只有自己（本进程）能接收到，那么该如何去做呢？
我会先想到权限，发送广播时限定有权限（receiverPermission）的接收者才能收到。但是我们知道APK太容易被反编译，注册广播的权限也只是一个字符串，并不安全。

然后可能使用Handler，没错，往主线程的消息池（Message Queue）发送消息，只有主线程的Handler可以分发处理它，广播发送的内容是一个Intent对象，我们可以直接用Message封装一下，留一个和sendBroadcast一样的接口。在handleMessage时把Intent对象传递给已注册的Receiver。
还有一个LocalBroadcastManager类，查了一下官方文档是Support V4包里的一个类，其实现方式也是使用Handler，思路也是一样的。

## 面试题： 怎么理解Activity的生命周期？

下面还是先过一下官网上的Activity生命周期图，注意几个框线其实代表又可以细分为不同的周期。

![](http://upload-images.jianshu.io/upload_images/1685558-d3176065dcf72d21.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


> 如果一个Activity在用户可见时才处理某个广播，不可见时注销掉，那么应该在哪两个生命周期的回调方法去注册和注销BroadcastReceiver呢？

Activity 的**可见生命周期**发生在 onStart调用与 onStop调用之间。在这段时间，用户可以在屏幕上看到 Activity 并与其交互。我们可以在 onStart中注册一个 BroadcastReceiver以监控影响 UI 的变化，并在用户无法再看到您显示的内容时在 onStop中将其取消注册。

如果对方回答是在onResume和onPause方法中，那么你可以去引导对方看看在这两个方法有什么不好的地方。

> 如果有一些数据在Activity跳转时（或者离开时）要保存到数据库，那么你认为是在onPause好还是在onStop执行这个操作好呢？

这题的要点和上一题是一样的，onPause较容易被触发，所以我们在做BroadcastReceiver注销时放在onStop要好些。onPause时Activity界面仍然是可见的，如弹出一个Dialog时。但在保存数据时，放在onPause去做可以保证数据存储的有效性，如果放在onStop去做，在某些情况下Activity走完onPause后有可能还没顺利走到onStop就被系统回收了。

但要注意在onPause中要非常迅速地执行完所需操作，不然会影响到下一个Activity的生命周期函数的调用。

> 简单说一下Activity A启动Activity B时，两个Activity生命周期的变化。

当一个 Activity 启动另一个 Activity 时，它们都会发生生命周期转变。第一个 Activity 暂停然后停止（但如果它在后台仍然可见，则不会停止，比如B是半透明的），系统会创建另一个 Activity。 如果这两个Activity 共用保存数据到文件或者数据库，必须要注意，在创建第二个 Activity 前，第一个 Activity 不会完全停止。更确切地说，启动第二个 Activity 的过程与停止第一个 Activity 的过程存在重叠。

以下是当 Activity A 启动 Activity B 时一系列操作的发生顺序：

> Activity A 的 onPause方法执行。
> Activity B 的 onCreate、onStart和 onResume方法依次执行。
> 然后，如果 Activity A 在屏幕上不再可见，则其 onStop方法执行。

您可以利用这种可预测的生命周期回调顺序管理从一个 Activity 到另一个 Activity 的信息转变。 例如，如果您必须在第一个 Activity 停止时向数据库写入数据，以便下一个 Activity 能够读取该数据，则应在 onPause而不是 onStop执行期间向数据库写入数据。

##面试题： 如何判断Activity是否在运行？
<div class="show-content">

我一般面试技术分两方面了解面试者，一是测重问面试者细节的地方，看对方是否真如简历上所说对XX“精通”、“熟悉”、“有一定的见解”，有实践经验的积累。别一种是侧重考察对方对问题（可以是未知问题）的理解和解决问题的思路。

## 面试题： 如何判断Activity是否在运行？

如下这场景我相信很多人都遇到过，这段话也是从某个帖子截取出来的：

> 从Activity A 启动一个线程去进行网络上传操作，在A中设立一个回调函数，当上传操作完成以后，在A的这个回调函数中会弹出一个对话框，用来显示回调信息。可是当上传的过程还在进行的时候，我按下back键，A的activity 被销毁了，可是那个上传的线程还在进行，当那个线程结束后，本来应该在A中弹出一个对话框，可是由于A已经不存在了，系统就会报错提示，“将对话框显示在不存在的页面上”，然后程序就挂掉了。

我看到过很多人用Handler来充当上面所提到的“回调函数”，即工作线程完成工作后，通过主线程的Handler处理UI更新，如弹出提示Dialog。可能有些人没有弄明白，Activity都被系统销毁了，工作线程怎么还能调它的变量呢？其实所谓的Activity销毁只是不再受系统的AMS控制，但Activity这个对象的实例还是存在于内存中的，具体什么时候真正把这个对象实例也销毁（回收）了，就要看内存回收机制了，哪怕是这个实例没有可达的引用了也不一定会马上回收。

针对这种用Handler更新UI的情况，我们需要在操作UI前判断一下此Activity是否已被销毁。很多人可能都用过isFinishing来判断，用多了就会发现好象不太准，为什么呢，看一下它的源代码：

```
    /**
     * Check to see whether this activity is in the process of finishing,
     * either because you called {@link #finish} on it or someone else
     * has requested that it finished.  This is often used in
     * {@link #onPause} to determine whether the activity is simply pausing or
     * completely finishing.
     *
     * @return If the activity is finishing, returns true; else returns false.
     *
     * @see #finish
     */
    public boolean isFinishing() {
        return mFinished;
    }

    /**
     * Returns true if the final {@link #onDestroy()} call has been made
     * on the Activity, so this instance is now dead.
     */
    public boolean isDestroyed() {
        return mDestroyed;
    }
```

而mFinished是在finish()中被赋值的，也就是说只有通过调用finish()结束的Activity，mFinished的值才会被置为true。所以有时候Activity的生命周期没有按我们预想的来走时（如内存紧张时），会出现判断出错的情况。

Android源码可以使用这个网站查询：[androidxref](http://androidxref.com/)

看看Google工程师是怎么判断的（来源于Android源码中的Call应用，AsyncTask中的onPostExecute片段）：

```
    @Override
    protected void onPostExecute(Void result) {
        final Activity activity = progressDialog.getOwnerActivity();

        if (activity == null || activity.isDestroyed() || activity.isFinishing()) {
            return;
        }

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
```

多了一个isDestroyed()的判断。

### 小结

如果对方没听说过isFinishing函数，那可以让他从自己的角度看如何解决这个问题，正好可以看看他的逻辑思维是否清晰合理。工作中往往会遇到，一些求职者由于之前是做其他方面刚转Android开发，对Android的了解还不够，但有很强理解和学习能力，通过引导发现他可以快速的得到合理的解决方案的话，我一般都很乐意要这样的人。


## 面试题：自定义View的状态是如何保存的？

2009年冬天，北京寒风刺骨，我们公司组织到北京进行为期一周的Android开发培训，讲师来自荷兰的Hello9培训机构。让我印向深刻的是，说着极易让人打瞌睡的北欧英文的讲师，在给我们讲EditText控件时，说到在Activity转屏时EditText是不会保存状态的（即在EditText中输入字符，然后转屏，重建Activity后EditText之前输入的字符没有被保存）。为表示他的正确，他特意演示了一下，这一演示发现状态被保存了！后来他解释说是，在早期的SDK版本（1.6以前）确实是不会保存的，之后Android做了改进。

在我问面试者关于自定义View的问题时，常常会问他们关于保存View的状态的问题，如果对方不大清楚怎么做，我会认为面试者在自己定View上的经验不够，因为至少要知道可以在View的onSaveInstanceState中保存数据吧。

Android有一套标准的做法，做过自定义View的人很容易遇到这个问题，因为Activity转屏，或Home键到后台很容易在被系统销毁，恢复时我们肯定是希望看到View保持之前状态。

> 提示：系统内存紧张时会主动销毁这类Activity，做为开发我们可以利用DDMS的stop process模拟这一动作而不必等到内存紧张时，如下图所示：
> 
> ![](http://upload-images.jianshu.io/upload_images/1685558-2385f882b1be5a67.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
> 
> 红色的STOP图标可以杀掉当前选择的进程
这个标准的做法可以随便从一个Android自带的控件中看到，如TextView的源代码( [SavedState extends BaseSavedState](http://androidxref.com/4.4.2_r2/xref/frameworks/base/core/java/android/widget/TextView.java) )。

```
    /**
     * User interface state that is stored by TextView for implementing
     * {@link View#onSaveInstanceState}.
     */
    public static class SavedState extends BaseSavedState {
        int selStart;
        int selEnd;
        CharSequence text;
        boolean frozenWithFocus;
        CharSequence error;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(selStart);
            out.writeInt(selEnd);
            out.writeInt(frozenWithFocus ? 1 : 0);
        ......
```

BaseSavedState是View的一个内部静态类，从代码上我们也很容易看出是把控件的属性（如selStart）打包到Parcel容器，Activity的onSaveInstanceState、onRestoreInstanceState最终也会调用到控件的这两个同名方法。

Parcel相关的问题以后专门再讲，现在我们可以焦点先放在状态保存上，先看一下Activity的状态如何保存的：

![](http://upload-images.jianshu.io/upload_images/1685558-8eaf71b2bbf765b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

     Activity 状态
**注：**无法保证系统会在销毁Activity前一定调用onSaveInstanceState，例如用户使用“返回” 按退出 Activity 时，因为用户的行为是在显式关闭 Activity，所以不会调用onSaveInstanceState。

> 如果系统调用onSaveInstanceState，那么它是在onStop还是在onPause之前执行呢？

可以肯定的是它会在调用 onStop之前，但是是不是在onPuase之前就不能确认了，要看情况，官方文档在说明这个执行顺序时用了“可能”这个词。

## 小结

Activity类的onSaveInstanceState默认实现会恢复Activity的状态，默认实现会为布局中的每个View调用相应的 onSaveInstanceState方法，让每个View都能保存自身的信息。

这里需要注意一个细节：想要保存View的状态，需要在XML布局文件中提供一个唯一的ID（android:id），如果没有设置这个ID的话，View控件的onSaveInstanceState是不会被调用的。


## 面试题：通过new创建的View实例它的onSaveStateInstance会被调用吗？

这里再强调一下，自定义View控件的状态被保存需要满足两个条件：

> 1.  View有唯一的ID；
> 2.  View的初始化时要调用**setSaveEnabled(true)** ；

简单看一下View状态保存和读取的调用过程：

![](http://upload-images.jianshu.io/upload_images/1685558-a3eb9f89e8995ebf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

里面的SparseArray(完整的参数是：SparseArray<Parcelable> )是一个KEY-VALUE的Map，KEY当然就是View的ID了。所以细看一下源码的调用过程，你一下就理解为什么一定要给View调置一个唯一的ID了。

那好，现在回答上这个网友的问题“通过new创建的View实例它的onSaveStateInstance会被调用吗？”，答案还是一样的如果设置了ID就会。其实我们在XML文件中配置的布局和属性最终都是通过LayoutInflater中的inflate方法去加载，由它去创建各个View的实例（还是用new），并根据XML文件中的属性设置相关的值。

> 我们再展开一下，如果我们定义了一个自义的Layout，在同一个界面中引用了两次这个自定义的Layout（如下图的myLayout1 & myLayout2），那么它的状态会发生什么情况呢？
![](http://upload-images.jianshu.io/upload_images/1685558-972d27df77d5137f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们会发现两个Layout的状态被保存了，但Layout中的每个子View状态是相同的，被最后一个保存的View覆盖了。这也是为什么我们要强调View的ID要唯一的原因。

如何解决这个问题呢？留给大家去展开吧，你是面试官的话，也可以留给来面试的人，不过这里太细节了建议最好做为附加题让对方试试（答不上不扣分）。






## 面试题：能讲讲Android的Handler机制吗？

Android应用程序是通过消息来驱动的，系统为每一个应用程序维护一个消息队例（MessageQueue），应用程序的主线程不断地从这个消息队例中获取消息（Looper），然后对这些消息进行处理（Handler），这样就实现了通过消息来驱动应用程序的执行。

Handler在整个Android应用中占有很重要的地方，所以面试时我们经常要考查一下面试者是否了理解它的原理，并且能够将大体的流程表述清楚，沟通和表达能力有时比技术能力更重要。

要讲清楚Android中的消息机制，肯定要先表述一下和Handler相关的一些类：

> **Message**：消息分为硬件产生的消息(如按钮、触摸)和软件生成的消息；
> **MessageQueue**：消息队列的主要功能向消息池投递消息(MessageQueue.enqueueMessage)和取走消息池的消息(MessageQueue.next)；
> **Handler**：消息辅助类，主要功能向消息池发送各种消息事件(Handler.sendMessage)和处理相应消息事件(Handler.handleMessage)；
> **Looper**：不断循环执行(Looper.loop)，按分发机制将消息分发给目标处理者。

Handler相关类的代码量并不大，建议大家都去看一下，网上也有很多介绍和分析这些源码的文章，大家自己Google一下。大家把代码过了一遍后，会更加深对整个过程的理解，讲起来就从容多了。不建议大家为了面试去背书。

面试时，如果一个人可以清楚的表达Handler的运行机制，那么我们接下来会主要问一下一些实际开发中注意的地方。比如会问在一个工作线程中创建自己的消息队例应该怎么做？

其实就是想从侧面验证他是否正的了解，是否知道要调用Looper.prepare（在每个线程只允许执行一次）。

或者再问问是否用过HandlerThread，它有什么优缺点等。

### 注意：Handler可能会引起的内存泄露

在Activity中像这样创建一个Handler再正常不过了。

```
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };
```

但是，其实上面的代码可能导致内存泄露，当你使用Android lint工具的话，会得到这样的警告:

> In Android, Handler classes should be static or leaks might occur, Messages enqueued on the application thread’s MessageQueue also retain their target Handler. If the Handler is an inner class, its outer class will be retained as well. To avoid leaking the outer class, declare the Handler as a static nested class with a WeakReference to its outer class

有兴趣的可以细看一下这篇译文：[《Android中Handler引起的内存泄露》](http://droidyue.com/blog/2014/12/28/in-android-handler-classes-should-be-static-or-leaks-might-occur/)

## 结论

诸如Handler这类耳熟能详的概念，但其实用起来又不复杂，面试时一般会更在意对方的表达上，看对方是否能用语言有效的组织语句。最后针对一个问题，还是要用一点小的细节验证对方是否正的做过。有些网友可能会觉得是被故意刁难，但如果面试官只提出一个问题，你说了答案后他就嗯一下就立即问下一主题，没有和你就这个问题再扩展一下，你是否也会觉得他什么都不懂，也会质疑他能否辨别出面试者的真实水平？



## 面试题：两个Activity之间如何传递参数？

在Android应用中，Activity占有极其重要的地位，Activity间的跳转更是加常便饭。即然跳转（界面切换）不可避免，那么在两个Activity之间传递参数就是一个常见的需求。大多数时候，我们也就传递一些简单的int，String类型的数据，实际中也有看到传递List和Bitmap的。

那么我们先回答这个题，如何传递参数：

> 使用Intent的Bundle协带参数，就是我们常用的Intent.putExtra方法。

做为面试官，紧接着可以问：除了传递基本类型外，如何传递自定义的对象呢？

这个问题就是想引出Android的Parcelable。一般很多面试者都有用过传递实现了Serializable接口的自定义对象的经验，因为这个很简单，加句代码就搞定了。而Parcelable的实现要多一些代码，典型的写法如下：

```
public class MyParcelable implements Parcelable {
     private int mData;

     public int describeContents() {
         return 0;
     }

     public void writeToParcel(Parcel out, int flags) {
         out.writeInt(mData);
     }

     public static final Parcelable.Creator<MyParcelable> CREATOR
             = new Parcelable.Creator<MyParcelable>() {
         public MyParcelable createFromParcel(Parcel in) {
             return new MyParcelable(in);
         }

         public MyParcelable[] newArray(int size) {
             return new MyParcelable[size];
         }
     };

     private MyParcelable(Parcel in) {
         mData = in.readInt();
     }
}
```

那我们为什么要考察对方会不会用Parcelable呢？先看一下这Parcelable和Serializable的区别：

> Serializalbe会使用反射，序列化和反序列化过程需要大量I/O操作，Parcelable自已实现封送和解封（marshalled &unmarshalled）操作不需要用反射，数据也存放在Native内存中，效率要快很多。

有人比较过它们两个的效率差别：

![](http://upload-images.jianshu.io/upload_images/1685558-09fbc40517e7e07b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

不同类型的数据不一定差据这么大，但却很直观的展示了Pacelable比Serializable高效。

> 有时面试官还可以追问一下：Parcelable和Parcle这两者之间的关系。

Parcelable 接口定义在封送/解封送过程中混合和分解对象的契约。Parcelable接口的底层是Parcel容器对象。Parcel类是一种最快的序列化/反序列化机制，专为Android中的进程间通信而设计。该类提供了一些方法来将成员容纳到容器中，以及从容器展开成员。

> 现在我们知道了如何传递自定义的对象，那么在两个Activity之前传递对象还要注意什么呢？

一定要要注意对象的大小，Intent中的Bundle是在使用Binder机制进行数据传递的，能使用的Binder的缓冲区是有大小限制的（有些手机是2M），而一个进程默认有16个binder线程，所以一个线程能占用的缓冲区就更小了（以前做过测试，大约一个线程可以占用128KB）。所以当你看到“The Binder transaction failed because it was too large.”这类TransactionTooLargeException异常时，你应该知道怎么解决了。

> 因此，使用Intent在Activity之间传递List和Bitmap对象是有风险的。

面试官可以就这个问题再展开，看面试者如何解决。

还有一个要注意的：因为android不同版本Parcelable可能不同，所以不推荐使用Parcelable进行数据持久化。之前我有过一次，将Android的PackageInfo进行持久化到数据库，结果用户升级Android系统后，再从数据库解封PackageInfo时应用就Crash了。

## 结论

对于初级的程序员来说，只要能抓住老鼠，白猫或者黑猫甚至是小狗都是没有区别的。但对于应用的流畅和体验来说，100毫秒和1000毫秒是有很大区别的。很多程序员眼里无关紧要的差别，最终在用户那儿会被几倍十几倍的放大，老板也会因为用户的投述而斥责你。因为总会有用户在用性能很差的手机，总有用户手机的使用情况很复杂（内存紧张，网络复杂等等），总有用户本人就很奇葩不会按你指定的套路出拳！当你鄙视老板不懂代码的艺术时，老板也会鄙视你不懂用户不懂细节的重要性，活该你一辈子做程序员。

所以，在能使用的Parcelable的地方，请不要贪图简便直接Serializable，实在懒的话也可以试试用插件自动生成Pracelabel的模板代码：**[android-parcelable-intellij-plugin](https://github.com/mcharmas/android-parcelable-intellij-plugin)** 





## 面试题：如何理解Android中的Context，它有什么用？

有些东西，大家天天都能看到，但并不一定了解和在意它。在Android开发中，加载资源，启动一个新的Activity，获取系统服务，获取数据库路径，创建一个View等都会使用到Context。Context就像一个长着大众脸的同学，你天天和它在一起上课，但却说不出它是谁。

官方文档对于Context的解释：

> Interface to global information about an application environment. This is an abstract class whose implementation is provided by the Android system. It allows access to application-specific resources and classes, as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc.

中文翻译“Context”为“上下文，背景，环境，语境”，有点抽象。对照上面的英文，也很容易理解它的意思：Context提供了一个应用的运行环境，通过这个上下文应用才可以访问资源，才能完成和其他组件、服务的交互。它就是一个调用者和具体实现的桥接。

再看看这个图，可以直观的了解Context相关类的继承关系：

![](http://upload-images.jianshu.io/upload_images/1685558-15d5535e992f66d7.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
    图片来自网络

具体的实现，大家可以看一看ContextImpl这个类的[源码](http://androidxref.com/6.0.1_r10/xref/frameworks/base/core/java/android/app/ContextImpl.java)，理一下它和LoadApk的关系，大家就会很容易明白Context是如何处理资源的。

> 注意：上图中的mPackageInfo是一个LoadApk对像，这个LoadApk是一个hide类型的类，所以你在SDK中是找不到它的。

但是，面对天天见的“大众脸同学”Context，很多人更想知道，理解它有什么实际的意义。做应用开发，我们只要会使用它不就行了吗？

我先问一个简单的问题：

> Application（或者Service）和Activity都可以调用Context的startActivity方法，那么在这两个地方调用startActivity有区别吗？

如果你曾经遇到过，就会知道在Application（或者Service）需要给Intent设置Intent.FLAG_ACTIVITY_NEW_TASK才能正常启动Activity，这就会引出Activity的Task栈问题，以后再做分析。

理解Context，对于我经历的项目来说，最有用的就是对于插件框架的开发了。如果有面试官问你:

> Context的实例是什么时候创建的？一个应用里面会有几个Context的实例？

对于一般的应用来说，你会觉得这两个问题很无聊。但如果你需要做插件开发，上面的问题就变成是很关键的问题了。你的插件框架会是一个小型的Android Framework层，你当然得自己处理插件的Application和Activity创建，那么你肯定要解决好这两个问题。详情可以查看ActivityThread这个类的[源码](http://androidxref.com/6.0.1_r10/xref/frameworks/base/core/java/android/app/ActivityThread.java)。

也就是说，如果我要招一个做插件开发的人，我肯定要了解一下他对这些问题的看法。或者一个人的简历上说他对Android Framework层很精通，也可以拿这些问题检测一下。

## 小结

有网友问“面试官是怎么考虑求职者的经验、学历、编程水平”这些方面的，其实这个问题不能脱离实际的公司和项目来回答。我只能说几个场景，有些公司有人才培养计划项目也不紧张，那么他们在招人时是以培养和贮备为目的，会更重视面试者的理解和学习能力。但如何一家公司急切需要人进来解决问题，他们就会更在乎你的项目经验了，最好是直接招以前就做过类似项目的。如果一家公司只是需要码农来搬代码，那么只要不是太差的，他们会更看重性价比。

所以有公司关注这些对Context或Framework方面的理解的面式题，一是他们应用可能遇到了一些问题，需要一些对机制比较了解的开发来解决；二是想通过这类问题，考察面试者是否真如简历上般资深，因为他们相信做多了项目的人，很容易遇到机制方面的问题（如Dex的65535方法数限制）。



## 面试题：如何优化ListView的性能？

在回答这个问题前，我认为很有必要和大家讲几点和getView相关的问题。我们设置或者优化ListView的性能很多时候都是在getView中完成的，反过来说就是很多性能问题都是由于没有正确使用getView造成的。

```
public View getView(int position, View convertView, ViewGroup parent)
```

所以我们不妨先思考一下如下的几个问题：

> 在一次显示ListView的界面时，getView会被执行几次？
> 
> 每次getView执行时间应该控制在多少毫秒之内？
> 
> getView中设置listener要注意什么？

首先我们要知道ListView的ItemView有一个复用机制，简单看如下图所示，ListView中有一个RecycleBin类复负回收不可见且可能被再次使用的ItemView，由ScrapView存储。

![](http://upload-images.jianshu.io/upload_images/1685558-73c9e629b9b0e273.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

所以我在们设置Listener进就要注意，使用convertView时需要重新设置一个Listener，保括一些数据也需要重设置，不然可能会显示之前那个ItemView在回收前的状态。

在绘制ListView前往往要计算它的高度，所以一个ListView界面上可以看到6个ItemView，但是getView的执行次数却有可能是12次，多出的次数用来计算高度（这个可以通过设置ListView的height为0来避免）。所以要避免在getView中进行逻辑运算，两次计算同一逻辑完全是浪费。

每个getView的执行时间更是少得可怜，很多人可能对这个时间没有概念，我可以简单的给大算一下：

> 1秒之内屏幕可以完成30帧的绘制，人才能看到它比较流畅（苹果是接近60帧，高于60之后人眼也无法分辨）。
> 每帧可使用的时间：1000ms/30 = 33.33 ms
> 每个ListView一般要显示6个ListItem，加上1个重用convertView：33.33ms/7 = 4.76ms

即是说，每个getView要在4.76ms内完成工作才会较流畅，但是事实上，每个getView间的调用也会有一定的间隔（有可能是由于handler在处理别的消息），UI的handler处理不好的话，这个间隔也可难会很大（0ms-200ms）。结论就是，留给getView使用的时间应该在4ms之内，如果不能控制在这之内的话，ListView的滑动就会有卡顿的现象。

了解了这几个问题，现在我们回来这次主要考查的面试题上，如何进行ListView的性能优化，让它滑动更加流畅。大家一般常用如下方法：

> 1.  重用ConvertView;
> 2.  使用View Holder模式；
> 3.  使用异步线程加载图片（一般都是直接使用图片库加载，如Glide, Picasso）；

我认为这些是面试者必备的知识点，如果连这些都说不清楚的话，也没有必要再深入问了。针对面试者的回答，可以适当选一两点追问一下，看是否真正明白。如：ViewHolder为什么能够起到优化性能的作用？

除此之前还有一些优化建议：

> 1.  在adapter的getView方法中尽可能的减少逻辑判断，特别是耗时的判断；
> 2.  避免GC（可以从LOGCAT查看有无GC的LOG）；
> 3.  在快速滑动时不要加载图片；
> 4.  将ListView的scrollingCache和animateCache这两个属性设置为false（默认是true）;
> 5.  尽可能减少List Item的Layout层次（如可以使用RelativeLayout替换LinearLayout，或使用自定的View代替组合嵌套使用的Layout）；

关于第4点，发现在一些型号的手机（如华为的P7）上特别管用，当其也优化都做完之后，有无这两项设置滑动的卡顿情况有明显不同。

```
<Listview
 android:scrollingCache="false" 
 android:animationCache="false"
```

## 小结

关于ListView有很多方面可以考察面试者，因为它实在是用的太频繁了，双方都能对某个问题点进行展开。如果一个面试者都没有做过ListView优化，那么如果不是他写的代码太少就是他使用ListView加载的数据太简单（可能只有几十项），其本上没有其他选项。所以这一题是很能看出面试者的项目经验和实际的开发水平，属于面试必考题之一。


## 面试题：如何实现应用内多语言切换？

我们知道Android的多语言实现很简单，可以在不同的语言环境下使用不同的资源。在不同的res/value-xx下放置不同语言的strings.xml实现字符的本地化，而这个value-xx目录的选择是根据Resource中的Configuration.Locale这项的值来决定的。如zh中文，就会选择value-zh目录，如果没有匹配到（即APK中没有value-zh目录）就使用默认的value目录中的字符资源。

> 其实最终实现字符串的选择都是在Assets这个类中，通过Native的方法来加载相应的字符串资源。

然而，我们还是会有一些业务场景需要不根据Android系统的Locale配置就改变应用的语言。实现的方式也很简单，直接调用Android开放的接口Resources.updateConfiguration：

```
    public static void changeSystemLanguage(Context context, String language) {
        if (context == null || TextUtils.isEmpty(language)) {
            return;
        }

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(language)) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else {
            config.locale = new Locale(language);
        }
        resources.updateConfiguration(config, null);
    }
```

上面的代码，我们可以在应用内通过language的值指定是显示哪种语言，当然language值我们需要保存在Preferences或者数据库中。

好像很简单，我们的项目为什么还会出现问题呢？而且大家都不知道如何下手，因为在Android N之前的版本都是可以正常切换语言的。后来我跟了一下，发现在MainActivity和SplashActivity这些Activity有继承了自已扩展的BaseActivity，而这个BaseActivity有这样一段代码：

```
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
```

config.setToDefaults会将Locale的值设为null，而再调用updateConfiguration可能会根据Android系统的语言重新设置Resources中的Locale。好吧，只是假设，还没有看到Android N的源代码。不过去掉这段代码后，在Android N（Preview）手机上切换语言正常了。

## 小结

今天遇到的问题，是以前遗留的代码埋下的坑终于暴露出来，也是这个项目缺乏代码审查（Code Review）机制的结果。找了几个人也无法说清覆写getResources这个方法的用意，最终也只能按历史问题处理了，是历史总有一些说不清楚的事，对吧。

回到这个面试题，现在你知道了可以在应用内切换语言（当然也可以修改Configuration的其他值），那么你有没有想过，如果不知道这个updateConfiguration的存在，你会怎么实现这个需求呢？或者说没有人和你说过updateConfiguration，你能找到它吗？



## 面试题：在项目中使用AsyncTask会有什么问题吗？


记得2012年的时候，在MTK公司内部的一个Work Shop上我分享了对AsyncTask的理解，听众都很有兴趣地参与讨论使用AsyncTask的问题所在。因为UI线程阻塞的问题，每一个Android应用开发都会遇到要开工作线程中去做耗时间的操作，相对于new Thread再使用Handler更新UI的话，直接使用AsyncTask无疑是最经济方便的选择。

关于AsyncTask有很多是非，如最早的128数量限制，后来MTK的同事还发现AsyncTask中的Handler Bug：在工作线程中先使用AsyncTask会造成它里面的Handler是指向工作线程的Looper（如果这个工作线程没有创建Looper，程序会崩溃），而这个Handler是静态的，会造成之后无法在onPostExecute方法中更新UI。在Android 4.1版本Google修改了这个BUG，把AsyncTask的初始化放到ActivityThread.main中去创建，以确保它的静态Handler指向主线程的Looper。

```
AsyncTask.init();
```

那我们考查AsyncTask会问些什么呢？得先问问会不会用吧，看看知不知道有onProgressUpdate方法。

其次问一下是怎么理解AsyncTask的机制，有没有看过它的源代码？
这个问题主要看对方是否对Android的东西有好奇心，会主动去看AsyncTask的源码，或者能大体地讲清楚AsyncTask的原理。一般有好奇心的同学都比较善长学习，善长学习的人都能比较快融入团队。

> AnsycTask执行任务时，内部会创建一个进程作用域的线程池来管理要运行的任务，也就就是说当你调用了AsyncTask.execute()后，AsyncTask会把任务交给线程池，由线程池来管理创建Thread和运行Therad。最后和UI打交道就交给Handler去处理了。

我们在实际的项目中，还需要关注一些问题：

> 线程池可以同时执行多少个TASK？

Android 3.0之前（1.6之前的版本不再关注）规定线程池的核心线程数为5个（corePoolSize），线程池总大小为128（maximumPoolSize），还有一个缓冲队列（sWorkQueue，缓冲队列可以放10个任务），当我们尝试去添加第139个任务时，程序就会崩溃。当线程池中的数量大于corePoolSize，缓冲队列已满，并且线程池中的数量小于maximumPoolSize，将会创建新的线程来处理被添加的任务。如下图会出现第16个Task比第6－15个Task先执行的情况。

![](http://upload-images.jianshu.io/upload_images/1685558-2615462ab9cbcdfd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 多个AsyncTask任务是串行还是并行？

从Android 1.6到2.3(Gingerbread) AsyncTask是并行的，即上面我们提到的有5个核心线程的线程池（ThreadPoolExecutor）负责调度任务。从Android 3.0开始，Android团队又把AsyncTask改成了串行，默认的Executor被指定为SERIAL_EXECUTOR。

```
    /**
     * An {@link Executor} that executes tasks one at a time in serial
     * order.  This serialization is global to a particular process.
     */
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();
```

从它的说明也可以看出是串行的。如需要并行，可以通过设置executeOnExecutor(Executor)来实现多个AsyncTask并行。

```
    public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor exec,
            Params... params) {
```

> AsyncTask容易引发的Activity内存泄露

如果AsyncTask被声明为Activity的非静态的内部类，那么AsyncTask会保留一个对创建了AsyncTask的Activity的引用。如果Activity已经被销毁，AsyncTask的后台线程还在执行，它将继续在内存里保留这个引用，导致Activity无法被回收，引起内存泄露。

当然，最后少不了问一句：“你在项目中，会用什么方案来替换AsyncTask呢？”

## 小结

感觉对AsyncTask的使用有点“成也萧何败萧何”的味道，它简单的解决了UI和后台线程交互的问题，但如果忽视它的限制（缺陷）和各版本不一致的线程池方式，可能会达不到预想的效果。最后发现没有使用过AsyncTask会被鄙视，如果你在实际项目中使用了AsyncTask也会被鄙视。不过，学习它对理解Android的机制和线程池的使用还是很的意义的，所以强烈建议大家读一下它的[源码](http://androidxref.com/6.0.1_r10/xref/frameworks/base/core/java/android/os/AsyncTask.java)。





## 面试题：修改SharedPreferences后两种提交方式有什么区别？

如果说程序可以简单理解成“指令和数据的集合”，那么你在任何平台上编程都难以离开数据存储，在Android平台上自然也不会例外。说到数据的存储，对于Key-Value对应的数据存取，Android提供SharedPreferences的方式可以进行方便的操作。大家也都觉得它的使用很简单，但是有时候简单的地方也会发生问题，而且你很难查觉到问题根源在这个地方。

SharedPreferences类是一个接口类，真正的实现类是SharedPreferencesImpl。修改SharedPreferences需要获取它的Editor，在对Editor进行put操作后，最后通过commit或者apply提交修改到内存和文件。当然有了两种都可以提交的方法，肯定要区别一下的。从实现类SharedPreferencesImpl的[源码](http://androidxref.com/5.0.0_r2/xref/frameworks/base/core/java/android/app/SharedPreferencesImpl.java)上看也很容易看出两者的区别：

> commit这种方式很常用，在比较早的SDK版本中就有了，这种提交修改的方式是同步的，会阻塞调用它的线程，并且这个方法会返回boolean值告知保存是否成功（如果不成功，可以做一些补救措施）。
> 而apply是异步的提交方式，目前Android Studio也会提示大家使用这种方式。

还有一点用得比较少的，就是SharedPreferences还提供一个监听接口可以监听SharedPreferences的键值变化，需要监控键值变化的可以用registerOnSharedPreferenceChangeListener添加监听器。

```
public interface SharedPreferences {
    /**
     * Interface definition for a callback to be invoked when a shared
     * preference is changed.
     */
    public interface OnSharedPreferenceChangeListener {
        void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key);
    }
```

### 多进程操作和读取SharedPreferences的问题

前段时间，项目组里发现一个偶现的问题，从Http明明获取了正确的数据保存到SharedPreferences，但立即再从SharedPreferences读取这个值时发现是初始值。开始大家一直把精力放在Http的请求上，最后才发现是SharedPreferences多进程间数据共享会导致的问题。

在SDK 3.0及以上版本，可以通过Context.MODE_MULTI_PROCESS属性来实现SharedPreferences多进程共享。如下设置：

```
    public static SharedPreferences getSharedPreferences(String name) {
        if (null != context) {
            if (Build.VERSION.SDK_INT >= 11) {
                return context.getSharedPreferences(name, Context.MODE_MULTI_PROCESS);
            } else {
                return context.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
        }

        return null;
    }
```

本来以为通过MODE_MULTI_PROCESS属性使用SharedPreferences就可以实现不同时程间共享数据，但是在真正使用中确发现有会有一定概率出现这个取值出错（变为初始值）问题。

最后发现在官网上Google也在SDK 6.0的版本将这个MODE_MULTI_PROCESS标识为deprecated（不赞成使用）。目前来说，越来越多的项目在不断的膨胀，为了降低单个进程的内存占用率，使用"android:process"配置一些组件在单独的进程中运行已经是司空见惯了，所以大家在遇到自己的项目有多进程时，要注意一下SharedPreferences的问题。

## 小结

在一个进程中，SharedPreference往往建单个实例就可以了，一般不会出现并发冲突，如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。

因为SharedPreferences在多进程方面的问题，大家也可以思考下能不能自己实现一个加强版的SharedPreferences解决这些问题，网上也有一些开源的替代方案，如Github上的[tray](https://github.com/grandcentrix/tray)。（建议大家先想一下，再看这个项目。）



## 面试题：有使用过ContentProvider码？能说说Android为什么要设计ContentProvider这个组件吗？


ContentProvider应用程序间非常通用的共享数据的一种方式，也是Android官方推荐的方式。Android中许多系统应用都使用该方式实现数据共享，比如通讯录、短信等。但我遇到很多做Android开发的人都不怎么使用它，觉得直接读取数据库会更简单方便。

那么Android搞一个内容提供者在数据和应用之间，只是为了装高大上，故弄玄虚？我认为其设计用意在于：

> 1.  封装。对数据进行封装，提供统一的接口，使用者完全不必关心这些数据是在DB，XML、Preferences或者网络请求来的。当项目需求要改变数据来源时，使用我们的地方完全不需要修改。
> 2.  提供一种跨进程数据共享的方式。

应用程序间的数据共享还有另外的一个重要话题，就是数据更新通知机制了。因为数据是在多个应用程序中共享的，当其中一个应用程序改变了这些共享数据的时候，它有责任通知其它应用程序，让它们知道共享数据被修改了，这样它们就可以作相应的处理。

ContentResolver接口的notifyChange函数来通知那些注册了监控特定URI的ContentObserver对象，使得它们可以相应地执行一些处理。ContentObserver可以通过registerContentObserver进行注册。

> 既然是对外提供数据共享，那么如何限制对方的使用呢？

android:exported属性非常重要。这个属性用于指示该服务是否能够被其他应用程序组件调用或跟它交互。如果设置为true，则能够被调用或交互，否则不能。设置为false时，只有同一个应用程序的组件或带有相同用户ID的应用程序才能启动或绑定该服务。

对于需要开放的组件应设置合理的权限，如果只需要对同一个签名的其它应用开放content provider，则可以设置signature级别的权限。大家可以参考一下系统自带应用的代码，自定义了signature级别的permission：

```
        <permission android:name="com.android.gallery3d.filtershow.permission.READ"
                    android:protectionLevel="signature" />

        <permission android:name="com.android.gallery3d.filtershow.permission.WRITE"
                    android:protectionLevel="signature" />

        <provider
            android:name="com.android.gallery3d.filtershow.provider.SharedImageProvider"
            android:authorities="com.android.gallery3d.filtershow.provider.SharedImageProvider"
            android:grantUriPermissions="true"
            android:readPermission="com.android.gallery3d.filtershow.permission.READ"
            android:writePermission="com.android.gallery3d.filtershow.permission.WRITE" />
```

如果我们只需要开放部份的URI给其他的应用访问呢？可以参考Provider的URI权限设置，只允许访问部份URI，可以参考原生ContactsProvider2的相关代码（注意path-permission这个选项）：

```
        <provider android:name="ContactsProvider2"
            android:authorities="contacts;com.android.contacts"
            android:label="@string/provider_label"
            android:multiprocess="false"
            android:exported="true"
            android:grantUriPermissions="true"
            android:readPermission="android.permission.READ_CONTACTS"
            android:writePermission="android.permission.WRITE_CONTACTS">
            <path-permission
                    android:pathPrefix="/search_suggest_query"
                    android:readPermission="android.permission.GLOBAL_SEARCH" />
            <path-permission
                    android:pathPrefix="/search_suggest_shortcut"
                    android:readPermission="android.permission.GLOBAL_SEARCH" />
            <path-permission
                    android:pathPattern="/contacts/.*/photo"
                    android:readPermission="android.permission.GLOBAL_SEARCH" />
            <grant-uri-permission android:pathPattern=".*" />
        </provider>
```

> ContentProvider接口方法运行在哪个线程中呢？

ContentProvider可以在AndroidManifest.xml中配置一个叫做android:multiprocess的属性，默认值是false，表示ContentProvider是单例的，无论哪个客户端应用的访问都将是同一个ContentProvider对象，如果设为true，系统会为每一个访问该ContentProvider的进程创建一个实例。

这点还是比较好理解的，那如果我要问每个ContentProvider的操作是在哪个线程中运行的呢（其实我们关心的是UI线程和工作线程）？比如我们在UI线程调用getContentResolver().query查询数据，而当数据量很大时（或者需要进行较长时间的计算）会不会阻塞UI线程呢？

要分两种情况回答这个问题：

> 1.  ContentProvider和调用者在同一个进程，ContentProvider的方法（query/insert/update/delete等）和调用者在同一线程中；
> 2.  ContentProvider和调用者在不同的进程，ContentProvider的方法会运行在它自身所在进程的一个Binder线程中。

但是，注意这两种方式在ContentProvider的方法没有执行完成前都会blocked调用者。所以你应该知道这个上面这个问题的答案了吧。

也可以看看[CursorLoader](http://androidxref.com/6.0.1_r10/xref/frameworks/base/core/java/android/content/CursorLoader.java)这个类的源码，看Google自己是怎么使用getContentResolver().query的。

> ContentProvider是如何在不同应用程序之间传输数据的？

这个问题点有些深入，大家要对Binder进程间通信机制比较了解。因为之前我们有提到过一个应用进程有16个Binder线程去和远程服务进行交互，而每个线程可占用的缓存空间是128KB这样，超过会报异常。ContentResolver虽然是通过Binder进程间通信机制打通了应用程序之间共享数据的通道，但Content Provider组件在不同应用程序之间传输数据是基于匿名共享内存机制来实现的。有兴趣的可以查看一下老罗的文章[Android系统匿名共享内存Ashmem（Anonymous Shared Memory）简要介绍和学习计划](http://blog.csdn.net/luoshengyang/article/details/6651971)

![](http://upload-images.jianshu.io/upload_images/1685558-982648eb6de31f91.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 

用个图总结一下

## 小结

初学开发或者开发经验少的人，往往不喜欢在写代码时思考如何应对以后可能产生的变化和相关接口的扩展能力，所以他们往往看不到ContentProvider的巨大好处。我觉得一般常见到的组件或概念，大家不一定需要每个都去偿试写写代码Demo（当然这样最好），但我认为对它们仍然需要有足够的重视，要去理清它的计设思路和使用场景。这样对你是极好的，之后你在面对问题时才会有多种解决方案的选择机会。

人生，比的不就是谁有更多的选择吗？





## 面试题：如何处理线程同步的问题？

最近领导让我在插件框架上加上一个接口，在宿主应用中可以调用所有的插件去清除自己的缓存数据，当完成所有插件的清除任务后再执行下一步操作。领导就是需求嘛，领导改变注意那是再正常不过的事了，但是不是领导的需求人员有可能变多了会有人身危险。我平时和同事吹牛时，常和他们说工作的七字真言：

“不急、不怕、不要脸”（抄自冯唐）

我认为对于软件开发来说，这句话很值得品味。在需求改变时，不要急于修改代码，而是要先做一个全盘的考虑，有些时候你还没考虑好，需求方就说不要做了。在接到新任务或者遇到困难时不要怕，没什么可怕的，你不难受你就不会有提高。最后，不要脸更是程序员最需要的，这个自己体会。

有可能很多人对插件并不了解，不过没关系，这个需求简单地说就是主线程要等待多个子线程全部完成工作后，才能继续执行。

说到多线程的同步问题，面试多的人应该很容易被面试官问：Object的wait和notify/notifyAll如何实现线程同步？

> 在Object.java中，定义了wait(), notify()和notifyAll()等接口。wait()的作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。而notify()和notifyAll()的作用，则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。

wait和yield（或sleep）的区别？

> 1.  wait()是让线程由“运行状态”进入到“等待(阻塞)状态”，而yield()是让线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权。
> 2.  wait()是会线程释放它所持有对象的同步锁，而yield()方法不会释放锁。

而我接触到的很多情况是：问线程同步的问题，大多数人基本上只知道synchronized。

要搞清线程的同步问题，大家要先了解一下“对象的同步锁”，这个留给大家自己去看吧，这里不做展开。我们回到新接到的这个需求上来，这个场景其实挺合适做为一个面试题的。

如何实现呢？我想到一个简单的方法就是用CountDownLatch。

CountDownLatch：一个同步辅助类（大名鼎鼎的java.util.concurrent包），在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。

用给定的任务数初始化CountDownLatch，一个线程工作完成（任务成功或者失败都算工作完成）就调用 countDown() 方法，当计数到达零之前，await 方法会一直受阻塞。当计数器为零时，会释放所有等待的线程，await后的代码将被执行。

> CountDownLatch计数无法被重置。如果需要重置计数，请考虑使用 CyclicBarrier。

还有其他的实现方式吗？这个是肯定的。比如，真接上Thread.jion，代码难看是会难看点，但也能完成这个需求。

我还查到一种方式是使用java.util.concurrent.ExecutorService的awaitTermination阻塞主线程，等待线程池的所有线程执行完成。需要设置一个超时时间的参数，如果超时则awaitTermination返回false，如果线程池中的线程全部执行完成，返回true。

## 小结

因为现在有很多开源的框架或者代码库，帮我们解决了很多底层诸如网络请求、线程池管理的问题，使得很多情况下我们都不怎么接触到线程同步的问题。不过还有很有必要抽时间来学习一些线程同步的知识，对我们提高并发编程的能力很有帮助。

如果大家有更好的方式实现我开头提到的需求，可以回帖一起讨论一下。



### 面试题：Activity的启动模式（launchMode）有哪些，有什么区别？
2010年从韩国回来时，我也是信心爆满，感觉三星的Android项目都能做下来了，Android的开发水平那是杠杠的。有一次想跳槽，面试一家公司时就被问了Activity启动模式的问题，微胖的面试官问我怎么看待四种启动模式，我吧嗒吧嗒后，面试官接着问我Launcher这个应用的Home界面（一般是指Launcher.java）用的是哪种模试。

我自信的回答用singleInstance，要不是面试官早有准备，估计他都要被我的自信弄得要开始怀疑人生。我的相法很简单，认为它全局只有一个实例而且应该只有一个实例，用singleInstance最好。

当我回来查询Launcher的源代码时发现使用的是SingleTask模式。之后虽然拿到了offer，但我仍然为这个问题耿耿于怀。当我后来到MTK公司工作时，才对Android的四种模式有了更深入的理解。
这应该是一道很虐人的面试题，很多人都答不上来，很多人根本就没有用过。当我发现在被我面试的人中有80%的比例对它不了解时，我找过一些同事讨论是否还有在面试中考查这个问题的必要，得到的回答是“程序员何苦为难程序员”！

因为很多程序员都认为这个启动模式没有多大用处。好吧，我用一个实际中很容易遇到的问题来引出它有多么有用。

> 很多人在使用startActivityForResult启动一个Activity时，会发现还没有开始界面跳转本身的onActivityResult马上就被执行了，这是为什么呢？

遇到过吧，我见过很多人为了这个问题抓耳挠腮的。在Activity.java的startActivityForResult方法上看一下官方的说明吧：

```
     * <p>Note that this method should only be used with Intent protocols
     * that are defined to return a result.  In other protocols (such as
     * {@link Intent#ACTION_MAIN} or {@link Intent#ACTION_VIEW}), you may
     * not get the result when you expect.  For example, if the activity you
     * are launching uses the singleTask launch mode, it will not run in your
     * task and thus you will immediately receive a cancel result.
```

很多人出现这个问题，确实是因为startActivityForResult启动的Activity设置了singleTask的启动模式。但是，除了这种情况还有可能会马上执行吗？

有，而且很多。如下面表格，左边第1列代表MainActivity的启动模式，第一行代表SecondActivity（即要startActivityForResult启动的Activity）的启动模式，打叉代表在这种组合下onActivityResult会被马上调用。
|  | stand | singleTop | singleTask | singleInstance |
| --- | --- | --- | --- | --- |
| stand | √ | √ | x | x |
| singleTop | √ | √ | x | x |
| singleTask | √ | √ | x | x |
| singleInstance | x | x | x | x |

好在幸运的是，Android在5.0及以后的版本修改了这个限制。也就是说上面x的地方全部变成了√。

那么在Android 5.0后，还会有这个问题吗？

还是会的。如在Intent中设置了FLAG_ACTIVITY_NEW_TASK再startActivityForResult，即使是标准的启动模式仍然会有这个问题。

```
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
```

Log如下：

```
07-12 14:21:14.849 20774-20774/net.goeasyway.test I/MainActivity: onCreate
07-12 14:21:14.875 20774-20774/net.goeasyway.test I/MainActivity: onResume
07-12 14:21:19.995 20774-20774/net.goeasyway.test I/MainActivity: onPause
07-12 14:21:19.995 20774-20774/net.goeasyway.test I/MainActivity: onActivityResult requestCode=1 resultCode=0
07-12 14:21:19.996 20774-20774/net.goeasyway.test I/MainActivity: onResume
07-12 14:21:19.996 20774-20774/net.goeasyway.test I/MainActivity: onPause
07-12 14:21:20.005 20774-20774/net.goeasyway.test I/SecondActivity: onCreate
07-12 14:21:20.018 20774-20774/net.goeasyway.test I/SecondActivity: onResume
```

> 注意：MainActivity的onResume也会被触发。因为onActivityResult被执行时，它会重新获得焦点。很多人也会遇到onResume被无故调用，也许就是这种情况。

所以，最终我们发现只要是不和原来的Activity在同一个Task就会产生这种立即执行onActivityResult的情况，从原代码也可以得到验证，详情查看[ActivityStackSupervisor.java](http://androidxref.com/6.0.1_r10/xref/frameworks/base/services/core/java/com/android/server/am/ActivityStackSupervisor.java#1882) 。

```
        if (r.resultTo != null && (launchFlags & Intent.FLAG_ACTIVITY_NEW_TASK) != 0
                && r.resultTo.task.stack != null) {
            // For whatever reason this activity is being launched into a new
            // task...  yet the caller has requested a result back.  Well, that
            // is pretty messed up, so instead immediately send back a cancel
            // and let the new task continue launched as normal without a
            // dependency on its originator.
            Slog.w(TAG, "Activity is launching as a new task, so cancelling activity result.");
            r.resultTo.task.stack.sendActivityResultLocked(-1,
                    r.resultTo, r.resultWho, r.requestCode,
                    Activity.RESULT_CANCELED, null);
            r.resultTo = null;
        }
```

### 原因

其实上面代码中的英文注解也说得很清楚了，Android认为不同的Task之间对这种要求返回结果的启动方式会产生一些依赖（对Task），所以干脆简单粗暴在跳转前直接返回RESULT_CANCELED结果。

我们还是用一个例子简单解释一下，如下图，有两个任务栈（stack），处于前可视状态的是“Back Stack”也叫返回栈，处理后台的是“Background Task”。

![](http://upload-images.jianshu.io/upload_images/1685558-06e4e92ccf90220e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

当“Activity 2”通过startActivityForResult启动“Activity Y”时，“Background Task”中的Activity会被压入返回栈的栈顶。这种情况下，如果没有在跳转前直接返回RESULT_CANCELED给“Activity 2”，那么按Back键，应该要跳转到“Activity X”，而按Back键“Activity Y”就会调用finish会发送Result给启动它的“Activity 2”。这时就很难搞清楚，到底是“Activity 2”还是“Activity X”应该获得焦点了，会产生一些混乱或是违反的原有的一些约定。

### 小结

关于启动模式的问题，其实我开始写这个系统的文章时就想介绍它的，不过发现它的水实现太深了，需要用比较长的篇幅才能说明清楚。今天也只是通过一个实际中容易碰到的问题引起大家的关注，也同时引出了“任务”和“返回栈”。

所以，就让程序员多为难程序员一次，进一步的说明请听下回分解。




### 面试题： Activity的启动模式有哪些，有什么区别？

大大咧咧的东北人Timothy和我在两个公司都做过同事，虽然同为程序员，但Timothy一直在沟通和应酬方面展示出他强于一般程序员的能力。2013年的时候，他选择辞职跨界到菲律宾的马尼拉卖光盘，就是那种用来刻录的空白光盘，虽然我们很少使用了，但在东南亚还很有市场。一个月大概能卖出一两百万张光盘，这么几年下来不知道卖出去的光盘连起来能不能绕地球一圈?

今天问了一下Timothy不当程序员的感受，“做程序员太累了，主要也是自己不喜欢编程，现在自由多了。”Timothy在程序员35岁坎之前解决了他的问题，直接由程序员转做Boss了。

当问他还对以前面试Android工作时被问到的问题有印象吗？Timothy能想起来的也就是这一道“程序员何苦为难程序员”的Activity启动模式相关的问题。

要理解Activity的四种启动模式，我们得来先说说Activity任务和返回栈的事。强烈建议大家可以先读一下官方的文档[Tasks and Back Stack](https://developer.android.com/guide/components/tasks-and-back-stack.html)，官方已经翻译成中文了，不存在阅读障碍（有可能需要代理或者VPN才能登录此网站）。

简单说，Task是为了完成某个工作的一组相关联的Activity的集合。这些Activity可以来自不同的APK（即可以在不同的进程），为了方便大家更直观感受一下，可以使用adb命令查看一下当前运行的Task：

> adb shell dumpsys activity activities

![](http://upload-images.jianshu.io/upload_images/1685558-33f8e40af172bc9f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

注意其中的几个关键字：

> Stack
> TaskRecord
> ActivityRecord

用图形来表示如下：

![](http://upload-images.jianshu.io/upload_images/1685558-dcd915f6a866e783.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
"Hist"代表Task中的ActivityRecord，可以理解成对应某个实际的Activity。
"Stack #0"表示mHomeStack（ActivityStack类），保存了Launcher相关的Activity的Task。
"Stack #1"表示mFocusStack（ActivityStack类），用于保存Launcher除外的其他应用的Activity组成的Task。

这两个Stack由ActivityStackSupervisor负责组织管理，在Android 4.4之前的版本是没有ActivityStackSupervisor这个类的，也没有"Stack #"的划分，AMS直接管理Task的列表。

> Android也提供了ActivityManger.getRunningTasks()的接口，可以在得到RunningTaskInfo的列表（当前Android设备正在运行着的Task）。从RunningTaskInfo中又可以进一步得到更多的相关信息。

### 四种启动模式

理解了这些相关的结构，我们再来看四种启动模式就简单多了，说白了这些启动模式就是设置Activity如何被组织到相关的任务栈中。从字面上也很好解释每种模式的意思。

> 在配置Activity的属性时设置android:launchMode，有如下四种模式供选择：
> standard
> singleTop
> singleTask
> singleInstance

正如文档[Tasks and Back Stack](https://developer.android.com/guide/components/tasks-and-back-stack.html)也提到的，我们还要注意下面这几个Intent标签的用法：

FLAG_ACTIVITY_NEW_TASK
FLAG_ACTIVITY_CLEAR_TOP
FLAG_ACTIVITY_SINGLE_TOP

如我常会问面试者一个问题：

> 当前应用有两个Activity A和B，B的android:launchMode设置了singleTask模式，A是默认的standard，那么A startActivity启动B，B会新启一个Task吗？如果不会，那么startActivity的Intent加上FLAG_ACTIVITY_NEW_TASK这个参数会不会呢？

如果认真阅读了Tasks and Back Stack文档的“处理关联”这个章节的话，应该能回答这个问题。关联（taskAffinity）也是Activity的一个属性，默认值是应用的包名。

设置了"singleTask"启动模式的Activity，它在启动的时候，会先在系统中查找属性值affinity等于它的属性值taskAffinity的任务存在；如果存在这样的任务，它就会在这个任务中启动，否则就会在新任务中启动。

当Intent对象包含FLAG_ACTIVITY_NEW_TASK标记时，系统在查代时仍然按Activity的taskAffinity属性进行匹配，如果找到一个Task的taskAffinity与之相同，就将目标Activity压入此Task栈中，如果找不到则创建一个新的Task。

> 注意：设置了"singleTask"启动模式的Activity在已有的任务中已经存在相应的Activity实例，再启动它时会把这个Activity实例上面的Activity全部结束掉。

因为这几种模式在面试题中太过出名了，但实际上真正理解和使用过的人并不多，往往用如下的问题就很容易区分出来：

> 1.  设置为singleTask的启动模式，当Activity的实例已经存在时，再启动它，它的那么回调函数会被执行？我们可以在哪个回调中处理新的Intent协带的参数？（通过startActivity(Intent)启动）
> 2.  或者问设置为singleTop的启动模式，当Activity的实例已经存在于Task的栈顶，我们可以在哪个回调中处理新的Intent协带的参数？（在当前Activity中从通知栏点击再跳转到此Activity就是这种在栈顶的情况）

这两个问题就是想看看面试者是否真正的做过这样的设置，或者是否知道有**onNewIntent**这个回调函数的存在。

### 引申

上面提到了通知栏，那么再说一个场景，在项目中常遇到一个需求就是在通知栏中使用PendingIntent跳转到相关的Activity。但这个Activity往往是根据通知的内容的具体的Activity，通知来的时候有可能应用已经被KILL掉了，这时跳转这个具体内容的DetailActivity后，我们希望按Back键能回退到应用的主界面（MailActivity），你会怎么做呢？

在DetailActivity中onBackPressed做判断？

如果没有很好的解决方案的话，大家可以看看：TaskStackBuilder。

### 小结

希望这两篇文章没有“为难”到各位，不过我想大家以后遇到Activity跳转和栈管理的问题时会优先想到启动模式和Intent可协带的Flag，通过一定的组合就可以轻松的解决的实际的问题。至于各种奇葩的问题，那就具体问题再具体分析，至少你知道了这些相关的概念查找分析问题也会事半功倍。

关于程序员的“35岁坎”（业界就是这样流传的），我们都需要各自好好思考一下。如果一样东西我们学过、使用过，但当我们不在这个行业时，这个当年费尽辛苦学来的东西还能记住什么呢？会不会也只是很少的一部份，只是当年让我们为难的痛点呢？当有一天Android被淘汰了，或者自己转行了，那么从Android这个平台上我们学习到了什么呢？什么是会变的，什么又是不变的呢？

我想不变的东西，才是值得我们仔细思考的。