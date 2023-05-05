package basic_dl.d_withanno;

import basic_dl.d_withanno.anno.Color;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.stream.Stream;

public class WithAnnoApplication {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("basic_dl/withanno.xml");
        Map<String, Object> beans = ctx.getBeansWithAnnotation(Color.class);
        beans.forEach((beanName,bean)->{
            System.out.println(beanName+":"+bean.toString());
        });
        //只打印了有Color注解标志的Black和Red

        //如果想取出所有的bean的id，然后根据byId的方法实例化就行
        String[] beanNames = ctx.getBeanDefinitionNames();
        Stream.of(beanNames).forEach(System.out::println);


    }
}
