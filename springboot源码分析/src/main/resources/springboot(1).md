# Spring Boot 笔记 （一）
```java
public class SpringApplication {
    /**
     * 入口函数 resourceLoader为null  primarySources即为启动类 也就是SpringApplication.run(xxxx.class)
     */
    public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
        this.resourceLoader = resourceLoader;
        Assert.notNull(primarySources, "PrimarySources must not be null");
        // 去重 设置参数
        this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
        // 取得工程类型 基本上是 SERVLET (NONE  SERVLET  REACTIVE)
        // 根据是否存在 javax.servlet.Servlet org.springframework.web.context.ConfigurableWebApplicationContext 等来分析
        this.webApplicationType = deduceWebApplicationType();
        // 从配置文件spring.factories中找到 ApplicationContextInitializer 接口的实现类并实例化设置到Initializers属性中
        // DelegatingApplicationContextInitializer  ContextIdApplicationContextInitializer  ConfigurationWarningsApplicationContextInitializer
        // ServerPortInfoApplicationContextInitializer  SharedMetadataReaderFactoryContextInitializer  ConditionEvaluationReportLoggingListener
        setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
        // 从配置文件spring.factories中取得 ApplicationListener 接口的实现类并实例化设置到Listeners属性中
        // ConfigFileApplicationListener  AnsiOutputApplicationListener  LoggingApplicationListener ClasspathLoggingApplicationListener 
        // BackgroundPreinitializer  DelegatingApplicationListener  ParentContextCloserApplicationListener  ClearCachesApplicationListener
        // FileEncodingApplicationListener  LiquibaseServiceLocatorApplicationListener
        setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
        // 从运行时栈中取得入口main的class实例名 设值
        this.mainApplicationClass = deduceMainApplicationClass();
    }

    /**
     * 项目运行
     */
    public ConfigurableApplicationContext run(String... args) {
        // 任务执行观察者
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ConfigurableApplicationContext context = null;
        // spring boot 异常记录
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
        // 设置属性 java.awt.headless = true
        configureHeadlessProperty();
        // 创建 SpringApplicationRunListeners 监听器, 项目运行监听器, 其构造方法传入了从 spring.factories 中找到的 SpringApplicationRunListener 实现类的实例集合
        // 目前只有 EventPublishingRunListener , 创建时 做成 SimpleApplicationEventMulticaster 作为属性, application.getListeners() 作为属性
        // 1. starting: ApplicationStartingEvent 广播给实例的事件监听器 run开始即执行
        // 2. environmentPrepared: ApplicationEnvironmentPreparedEvent  广播给实例的事件监听器 环境创建ok 配置信息设置OK
        // 3. contextPrepared: 啥都没有 空函数 发生在容器创建完成
        // 4. contextLoaded: ApplicationPreparedEvent 广播给实例的事件监听器 容器创建完成 且实例化的listener也添加成容器监听器
        // 5. started: ApplicationStartedEvent context 发布事件  此时context已经refresh了 还没有 callRunners
        // 6. running: ApplicationReadyEvent context 发布事件 callRunners 执行完成
        // 7. failed: 异常时 ApplicationFailedEvent 广播给实例的事件监听器 run执行异常
        SpringApplicationRunListeners listeners = getRunListeners(args);
        // 使用 SimpleApplicationEventMulticaster 广播了一个 ApplicationStartingEvent 事件
        // 实质上是 找到所有符合事件类型条件的listener 循环执行事件的onApplicationEvent 方法 
        // 同步执行的 [LoggingApplicationListener, BackgroundPreinitializer, DelegatingApplicationListener, LiquibaseServiceLocatorApplicationListener]
        listeners.starting();
        try {
            // 创建一个应用参数持有类 改类会解析传入进来的参数 -D 
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            // 准备并配置环境 会发布一个 ApplicationEnvironmentPreparedEvent 事件给监听器
            ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
            // 设置 spring.beaninfo.ignore = true
            configureIgnoreBeanInfo(environment);
            // 打印Banner
            Banner printedBanner = printBanner(environment);
            // 创建容器 AnnotationConfigServletWebServerApplicationContext 实例
            context = createApplicationContext();
            // 从spring.factories中取得SpringBootExceptionReporter子类的实例集合
            exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[] { ConfigurableApplicationContext.class }, context);
            // 初始化容器 并发布 ApplicationPreparedEvent 事件
            // 1) 设置环境实例 2) 容器初始化前设置(无)  3) 执行initializer 4)将参数持有对象和banner添加到容器中  5)sources设置 6)添加监听器到容器中
            prepareContext(context, environment, listeners, applicationArguments, printedBanner);
            // 容器上下文刷新
            refreshContext(context);
            // 啥都没做
            afterRefresh(context, applicationArguments);
            // 观察者停止
            stopWatch.stop();
            if (this.logStartupInfo) {
                new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
            }
            // 容器启动完成事件发布 ApplicationStartedEvent
            listeners.started(context);
            // 执行runners  ApplicationRunner 和 CommandLineRunner 接口的实现类 执行其run方法
            callRunners(context, applicationArguments);
        }
        catch (Throwable ex) {
            // 执行异常 
            handleRunFailure(context, listeners, exceptionReporters, ex);
            throw new IllegalStateException(ex);
        }
        // 发布 ApplicationReadyEvent 事件
        listeners.running(context);
        return context;
    }

    /**
     * 取得 类型为type 构造函数参数为parameterTypes 的实例集合  args为启动传入的参数
     */
    private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 使用类型(其实type->className) 从 spring.factories 找到实现类名 传入类加载器 里面有缓存机制
        Set<String> names = new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
        // 根据名称和参数类型, 创建实例对象
        List<T> instances = createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
        // 根据order排序
        AnnotationAwareOrderComparator.sort(instances);
        return instances;
    }

    /**
     * 创建实例集合  根据 names
     */
    private <T> List<T> createSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, ClassLoader classLoader, Object[] args, Set<String> names) {
        List<T> instances = new ArrayList<>(names.size());
        for (String name : names) {
            try {
                // 根据name来找到对应的类类型, 可能是基本类型, 数组等, 然后使用类加载器得到Class对象
                Class<?> instanceClass = ClassUtils.forName(name, classLoader);
                // 判断instanceClass 是不是 type的子类
                Assert.isAssignable(type, instanceClass);
                // 根据参数类型取到相应的构造方法
                Constructor<?> constructor = instanceClass.getDeclaredConstructor(parameterTypes);
                // 生成一个实例对象 BeanUtils 中有对Kotlin 的处理(忽略)
                T instance = (T) BeanUtils.instantiateClass(constructor, args);
                instances.add(instance);
            }
            catch (Throwable ex) {
                throw new IllegalArgumentException("Cannot instantiate " + type + " : " + name, ex);
            }
        }
        return instances;
    }

    /**
     * 
     */
    private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments) {
        // 根据服务类型 创建或者配置一个环境 StandardServletEnvironment
        ConfigurableEnvironment environment = getOrCreateEnvironment();
        // 配置环境
        configureEnvironment(environment, applicationArguments.getSourceArgs());
        // 发布一个 ApplicationEnvironmentPreparedEvent 事件  环境配置完成事件
        // 同步 [ConfigFileApplicationListener AnsiOutputApplicationListener  LoggingApplicationListener ClasspathLoggingApplicationListener BackgroundPreinitializer  DelegatingApplicationListener FileEncodingApplicationListener]
        listeners.environmentPrepared(environment);
        // 
        bindToSpringApplication(environment);
        if (this.webApplicationType == WebApplicationType.NONE) {
            environment = new EnvironmentConverter(getClassLoader()).convertToStandardEnvironmentIfNecessary(environment);
        }
        ConfigurationPropertySources.attach(environment);
        return environment;
    }

    /**
     * 若有传入参数 则进行环境配置
     */
    protected void configurePropertySources(ConfigurableEnvironment environment, String[] args) {
        // 获取environment的一个MutablePropertySources属性
        MutablePropertySources sources = environment.getPropertySources();
        if (this.defaultProperties != null && !this.defaultProperties.isEmpty()) {
            sources.addLast(new MapPropertySource("defaultProperties", this.defaultProperties));
        }
        if (this.addCommandLineProperties && args.length > 0) {
            String name = CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME;
            if (sources.contains(name)) {
                PropertySource<?> source = sources.get(name);
                CompositePropertySource composite = new CompositePropertySource(name);
                composite.addPropertySource(new SimpleCommandLinePropertySource("springApplicationCommandLineArgs", args));
                composite.addPropertySource(source);
                sources.replace(name, composite);
            }
            else {
                sources.addFirst(new SimpleCommandLinePropertySource(args));
            }
        }
    }

    /**
     * server.profile.active 激活配置文件
     */
    protected void configureProfiles(ConfigurableEnvironment environment, String[] args) {
        environment.getActiveProfiles(); 
        Set<String> profiles = new LinkedHashSet<>(this.additionalProfiles);
        profiles.addAll(Arrays.asList(environment.getActiveProfiles()));
        environment.setActiveProfiles(StringUtils.toStringArray(profiles));
    }

    /**
     * 配置容器
     */
    private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment, SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
        // 设置环境配置到容器中
        context.setEnvironment(environment);
        // 容器创建之后 做一些额外的事情 启动时 啥都没干
        postProcessApplicationContext(context);
        // 执行getInitializers 中实例的 initializer 方法
        applyInitializers(context);
        // EventPublishingRunListener 实现类中 啥都没干 标志着容器初始化ok
        listeners.contextPrepared(context);
        if (this.logStartupInfo) {
            logStartupInfo(context.getParent() == null);
            logStartupProfileInfo(context);
        }

        // 启动参数持有类 注册到容器中 单例
        context.getBeanFactory().registerSingleton("springApplicationArguments", applicationArguments);
        if (printedBanner != null) {
            // banner 对象注册到容器中
            context.getBeanFactory().registerSingleton("springBootBanner", printedBanner);
        }

        // 即启动类
        Set<Object> sources = getAllSources();
        Assert.notEmpty(sources, "Sources must not be empty");
        // 将启动类 load到 BeanDefinitionLoader 中
        load(context, sources.toArray(new Object[0]));
        // 容器环境配置完成 发送事件 ApplicationPreparedEvent  在发送事件之前 找到容器启动时创建的监听器 getListeners, 若该监听器有实现 ApplicationContextAware 接口, 将进行context设置
        // 将getListeners 该监听器添加到容器中 addApplicationListener
        // 实现Aware接口的监听器有 ParentContextCloserApplicationListener 
        listeners.contextLoaded(context);
    }
}
```

```java
public class LoggingApplicationListener implements GenericApplicationListener {
    
}
```

```java
BackgroundPreinitializer
```

```java
DelegatingApplicationListener
```

```java
LiquibaseServiceLocatorApplicationListener
```

```java

```

```java

```
