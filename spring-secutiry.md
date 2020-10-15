# Spring Secutiry #

## 整体流程 ##

### DelegetingFilterProxy ###

* 配置：配置在web.xml之中，且`<filter-name>`必须指定为`springSecurityFilterChain`
* 功能：用于为Spring容器中指定的Bean做Filter代理，当指定`<filter-name>`为`springSecurityFilterChain`的时候，是整个安全校验流程的入口
* 工作流程
    * 获取目标Bean的id（targetBeanName）：
        * DelegetingFilterProxy可以通过构造器指定要代理的Bean的id
        * 如果没有指定，则获取FilterConfig，再根据FilterConfig获取当前DelegetingFilterProxy在web.xml中配置的filterName，然后将filterName作为目标Bean的id
    * 从ServletContext中获取WebApplicationContext：默认属性名为WebApplicationContext.class.getName()+".ROOT"；如果直接getAttribute取不到，就把ServletContext中配置的所有attribute迭代一边，获取第一个`instance of`为WebApplicationContext的属性
    * 从WebApplicationContext中根据targetBeanName获取目标Bean：目标Bean必须实现Filter接口
    * 将请求代理给目标Bean
