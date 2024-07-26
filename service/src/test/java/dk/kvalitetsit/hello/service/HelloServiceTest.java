package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {
    @InjectMocks
    private HelloService helloService;

    @Test
    void testValidInput() {
        var input = new HelloServiceInput(UUID.randomUUID().toString());

        var result = helloService.helloServiceBusinessLogic(input);
        assertNotNull(result);
        assertNotNull(result.now());
        assertEquals(input.name(), result.name());
    }
}
