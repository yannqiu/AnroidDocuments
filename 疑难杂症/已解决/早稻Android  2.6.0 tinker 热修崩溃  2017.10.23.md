#  早稻Android  2.6.0 tinker 热修崩溃  2017.10.23

##  log链接：https://bugly.qq.com/v2/crash-reporting/crashes/26f496cb82/7920?pid=1 

##  现象及影响

	由于在上一个版本发布了tinker补丁，打补丁成功之后没有删除包，用户升级之后重启的时候，程序崩溃退出，造成无法启动程序。问题比较难重现。


##  bug需要处理的等级 ,时间期限 和 负责人

	**等级 ：**  严重


	**状态 ：**  已解决


	**时间期限 ：**   2017年11月25日前解决


	**bug 跟踪处理人 ：**   林泽池



##  原因及解决方法 和 时间

	**原因**

	用户升级之后重启的时候，由于tinker会重新生成新的application ，并且tinker补丁缓存还有该包，于是执行打补丁操作，由于补丁的tinkerid 不一致，会回掉补丁失败，这个时候application由于重新生成，之前的context为空，这个时候在监听回掉调用了context.getpackname() 产生空指针错误。

	**解决方案**

	补丁打成功之后，删除补丁包。context进行判空。
	regist只考虑Application监听的case,对于activity监听，但是activity被回收的case不考虑监听


	**解决时间：**  2017年10月25日 13：40



##  总结

	对第三方的库，需要更深入的进行了解