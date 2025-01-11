# J-HTTP开发文档
***
### 一.写在前面
这个项目目前还没完工 目标是打造一个轻量级的开源web框架 如果完工会将jar发到releases中 \
"JHTTP"命名是由一个"J" 和一个"HTTP"组成 \
所谓J 就是Java的首字母 代表这个框架是用于Java的 而HTTP 总所周知是一个超文本传输协议\
这个项目就是提供一个便捷的HTTP调用方式 而不用自己造轮子或者是使用麻烦的JSP\
在一次自搜的时候发现和一个Gitee上的项目重名了... 正在想一个新的名字\

### 二.使用说明‘
**这里只是一个简略的介绍 具体内容请查看开发手册(TODO)**
##### 1.作为服务端
创建服务器使用`JHttpServer`来创建 代码如下
```java
JHttpServer server = new JHttpServer();
```
使用一个函数式接口`Route`实现`handle`方法来创建路由 如下面代码实现获得当前时间的功能 `handle`返回值为一个`bytes[]`
```java
timeRoute = new Route() {
    @Override
    public byte[] handle(JHttpRequest req) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        return formattedDate.getBytes();
    }
}
```
使用`JHttpServer.sign`来将路由绑定到服务器上 如下列代码将上列路由绑定到服务器上
```java
server.sign("/api/v1/getTime", timeRoute);
```
当然我们也可以使用匿名函数来获得更简洁美观的代码
```java
server.sign("/api/v1/getTime", (req) -> {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        return formattedDate.getBytes();
});
```
最后 当我们把业务代码全部写完 就可以把服务器运行起来了
```java
try {
    server.run("0.0.0.0", 80);
} catch (IOException e) {
    e.printStackTrace();
}
```
注意 `JHttpServer.run`会抛出IOException 使用时需要加上try-catch语句或者显式throw来防止程序意外终止
```
Server is running at http://127.0.0.1:80
INFO: find 1 user
INFO: HTTP/1.0 200 OK!
INFO: find 1 user
ERROR: 114514 This is a sheep
```
##### 2.作为客户端
还在TODO阶段...

##### 3.Logger功能
TODO

##### 4.SQL功能
TODO

##### 5.JSON解析
TODO

....预计寒假结束会做出第一个版本 尽请期待(大概吧) \
By cleversheep2011