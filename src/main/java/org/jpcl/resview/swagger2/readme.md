### 相关注解 [参照](https://baijiahao.baidu.com/s?id=1615175862715619117&wfr=spider&for=pc)
1. @Api  
    > 使用在Controller层Api类上，主要属性有tags(标签)、hidden(是否隐藏)、value、authorizations等。 
2. @ApiOperation  
    > 使用在Api类的接口方法上，主要属性有value(接口名称)、notes(注释)、hidden(是否隐藏)、httpMethod、ignoreJsonView、response、responseHeaders等等，某些属性注解可自动识别，无需配置。  
3. @ApiImplicitParams、@ApiImplicitParam
    > 使用在Api类的接口方法上，对接口参数进行说明，@ApiImplicitParams只有一个属性value，@ApiImplicitParam主要属性有name(参数名称)、value(参数说明)、required(是否必需)、dataType(数据类型)、paramType(参数类型)、dataTypeClass、defaultValue、readOnly等。  
4. @ApiModel  
    > 用在实体类上，主要属性有description(描述)、parent(父类)、subTypes、value、discriminator等。  
5. @ApiModelProperty
    > 用在实体类属性上，主要属性有access、accessMode、allowableValues、allowEmptyValue(是否允许为空)、dataType(数据类型)、example(示例)、hidden(是否隐藏)、name(名称)、notes、required(是否必需)、value(说明)等。  
5. @ApiParam  
    > 对单个参数的描述  
5. @ApiResponse  
    > 对http响应的描述  
5. @ApiResponses  
    > 对多个http响应的描述  
5. @ApiError  
    > 发生错误返回的信息  

http://localhost:8080/swagger-ui.html 查看

### 生产环境关闭swagger2
1. 方式一  
    在Swagger2Config上使用@Profile注解标识，@Profile({"dev","test"})表示在dev和test环境才能访问swagger-ui.html，prod环境下访问不了
2. 方式二  
    在Swagger2Config上使用@ ConditionalOnProperty注解，@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")表示配置文件中如果swagger.enable =true表示开启。所以只需要在开发环境的配置文件配置为true，生产环境配置为false即可。