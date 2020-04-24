authorizeRequests() 实际上返回了一个URL拦截注册器，可以调用它的anyRequest(), antMatchers, regexMatchers()等方法来匹配系统的URL, 并为其指定安全策略。
csrf() 方法是spring security提供的跨站请求伪造防护功能，默认开启csrf()方法。
* 表示匹配0或任意数量的字符
** 表示匹配0或更多的目录