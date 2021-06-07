package dk.kvalitetsit.kithugs.service;

import dk.kvalitetsit.kithugs.service.model.HelloServiceOutput;
import dk.kvalitetsit.kithugs.service.model.HelloServiceInput;

public interface HelloService {
    HelloServiceOutput helloServiceBusinessLogic(HelloServiceInput input);
}
