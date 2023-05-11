package basic_di.autowired.beans;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name = "administrator";

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
