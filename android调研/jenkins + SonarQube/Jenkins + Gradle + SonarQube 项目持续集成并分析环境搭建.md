%%:uuid=170725174533001
#Jenkins + Gradle + SonarQube 项目持续集成并分析环境搭建
##jenkins简单介绍
***
        Jenkins 是一个可扩展的持续集成引擎。
**主要用于**

        1.持续、自动地构建/测试软件项目。
        2.监控一些定时执行的任务。
**优势**

        1.软件构建自动化 ：配置完成后，CI系统会依照预先制定的时间表，或者针对某一特定事件，对目标软件进行构建
        2.构建可持续的自动化检查 ：CI系统能持续地获取新增或修改后签入的源代码，也就是说，当软件开发团队需要周期性的检查新增或修改后的代码时，CI系统会不断确认这些新代码是否破坏了原有软件的成功构建。这减少了开发者们在检查彼此相互依存的代码中变化情况需要花费的时间和精力
        3. 构建可持续的自动化测试 ：构建检查的扩展部分，构建后执行预先制定的一套测试规则，完成后触发通知(Email,RSS等等)给相关的当事人。
        4.生成后后续过程的自动化 :当自动化检查和测试成功完成，软件构建的周期中可能也需要一些额外的任务，诸如生成文档、打包软件等

##jenkens安装
[前往官网](https://jenkins.io/download/),选择其中的.war文件进行下载。
**安装方式 | 必须安装jdk环境**
1.必须事先安装Tomcat服务器，然后将.war文件放到webapps目录下，重启Tomcat就可以了。
2.直接进入cmd，使用命令
```
java -jar jenkins.war
```
使用这种方式进行安装，使用的是jenkins内置的服务器。
安装成功（cmd会出现类似jenkins is running）后，然后在浏览器输入http://localhost:8080
如果出现“网络无法运作”，请检查当前是否使用的代理服务器，应该转为“直接连接”
网页加载成功后，出现一下界面
![](C:\Users\peiyu_wang\Desktop\2.png)
在框中输入控制台输出的密钥
![](C:\Users\peiyu_wang\Desktop\3.png)
如果是通过Tomcat安装，密钥在webapp.jenkins\secrets\initialAdminPassword文件中
之后就是注册用户名
注册成功并登陆后，便出现一下界面
![](C:\Users\peiyu_wang\Desktop\4.png)
安装推荐插件
![](C:\Users\peiyu_wang\Desktop\5.png)
安装完成后，进行环境的配置
***
1.点击系统管理
![](C:\Users\peiyu_wang\Desktop\6.png)
2.点击Global Tool Configuration
![](C:\Users\peiyu_wang\Desktop\7.png)
3.配置jdk，配置git
![](C:\Users\peiyu_wang\Desktop\8.png)
4.配置gradle
![](C:\Users\peiyu_wang\Desktop\9.png)
jenkins集成的项目使用的gradle版本，这里必须配置，因为自动构建需要和项目使用相同版本的gradle
5.点击Save
6.安装Android SDK
点击系统设置
![](C:\Users\peiyu_wang\Desktop\10.png)
7.配置Android Home全局属性，全局属性对所有项目生效
![](C:\Users\peiyu_wang\Desktop\11.png)
8.点击Save
9.安装Android Line插件，点击管理插件
![](C:\Users\peiyu_wang\Desktop\7.png)
***
创建Job
***
1.
点击新建
![](C:\Users\peiyu_wang\Desktop\6.png)
2.填写名称，选择自由风格的软件项目
![](C:\Users\peiyu_wang\Desktop\12.png)
3.简单介绍下面内容
![](C:\Users\peiyu_wang\Desktop\13.png)
>>1.丢弃旧的构建
    1.1保持构建的天数，表示构建的内容在1天内不被删除，操作1天后删除
    1.2表示允许保持构建的最大数量，当超过这个数量时，最前面的构建会被删除
2.参数化构建，就是在构建时传入一下参数，这些参数会被配置到项目中，并且可以影响后面的构建步骤。详情请点击有点的？

4.配置项目Git地址，可以选择构建的分支
![](C:\Users\peiyu_wang\Desktop\14.png)
5.点击Add填写Git账号和密码
![](C:\Users\peiyu_wang\Desktop\15.png)
6.构建触发器，就是什么时候触发构建
![](C:\Users\peiyu_wang\Desktop\16.png)
>>有多种触发形式
这里介绍第三种，定时构建
可以规定每天，或者某个时候进行定时构建。日程表中的内容是安装一个格式编写的，详情可以点击右边的?

7.点击增加构建步骤，选择gradle script
![](C:\Users\peiyu_wang\Desktop\17.png)
8.可以选择invoke Gradle,此时是从刚才配置的Gradle版本选择一个，但是必须和项目是同一个版本,或者选择Use Gradle Wrapper，此时的Wrapper location,就是项目的根目录，有build.gradle,gradlew的根目录。Tasks就是clean build这类gradle命令，可以连着写。
推荐在命令后加上
```
--stacktrace --debug
```

例如
```
clean assembleRelease --stacktrace --debug
```
![](C:\Users\peiyu_wang\Desktop\18.png)
但是当我们要使用SonarQube时，只能选择Use Gradle Wrapper
配置如下，其中Root Build script就是要构建的根目录。此时选择的时andfix模块
![](C:\Users\peiyu_wang\Desktop\19.png)
9.设置构建完成之后的操作
![](C:\Users\peiyu_wang\Desktop\20.png)
设置邮件提醒。但是使用邮件还得配置其他，这里不做介绍。
10.点击Save
***
在主界面点击刚才创建的项目
![](C:\Users\peiyu_wang\Desktop\22.png)
点击立即构建。
如果出现失败，点击#*，进入后点击Console Output查看控制台信息
一般错误：
1.Gradle版本不一致，更改Gradle版本
2.项目使用的SDK版本在刚才配置的sdk路径中没有，安装相应的sdk版本
3.项目中使用的sdk路径找不到，这时可以更改项目根目录下的local.properties文件，将sdk路径改为正确的路径

构建成功后，图片时蓝色的。
然后进入sonarQube服务器，便可以看到刚才自动构建的项目。
##**注意**
1.如果项目中的sonarLine绑定了Sonar服务器，要解绑
![](C:\Users\peiyu_wang\Desktop\26.png)
2.配置项目的gradle.properties
![](C:\Users\peiyu_wang\Desktop\25.png)
3.确保git上的项目有2中的配置

##SonarQube分析配置
1.安装Android Lint
![](C:\Users\peiyu_wang\Desktop\27.png)
2.设置Android Lint的父类为Sonar Way
![](C:\Users\peiyu_wang\Desktop\28.png)
此时Android Lint就有了Android Lint检测规则，也有了原来Sonar Way的检测规则。
3.设置Android Lint为默认的质量配置，之后的自动构建使用Android Lint
4.更新的项目情况，在SonarQube可以清晰的看到
![](C:\Users\peiyu_wang\Desktop\30.png)
