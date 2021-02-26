# 接口
## 容器启动篇



## 接口工具
### SpringFactoriesLoader
> 根据类型和类加载器, 从 spring.factories 中取得该类型的 类名 集合  
> SpringFactoriesLoader.loadFactoryNames(type, classLoader)  

### AnnotationAwareOrderComparator
> 传递一个list集合 该方法根据order对集合进行排序 由小->大  
> AnnotationAwareOrderComparator.sort(instances)  

### ClassUtils
> 根据类名和类加载器 得到该类名对应类的Class  Class.forName("类的全限定名")  实例对象.getClass()  类名.class （类字面常量）  
> ClassUtils.forName(name, classLoader);  

> 获取一个默认的类加载器    
> getDefaultClassLoader()   

> 判断类是否是可以缓存的 也就是说 类是否在该classLoader和父Loader中   
> isCacheSafe(Class<?> clazz, @Nullable ClassLoader classLoader)   

> 取得一个没有包名的类名  
> ClassUtils.getShortName(beanClassName)  

> 将类名转变成Bean名  
> Introspector.decapitalize(shortClassName)  

### ClassLoader
> 取得当前运行栈线程的类加载器   
> Thread.currentThread().getContextClassLoader();   
> ClassLoader.getSystemResources(文件名)   
> classLoader.getResources(文件名)   

### BeanUtils
> 根据构造器, 传递构造参数, 构造一个实例   
> BeanUtils.instantiateClass(constructor, args);   

### PropertiesLoaderUtils
> 加载一个配置文件   
> Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(URL));   

### StringUtils
> 根据 , 分割字符串为 String[]   
> StringUtils.commaDelimitedListToStringArray(String)   

### AnnotationUtils
> 查找Class类型上是否包含指定的注解(递归) 注解中的注解 包含就行   
> AnnotationUtils.findAnnotation(type, Component.class)  

### MultiValueMap<K, V> -》 Map<K, List<V>>

### PathMatchingResourcePatternResolver
> 根据路径查询资源文件 .class后缀
> getResources  
