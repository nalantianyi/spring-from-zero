package basic_di.autowired;

import basic_di.autowired.beans.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredApplication {
    public static void main(String[] args) {
        ApplicationContext ctx=new AnnotationConfigApplicationContext("basic_di.autowired.beans");
        Dog dog=ctx.getBean(Dog.class);
        System.out.println(dog);

    }
}
