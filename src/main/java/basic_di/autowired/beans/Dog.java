package basic_di.autowired.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dog {
    @Value("dogdog")
    private String name;

//    @Autowired
//    public Dog(Person person) {
//        this.person = person;
//    }
    // 构造器注入

    //    @Autowired 属性注入
    private Person person;

    @Autowired
    private List<Person> persions;

    @Autowired //setter 注入
    @Qualifier("administrator")
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", person=" + person +
                ", persions=" + persions +
                '}';
    }
}
