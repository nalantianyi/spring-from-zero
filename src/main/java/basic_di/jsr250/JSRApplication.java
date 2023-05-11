package basic_di.jsr250;

import basic_di.jsr250.bean.Bird;
import basic_di.jsr250.config.JSRConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JSRApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JSRConfiguration.class);
        Bird bird = ctx.getBean(Bird.class);
        System.out.println(bird);
    }
}
