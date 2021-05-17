package dk.kvalitetsit.kithugs.controller;

import dk.kvalitetsit.kithugs.api.HelloRequest;
import dk.kvalitetsit.kithugs.controller.exception.BadRequestException;
import dk.kvalitetsit.kithugs.service.HelloService;
import dk.kvalitetsit.kithugs.api.HelloResponse;
import dk.kvalitetsit.kithugs.service.model.HelloServiceInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @PostMapping(value = "/v1/hello")
    public @ResponseBody HelloResponse hello(@RequestBody(required = false) HelloRequest body) {
        logger.debug("Enter POST hello.");

        if(body == null) {
            throw new BadRequestException();
        }

        var serviceInput = new HelloServiceInput();
        serviceInput.setName(body.getName());

        var serviceResponse = helloService.helloServiceBusinessLogic(serviceInput);

        var helloResponse = new HelloResponse();
        helloResponse.setName(serviceResponse.getName());
        helloResponse.setNow(serviceResponse.getNow());

        return helloResponse;
    }
}
