# Reverse Proxy 反向代理服务

反向代理（Reverse Proxy）是指以代理服务器来接受internet上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给internet上请求连接的客户端，此时代理服务器对外就表现为一个反向代理服务器。

简单来说，你的反向代理服务器会接收请求，但其自身不处理该请求，而是对请求经过一些处理，例如添加日志、缓存、身份验证等服务，然后再将请求转发到相应的应用服务器中进行处理，最后将处理结果返回。

## Java 实现
@see java-impl 目录

@see [在Spring应用调试中实现反向代理服务器](https://zhuanlan.zhihu.com/p/21360559)

#### maven pom:
```
<!--custom dependencies-->
<dependency>
    <groupId>org.mitre.dsmiley.httpproxy</groupId>
    <artifactId>smiley-http-proxy-servlet</artifactId>
    <version>1.10</version>
</dependency>

<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>20.0</version>
</dependency>
```
#### application.yml
```
server:
  port: 8888
```

#### 核心servlet配置：
```java
@Bean
public Servlet lanyusProxy(){
    return new ProxyServlet();
}

@Bean
public ServletRegistrationBean proxyServletRegistration(){

    ServletRegistrationBean registrationBean
            = new ServletRegistrationBean(lanyusProxy(),"/*");
    Map<String,String> params = ImmutableMap.of(
            "targetUri","http://idea.lanyus.com:80",
            "log",      "true"
    );
    registrationBean.setInitParameters(params);
    return registrationBean;
}
```
这里默认反代了 `idea.lanyus.com`, 于是可直接使用 localhost:8888 来激活idea、jrebel、xrebel、jrebel for android。

借鉴了[ReverseProxy in golang](https://github.com/ilanyu/ReverseProxy)Go写的反向代理服务器。

#### 使用方式：
```
下载源码或jar包，默认反代idea.lanyus.com, 运行起来后, http://127.0.0.1:8888/JRebel用户名 就是激活地址了, 邮箱随意填写, 当然, 也可用于idea（激活网址填入 localhost:8888）

也可以在Docker中使用
```
