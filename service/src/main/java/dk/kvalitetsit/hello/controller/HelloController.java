package dk.kvalitetsit.hello.controller;

import dk.kvalitetsit.hello.service.HelloService;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import org.openapitools.api.KithugsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements KithugsApi {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public ResponseEntity<org.openapitools.model.HelloResponse> v1HelloPost(org.openapitools.model.HelloRequest helloRequest) {
        logger.debug("Enter POST hello.");

        var serviceInput = new HelloServiceInput();
        serviceInput.setName(helloRequest.getName());

        var serviceResponse = helloService.helloServiceBusinessLogic(serviceInput);

        var helloResponse = new org.openapitools.model.HelloResponse();
        helloResponse.setName(serviceResponse.getName());
        helloResponse.setNow(serviceResponse.getNow().toOffsetDateTime());

        return ResponseEntity.ok(helloResponse);
    }
}
