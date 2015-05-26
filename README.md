# 关于

基于Winlet框架开发的应用的演示。

# 安装准备

为了下载运行本项目中的演示应用，请先做好以下准备工作：

* 安装JDK 1.8
* 安装支持Java 8的Eclipse（或Spring Tool Suite或其他Java集成开发环境），在其中把Java 8运行环境配置好。
* Maven、git能正常工作
 
# 下载运行

请按以下步骤下载运行：
* 执行以下命令从github上下载本项目：
```
git clone https://github.com/yangjm/winlet_demo.git
```
* 在Eclipse中用Import -> Existing Maven Projects把winlet_demo项目中的demo_1和demo_2项目加入到工作区
* 在Eclipse中建立服务器，把demo_1和demo_2项目加入
* 启动运行服务器


demo_1中有两个演示页面，URL为（假设Eclipse服务器使用端口8080）：
* http://localhost:8080/demo_1/resources/user.html
* http://localhost:8080/demo_1/resources/user1.html

demo_2的访问入口URL：
* http://localhost:8080/demo_2/site/home

# demo_2截屏

登录

![demo2_1](https://github.com/yangjm/winlet_demo/blob/master/doc/demo2_1.png)

权限管理 － 列表模式

![demo2_2](https://github.com/yangjm/winlet_demo/blob/master/doc/demo2_2.png)

权限管理 － 展开模式

![demo2_3](https://github.com/yangjm/winlet_demo/blob/master/doc/demo2_3.png)

角色管理

![demo2_4](https://github.com/yangjm/winlet_demo/blob/master/doc/demo2_4.png)

用户管理

![demo2_5](https://github.com/yangjm/winlet_demo/blob/master/doc/demo2_5.png)
