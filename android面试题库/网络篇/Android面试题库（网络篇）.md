#2、Android面试题库（网络篇）

@(笔记)

* * *
#### 概述 

Android网络应用的相关知识，离不开最基础的计算机网络知识。Android的虚拟机基于JVM所以编程语言无限相似于Java，当然类库也可以在Android上应用。所以Java上网络相关的知识同样适应于Android。接下来由浅入深的来学习网络知识。因为网络上有一些知名博主的技术底蕴与文采功力远胜于本人，所以基础知识，我就引导大家去自己学习了。记住，一定理解明白基础。

如果你仅在大学稀里糊涂学习了网络基础课程，工作后又没有从事专业的网络相关工作，大概对网络基础知识忘记的差不多了。认真看吧。

#### 互联网协议 

[协议入门一](http://www.ruanyifeng.com/blog/2012/05/internet_protocol_suite_part_i.html)

[协议入门二](http://www.ruanyifeng.com/blog/2012/06/internet_protocol_suite_part_ii.html)

相信上面的文章一定或多或少解决了你多年一直难以理解的概念，也清楚了互联网协议的整个通信过程。由基础到中级，咱们来看看接下来世界上最流行的网络协议

互联网通信我们知道必须知道对方的IP地址，那么通常我们是根据域名比如 www.google.com来搜索进到网站内的，这是如何做到的？  [DNS](http://www.ruanyifeng.com/blog/2016/06/dns.html)  

互联网是开放式平台，每一块都涉及到安全问题，那么我们遇到的[DNS的相关安全问题有哪些？](http://www.jianshu.com/p/eff9553c8b64) 我们项目使用的是[DnsPod](https://www.dnspod.cn/) 

#### HTTP

[http协议入门](http://www.ruanyifeng.com/blog/2016/08/http.html)

通过这篇文章你一定会明白了Http协议的前世今生，明白了http/1.x  、SPDY、http/2 这三个家伙的关系了，还记得okhttp有多强大么？支持http/2 ! 

做Android的同学肯定知道以下三种网路框架

volley          (HurlStack，模板模式的应用,支持HttpUrlConnection, HttpClient, OkHttp，)

 retrofit        (基于okhttp)

android-async-http （基于httpclient）

OkHttp 是基于http协议封装的一套请求客户端，虽然它也可以开线程，但根本上它更偏向真正的请求，跟HttpClient, HttpUrlConnection的职责是一样的.

所以现在不要再问，网络框架是Volley好，还是OkHttp好。 

你现在可以Volley+OkHttp去自由实现网络框架了！

**okhttp是java平台目前最好的对http协议的封装客户端.**

volley是一个简单的异步http库，仅此而已。缺点是不支持同步，这点会限制开发模式；不能post大数据，所以不适合用来上传文件。

android-async-http。与volley一样是异步网络库，但volley是封装的httpUrlConnection，它是封装的httpClient，而android平台不推荐用HttpClient了，所以这个库已经**不适合android平台**了。

okhttp是**高性能**的http库，支持同步、异步，而且实现了spdy、http2、websocket协议，api很简洁易用，和volley一样实现了http协议的缓存。picasso就是利用okhttp的缓存机制实现其文件缓存，实现的很优雅，很**正确，**反例就是UIL（universal image loader），自己做的文件缓存，而且不遵守http缓存机制。

retrofit在okhttp基础之上做的封装，项目中可以直接用。retrofit的设计非常经典，里面有众多设计模式的巧妙应用，值得反复研究。[Retrofit分析](http://www.jianshu.com/p/45cb536be2f4)

#### HTTPS

我们都知道17年1月1日起，iOS9规定禁止忽略[ATS](http://www.cocoachina.com/ios/20150821/13140.html) . iOS切换必须升级到https上，随之Android也跟着升级到https了，我们不光要升级成功到https，还有必要了解一些基础知识，最后告诉大家如何升级到https 。来看

[HTTPS协议概述](http://www.ruanyifeng.com/blog/2014/02/ssl_tls.html)

通过这篇文章，明白了 HTTPS的通信过程，也知道了SSL和TLS的关系了。https加密通信握手那块儿如果明白了就忽略这篇更加易懂的文章吧

[图解SSL/TLS协议](http://www.ruanyifeng.com/blog/2014/09/illustration-ssl.html)

如果看完上面2篇文章还是无法在大脑有一个清晰的Https握手过程，我建议你继续看。来看下[常见的https七大误解](http://www.ruanyifeng.com/blog/2011/02/seven_myths_about_https.html)

在之前问行业内朋友，为什么不升级到HTTPS，回答总说是因为它慢，到底慢多少，慢到哪里，没人能够说清楚  [HTTPS延迟内幕](http://www.ruanyifeng.com/blog/2014/09/ssl-latency.html)

通过以上四篇基础文章的积累，我们来看下[如何从HTTP升级到HTTPS](http://www.ruanyifeng.com/blog/2016/08/migrate-from-http-to-https.html)

有了以上网络基础知识，我们来看下Android上现在最强大的OkHttp 

#### OKHTTP

首先看如何应用它  [OKHTTP官方文档](http://www.jianshu.com/p/2c7ecd37920f)

想更近一步了解使用请看  [OKHTTP完全使用教程](http://www.jianshu.com/p/ca8a982a116b)

根据使用去跟踪学习原理 [OKHTTP原理综述](http://www.jianshu.com/p/aad5aacd79bf)

当然想读懂原理还需要其他知识比如常见的设计模式[工厂模式](http://www.jianshu.com/p/da06e305dd82)，[代理模式](http://www.jianshu.com/p/acd7b097df91)，观察者模式，生产/消费者模式，命令模式，等等