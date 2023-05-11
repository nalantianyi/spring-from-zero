package basic_di.jsr250.config;

import basic_di.jsr250.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("basic_di.jsr250.bean")
public class JSRConfiguration {
    @Bean
    public Person master() {
        return new Person("master");
    }

    @Bean
    public Person admin() {
        return new Person("admin");
    }
}