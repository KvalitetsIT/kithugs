package dk.kvalitetsit.kithugs.service;

import dk.kvalitetsit.kithugs.service.model.HelloServiceInput;
import dk.kvalitetsit.kithugs.service.model.HelloServiceOutput;

import java.time.ZonedDateTime;

public class HelloServiceImpl implements HelloService {
    @Override
    public HelloServiceOutput helloServiceBusinessLogic(HelloServiceInput input) {
        var output = new HelloServiceOutput();
        output.setNow(ZonedDateTime.now());
        output.setName(input.getName());

        return output;
    }
}
