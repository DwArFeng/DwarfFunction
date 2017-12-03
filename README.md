# dutil for Java - DwArFeng的Java功能代码

## dutil 是干什么的？

dutil (DwArFeng's java func) 是DwArFeng在编写Java程序时总结的一些工具方法和一些常用的类。

## dutil包含哪些内容？

dutil 包含一些基本的，用于各种程序中的快捷的或者具有功能性的工具包，也含有用于数学、物理学等其它方面的功能
强大的工具包。

### 该工具包中的子工具一览

| 包名称 								| 主要功能																	| 进度							|
| :------------ 				|:---------------														|:---------------:	|
| dutil.basic   				| 基础包：基本的通用性工具以及类型定义  		|初步完善，稳定开发	|
| dutil.detool   				| 调试专用包：目前还没有完善						 		|没有完善						|
| dutil.foth   					| 带算式结构的数学包：提供有结构的数学定义	|有限的完善					|
| dutil.math   					| 数学包：定义多种数学类，目前还在完善	 		|有限的完善					|
| dutil.phic   					| 物理包：目前还没有完善							  		|没有完善						|
| dutil.demo   					| 示例包：正在完善							  					|正在完善						|
| dutil.develop   			| 开发工具包：正在完善							  			|正在完善						|

## 如何使用dutil包？
该项目拥有两个分支：独立版和maven版。
### 独立版本下载和使用
独立版的最新版本是0.1.5.b-beta，并且不会再进行更新，如果需要使用独立版，只需要找到 tag 	__origin-v0.1.5.b-beta__ 对应的发布版，下载文件 __beta-0.1.5.b.zip__ ，将这个文件中需要使用的jar包放在项目的运行环境中即可。
### maven版本下载和使用
maven版本从 0.1.5.c-beta 起开始，一直到最新的版本，是这个项目的主要维护版本。所有发布的版本均可以在 release 中找到。使用的方法是：在release中，在Download选项中点击 __Source code__,下载源代码之后，使用maven指令进行编辑。
```
mvn install
```
如果test失败，请多尝试数次（部分test会涉及延迟，线程中断，因此成功与否可能依赖于系统平台），多次test失败后，请尝试使用
```
mvn install -Dmaven.test.skip=true
```
请在项目的 __pom.xml__ 中插入依赖
```
<dependency>
    <groupId>com.dwarfeng</groupId>
    <artifactId>dutil</artifactId>
    <version>beta-0.1.5.b</version>
</dependency>
```

## 更新记录  

请参考ChangeLog.txt
