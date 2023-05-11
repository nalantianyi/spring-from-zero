package basic_di.jsr250.bean;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Bird {
    @Resource(name="master")
    private Person person;
}
