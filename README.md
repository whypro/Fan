# Fan

模拟风扇转动的 JAVA(J2SE) 小程序  
2010-12-11

## 一、任务（问题）描述
在窗口中绘制具有4个叶片的风扇；用Timer或多线程程序设计实现叶片的转动；设置启动、停止按钮，定义一个滚动条控制叶片的转速。

## 二、设计与实现
本程序使用了MVC和观察者模式。    
主要类有四个：   
1. FanModel: 
Model，抽象了风扇的功能，封装了风扇的状态。继承了Observable类，当对象发生改变时通知其他观察者。(FanModel.java)
2. FanView: 
View，观察者，将模型的改变显示在窗口上，维护与Model的一致性。实现了Observable接口。 (FanView.java)
3. FanController:
Controller，根据用户触发的时间更新Model。 (FanController.java)
4. FanMain: 
程序入口。 (FanMain.java)

## 四、体会与总结
1. 在实现过程中，发现控制速度有两种方式，一是增加run()方法中扇叶位置的增加量，二是减少run()方法中sleep()的时间。本程序使用这种方式，即：定义变量FPS(frames per second)，粗略的表示每秒刷新的次数，FPS随speed(revolutions per second)的增加而增加，满足表达式FPS = speed * 360.，这样保证了风扇平滑的旋转。
2. 因为时间仓促，GUI设计的还不够完美。使用者不能交互式地设置扇叶数、扇叶大小、旋转方向、扇叶颜色等（而这些在当初设计FanModel类时都已定义和实现）。
3. 在程序的编写过程中，遇到的问题有很多，例如线程与MVC模式的结合，Controller中线程休眠时间的控制，尤其是类的设计和功能的划分花费了大量的时间。
