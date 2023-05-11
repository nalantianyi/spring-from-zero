package basic_di.autowired.config;

import basic_di.autowired.beans.Cat;
import basic_di.autowired.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("basic_di.autowired.beans")
public class AutowiredConfiguration {

    @Bean
    @Autowired
    public Cat cat(Person person){
        Cat cat=new Cat();
        cat.setName("mimi");
        cat.setPerson(person);
        return cat;
    }

    @Bean
    @Primary
    public Person master() {
        Person master = new Person();
        master.setName("master");
        return master;
    }
}

