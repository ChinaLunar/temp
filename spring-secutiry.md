# Spring Secutiry #

## 整体流程 ##

### DelegetingFilterProxy ###

* 配置：配置在web.xml之中，且`<filter-name>`必须指定为`springSecurityFilterChain`
* 功能：整个安全校验流程的入口
* 工作流程
    * 从ServletContext中获取WebApplicationContext：默认属性名为WebApplicationContext.class.getName()+".ROOT"；如果直接getAttribute取不到，就把ServletContext中配置的所有attribute迭代一边，获取第一个instance of为WebApplicationContext的属性
    * 