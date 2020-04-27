# 自定义视图的使用
## JsonView
1) 在resource目录下添加res-code.properties文件, 文件中配置自己的返回code和与之对应的msg。
这个文件名是默认的。 还可以在application.yml 中去指定res.code.path的值，这个只指向的是自己定义
返回code文件的相对于resource的目录的地址。
2) 返回视图时，直接可以通过构造函数传递这个key来构造对象。