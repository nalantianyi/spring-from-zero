package basic_dl.a_quickstart_byname;

import basic_dl.a_quickstart_byname.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class QuickstartByNameApplication {
    public static void main(String[] args) {
        BeanFactory factory1 = new ClassPathXmlApplicationContext("basic_dl/quickstart-byname.xml");
        Person person1 = (Person) factory1.getBean("person");
        BeanFactory factory2 = new ClassPathXmlApplicationContext("basic_dl/quickstart-bytype.xml");
        Person person2 = factory2.getBean(Person.class);
        System.out.println(person1);
        System.out.println(person2);

    }
}
