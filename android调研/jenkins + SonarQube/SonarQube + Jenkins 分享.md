%%:uuid=170726102319001
#SonarQube
###简介
SonarQube是一个开源的代码质量管理平台，可以通过插件机制集成不同的测试工具、代码分析工具以及继续集成工具。比如pmdcpd、checkstyle、findbugs、jenkins等。具体的代码分析工具介绍,见http://tianya23.blog.51cto.com/1081650/415146.
但是SonarQube并不是简单的将各种质量检测工具的结果直接展示给用户，而是通过不同的插件算法对这些结果进行在加工，最终以量化的方式来衡量代码质量。
###特点
1.支持25+种编程语言
2.多维度分析项目质量
>>1.Architecture&Design
2.Duplications
3.Unit tests
4.Complexity
5.Potential bugs
6.Coding rules
7.Comments

###架构图
![](C:\Users\peiyu_wang\Desktop\sonarQube.png)
###安装
见另一个附件《SonarQube Win安装部署与实践》
***
#jenkins
###[介绍](https://gwpost.gitbooks.io/jenkins-the-definitive-guide/content/Introducting%20Jenkins/jie_shao.html)
jenkins是一个持续集成管理平台，每一次集成都是通过自动化的构建来验证，包括自动编译、发布和测试。简单的说，就是定时的或者指定触发条件的自动执行我们定义好的命令或脚本对项目进行相关的处理，并将结果反馈给开发者。
###特点
1.易安装，无需数据库
2.易配置，所有配置都是通过web页面
3.变更支持，Jenkins能从代码仓库（Subversion/CVS）中获取并产生代码更新列表并输出到编译输出信息中
4.集成E-mail，将结果反馈
###安装
见另一个附件《Jenkins + Gradle + SonarQube 项目持续集成并分析环境搭建》
#jenkins + SonarQube
实现自动分析项目，并将分析结果自动更新到SonarQube，每次查看SonarQube都是最新的分析结果

