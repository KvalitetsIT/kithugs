package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.service.model.HelloServiceOutput;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import dk.kvalitetsit.hello.dao.entity.HelloEntity;

import java.util.List;

public interface HelloService {
    HelloServiceOutput helloServicePost(HelloServiceInput input);
    List<HelloServiceOutput> helloServiceGetAll();
}