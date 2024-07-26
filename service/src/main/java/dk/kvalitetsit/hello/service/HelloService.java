package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import dk.kvalitetsit.hello.service.model.HelloServiceOutput;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class HelloService {

    public HelloServiceOutput helloServiceBusinessLogic(HelloServiceInput input) {
        return new HelloServiceOutput(input.name(), ZonedDateTime.now());
    }
}
