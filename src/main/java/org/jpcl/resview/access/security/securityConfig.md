## 方法介绍
```
//session过期后的处理，invalidSessionStrategy() 和 invalidSessionUrl 前者是在类中处理，后者是跳转到一个url。
.sessionManagement().invalidSessionUrl();
//指定最大登录数
.maximumSessions(1)
//当达到最大值时，是否保留已经登录的用户；为true，新用户无法登录；为 false，旧用户被踢出
.maxSessionsPreventsLogin(false)
//当达到最大值时，旧用户被踢出后的操作
.expiredSessionStrategy(customExpiredSessionStrategy);
customExpiredSessionStrategy 是一个实现了SessionInformationExpiredStrategy 接口的类 是当旧用户被踢出的处理逻辑
```
## 配置
+ Session超时的配置
```properties
# session 过期时间，单位：秒
server.servlet.session.timeout=60
```
> 从用户最后一次操作开始计算过期时间。  
> 过期时间最小值为 60 秒，如果你设置的值小于 60 秒，也会被更改为 60 秒。
>