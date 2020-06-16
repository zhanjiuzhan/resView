# 服务端跨域请求的解决方法
1. 创建一个filter来解决
    ```java
    @Component
    public class SimpleCORSFilter implements Filter {
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
            chain.doFilter(req, res);
        }
        public void init(FilterConfig filterConfig) {}
        public void destroy() {}
    }
   ```
2. 基于WebMvcConfigurerAdapter配置加入Cors的跨域
    ```java
    @Configuration 
    public class CorsConfig implements WebMvcConfigurer  { 
        @Override 
        public void addCorsMappings(CorsRegistry registry) { 
            registry.addMapping("/**") 
                    .allowedOrigins("*") 
                    .allowCredentials(true) 
                    .allowedMethods("GET", "POST", "DELETE", "PUT") 
                    .maxAge(3600); 
        } 
    }
    ```
3. 使用注解
    ```java
    @CrossOrigin(origins = "http://192.168.1.10:8080", maxAge = 3600)
    @RequestMapping("rest_index")
    @RestController
    public class IndexController{
    }
    ```
   + origins  ： 允许可访问的域列表
   + maxAge：准备响应前的缓存持续的最大时间（以秒为单位）。

