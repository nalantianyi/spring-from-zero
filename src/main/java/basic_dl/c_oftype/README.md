#### 【面试题】BeanFactory与ApplicationContext的对比
既然都聊到这个份上了，那咱就顺便来一道面试题吧，看看这个问题如何在面试中回答会比较合适。

以下答案仅供参考，可根据自己的理解调整回答内容：

BeanFactory 接口提供了一个抽象的配置和对象的管理机制，ApplicationContext 是 BeanFactory 的子接口，它简化了与 AOP 的整合、消息机制、事件机制，以及对 Web 环境的扩展（ WebApplicationContext 等），BeanFactory 是没有这些扩展的。

ApplicationContext 主要扩展了以下功能：

AOP 的支持（ AnnotationAwareAspectJAutoProxyCreator 作用于 Bean 的初始化之后 ）
配置元信息（ BeanDefinition 、Environment 、注解等 ）
资源管理（ Resource 抽象 ）
事件驱动机制（ ApplicationEvent 、ApplicationListener ）
消息与国际化（ LocaleResolver ）
Environment 抽象（ SpringFramework 3.1 以后）