package basic_dl;

import basic_dl.bean.Person;
import basic_dl.dao.DemoDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class QuickstartByNameApplication {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("basic_dl/quickstart.xml");
        Person person = factory.getBean(Person.class);//按类型进行注入，如果按name注入的话就需要强转
        System.out.println(person);

        DemoDao demoDao = factory.getBean(DemoDao.class);//如果有多个实现类的话？
        System.out.println(demoDao.findAll());

    }
}
