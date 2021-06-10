package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.service.model.HelloServiceOutput;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;

public interface HelloService {
    HelloServiceOutput helloServiceBusinessLogic(HelloServiceInput input);
}
