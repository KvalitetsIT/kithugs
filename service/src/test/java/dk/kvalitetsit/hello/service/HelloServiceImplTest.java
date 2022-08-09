package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HelloServiceImplTest {
    private HelloService helloService;

    @Before
    public void setup() {
        helloService = new HelloServiceImpl();
    }

    @Test
    public void testValidInput() {
        var input = new HelloServiceInput(UUID.randomUUID().toString());

        var result = helloService.helloServiceBusinessLogic(input);
        assertNotNull(result);
        assertNotNull(result.now());
        assertEquals(input.name(), result.name());
    }
}
