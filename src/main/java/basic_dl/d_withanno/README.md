### 依赖查找的高级使用——延迟查找【引用了原文】
对于一些特殊的场景，需要依赖容器中的某些特定的 Bean ，但当它们不存在时也能使用默认 / 缺省策略来处理逻辑。这个时候，使用上面已经学过的方式倒是可以实现，但编码可能会不很优雅。

1. 使用现有方案实现Bean缺失时的缺省加载
咱把设计做的简单一些，准备两个 bean ：Cat 和 Dog ，但是在 xml 中咱只注册 Cat ，这样 IOC 容器中就只有 Cat ，没有 Dog 。

之后，咱来编写启动类。由于 Dog 没有在 IOC 容器中，所以调用 getBean 方法时会报 NoSuchBeanDefinitionException ，为了保证能在没有找到 Bean 的时候启用缺省策略，咱可以在 catch 块中手动创建，实现代码如下：

```Java
public class ImmediatlyLookupApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("basic_dl/quickstart-lazylookup.xml");
        Cat cat = ctx.getBean(Cat.class);
        System.out.println(cat);
        
        Dog dog;
        try {
            dog = ctx.getBean(Dog.class);
        } catch (NoSuchBeanDefinitionException e) {
            // 找不到Dog时手动创建
        	dog = new Dog();
        }
        System.out.println(dog);
    }

}
```
可以发现这种编码方式相当不优雅，而且很别扭（性能也低）。如果真的后续每一个 bean 都这样操作，那编码量岂不是巨大？这肯定不行，一定有改良方案。

2. 改良-获取之前先检查
既然作为一个容器，能获取自然就能有检查，ApplicationContext 中有一个方法就可以专门用来检查容器中是否有指定的 Bean ：containsBean

    ```Dog dog = ctx.containsBean("dog") ? (Dog) ctx.getBean("dog") : new Dog();```
但注意，这个 containsBean 方法只能传 bean 的 id ，不能查类型，所以虽然可以改良前面的方案，但还是有问题：如果 Bean 的名不叫 dog ，叫 wangwang ，那这个方法岂不是废了？所以这个方案还是不够好，需要改良。

3. 改良-延迟查找
如果能有一种机制，我想获取一个 Bean 的时候，你可以先不给我报错，先给我一个包装让我拿着，回头我自己用的时候再拆开决定里面有还是没有，这样是不是就省去了 IOC 容器报错的麻烦事了呢？在 SpringFramework 4.3 中引入了一个新的 API ：ObjectProvider ，它可以实现延迟查找。

于是，咱可以改良上面的代码如下：（为了保留上面的案例示范，下面新起一个类）

```
public class LazyLookupApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("basic_dl/quickstart-lazylookup.xml");
        Cat cat = ctx.getBean(Cat.class);
        System.out.println(cat);
        // 下面的代码会报Bean没有定义 NoSuchBeanDefinitionException
        // Dog dog = ctx.getBean(Dog.class);
    
        // 这一行代码不会报错
        ObjectProvider<Dog> dogProvider = ctx.getBeanProvider(Dog.class);
    }
}
```
可以发现，ApplicationContext 中有一个方法叫 getBeanProvider ，它就是返回上面说的那个**“包装”。如果直接 getBean ，那如果容器中没有对应的 Bean ，就会报 NoSuchBeanDefinitionException；如果使用这种方式，运行 main 方法后发现并没有报错，只有调用 dogProvider 的 getObject ，真正要取包装里面的 Bean 时，才会报异常。所以总结下来，ObjectProvider 相当于延后了 Bean 的获取时机，也延后了异常可能出现的时机**。

但是，上面的问题还没有被解决呀，调用 getObject 方法还是会报异常，那下面咱就继续研究 ObjectProvider 的其他一些方法。

4. 延迟查找-方案实现
ObjectProvider 中还有一个方法：getIfAvailable ，它可以在找不到 Bean 时返回 null 而不抛出异常。使用这个方法，就可以避免上面的问题了。改良之后的代码如下：
```
    Dog dog = dogProvider.getIfAvailable();
    if (dog == null) {
        dog = new Dog();
    }
```
5. ObjectProvider在jdk8的升级
随着 SpringFramework 5.0 基于 jdk8 的发布，函数式编程也被大量用于 SpringFramework 中。ObjectProvider 中新加了几个方法，可以使编码更佳优雅。

看着上面 4.4 节的那几行代码，有木有联想到 Map 中的 getOrDefault ？由此，ObjectProvider 在 SpringFramework 5.0 后扩展了一个带 Supplier 参数的 getIfAvailable ，它可以在找不到 Bean 时直接用 Supplier 接口的方法返回默认实现，由此上面的代码还可以进一步简化为：

```
Dog dog = dogProvider.getIfAvailable(() -> new Dog());
```
或者更简单的，使用方法引用：

```Dog dog = dogProvider.getIfAvailable(Dog::new);```
当然，一般情况下，取出的 Bean 都会马上或者间歇的用到，ObjectProvider 还提供了一个 ifAvailable 方法，可以在 Bean 存在时执行 Consumer 接口的方法：

```dogProvider.ifAvailable(dog -> System.out.println(dog)); // 或者使用方法引用```
以上就是关于延迟查找的内容，这种方案可以使用，但在日常开发中可能使用的不是很多，小伙伴们实际动手操作一遍，有一个印象即可。后续如果升级到更高的位置，这部分或许会在封装组件和底层时用到。