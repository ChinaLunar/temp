# Spring Secutiry #

## 整体流程 ##

### DelegetingFilterProxy ###

* 配置：配置在web.xml之中，且`<filter-name>`必须指定为`springSecurityFilterChain`
* 功能：用于为Spring容器中指定的Bean做Filter代理，当指定`<filter-name>`为`springSecurityFilterChain`的时候，是整个安全校验流程的入口
* 优点：Spring容器通过ContextLoaderListener加载Bean，这个加载过程包含在Serlvet容器启动初始化Listener的过程之中。使用DelegetingFilterProxy之后，只需要向Servlet容器注册DelegetingFilterProxy就可以，而不需要提前查询Spring容器中的Filter实例，也不需要提前向Servlet做注册
* 工作流程
    * 获取目标Bean的id（targetBeanName）：
        * DelegetingFilterProxy可以通过构造器指定要代理的Bean的id
        * 如果没有指定，则获取FilterConfig，再根据FilterConfig获取当前DelegetingFilterProxy在web.xml中配置的filterName，然后将filterName作为目标Bean的id
    * 从ServletContext中获取WebApplicationContext：默认属性名为WebApplicationContext.class.getName()+".ROOT"；如果直接getAttribute取不到，就把ServletContext中配置的所有attribute迭代一边，获取第一个`instance of`为WebApplicationContext的属性
    * 从WebApplicationContext中根据targetBeanName获取目标Bean：目标Bean必须实现Filter接口
    * 将请求代理给目标Bean


### `springSecurityFilterChain` Bean：FilterChainProxy ###

DeletetingFilterProxy将请求代理给`springSecurityFilterChain` Bean，这个Bean本质上就是一个Filter，实际的实现类是`FilterChainProxy`

* 定义：`org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration#springSecurityFilterChain`
* 初始化流程：
   * 创建抽象类`WebSecurityConfigurerAdapter`的默认子类（不做任何额外实现），并其apply给`WebSecurity`，然后调用build创建目标Bean（目标Filter）
   * `WebSecurity`继承`AbstractConfiguredSecurityBuilder`，`AbstractConfiguredSecurityBuilder`继承`AbstractSecurityBuilder`
   * `WebSecurity`.build继承自`AbstractSecurityBuilder`，调用`AbstractSecurityBuilder`.doBuild
   * `AbstractConfiguredSecurityBuilder`对`AbstractSecurityBuilder`.doBuild有额外实现，调用`AbstractConfiguredSecurityBuilder`.performBuild
   * `WebSecurity`对`AbstractConfiguredSecurityBuilder`.performBuild又额外实现，负责创建FilterChainProxy并返回作为`springSecurityFilterChain` Bean
功能：FilterChainProxy用于接收DelegetingFilterProxy的请求，并把请求发送给之后的SecutiryFilterChain（List）做安全处理
