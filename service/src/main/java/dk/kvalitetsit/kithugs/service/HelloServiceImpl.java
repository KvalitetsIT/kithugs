package dk.kvalitetsit.kithugs.service;

import dk.kvalitetsit.kithugs.service.model.HelloServiceInput;
import dk.kvalitetsit.kithugs.service.model.HelloServiceOutput;

import java.time.LocalDateTime;

public class HelloServiceImpl implements HelloService {
    @Override
    public HelloServiceOutput helloServiceBusinessLogic(HelloServiceInput input) {
        var output = new HelloServiceOutput();
        output.setNow(LocalDateTime.now());
        output.setName(input.getName());

        return output;
    }
}
