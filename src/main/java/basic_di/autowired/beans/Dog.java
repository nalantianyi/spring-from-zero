package basic_di.autowired.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Autowired //setter 注入
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }
}
