package dk.kvalitetsit.kithugs.controller;

import dk.kvalitetsit.kithugs.api.HelloRequest;
import dk.kvalitetsit.kithugs.service.HelloService;
import dk.kvalitetsit.kithugs.api.HelloResponse;
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

    public HelloController(HelloService smsService) {
        this.helloService = smsService;
    }

    @PostMapping(value = "/v1/hello")
    public @ResponseBody
    HelloResponse sms(@RequestBody HelloRequest body) {
        logger.debug("Enter POST hello.");

        return null;
    }
}
