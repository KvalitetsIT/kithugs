package dk.kvalitetsit.hello.controller;

import dk.kvalitetsit.hello.controller.exception.BadRequestException;
import dk.kvalitetsit.hello.service.HelloService;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import dk.kvalitetsit.hello.service.model.HelloServiceOutput;

import org.openapitools.api.KithugsApi;
import org.openapitools.model.DetailedError;
import org.openapitools.model.HelloRequest;
import org.openapitools.model.HelloResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
// CORS - Consider if this is needed in your application. Only here to make Swagger UI work.
@CrossOrigin(origins = "http://localhost")
public class HelloController implements KithugsApi {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public ResponseEntity<List<HelloResponse>> v1HelloGet(String name) {
        logger.debug("Enter GET hello.");

        List <HelloServiceOutput> serviceResponseList;

        // Just for demonstrating error response. Actual validation should most likely be somewhere else.
        if(name != null && name.equalsIgnoreCase("NOT_VALID")) {
            throw new BadRequestException(DetailedError.DetailedErrorCodeEnum._10, "%s is not a valid name.".formatted(name));
        }
        var serviceInput = new HelloServiceInput(name);

        // Gets all entries of the name in the db. If name is null, get entire db contents.
        serviceResponseList = helloService.helloServiceGetByName(serviceInput);

        List<HelloResponse> helloResponseList = serviceResponseList.stream()
            .map(serviceResponse -> {
                HelloResponse helloResponse = new HelloResponse();
                helloResponse.setName(serviceResponse.name());
                helloResponse.setNow(serviceResponse.now().toOffsetDateTime());
                return helloResponse;
            })
            .toList();

        return ResponseEntity.ok(helloResponseList);
    }

    @Override
    public ResponseEntity<HelloResponse> v1HelloPost(HelloRequest request) {
        var name = request.getName();
        logger.debug("Enter POST hello with name: {}", name);

        // Validate the request
        if (name.equals("")) {
            throw new BadRequestException(DetailedError.DetailedErrorCodeEnum._10, "Name cannot be null or empty.");
        }

        var serviceInput = new HelloServiceInput(name);

        var serviceResponse = helloService.helloServicePost(serviceInput);

        var helloResponse = new HelloResponse();
        
        helloResponse.setName(serviceResponse.name());
        helloResponse.setNow(serviceResponse.now().toOffsetDateTime());

        return ResponseEntity.ok(helloResponse);
    }
}
