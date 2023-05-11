package basic_di.autowired;

import basic_di.autowired.beans.Cat;
import basic_di.autowired.beans.Dog;
import basic_di.autowired.config.AutowiredConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredApplication {
    public static void main(String[] args) {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(AutowiredConfiguration.class);
        Cat cat = ctx.getBean(Cat.class);
        System.out.println(cat);

        Dog dog = ctx.getBean(Dog.class);
        System.out.println(dog);

    }
}
