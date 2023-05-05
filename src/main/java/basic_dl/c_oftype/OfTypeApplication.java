package basic_dl.c_oftype;

import basic_dl.c_oftype.dao.DemoDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class OfTypeApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("basic_dl/oftype.xml");
        Map<String, DemoDao> beans = ctx.getBeansOfType(DemoDao.class);

        beans.forEach((beanName, bean) -> {
            System.out.println(beanName + ":" + bean.toString());
        });


    }
}
