package dk.kvalitetsit.kithugs.service;

import dk.kvalitetsit.kithugs.service.model.HelloServiceInput;
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
        var input = new HelloServiceInput();
        input.setName(UUID.randomUUID().toString());

        var result = helloService.helloServiceBusinessLogic(input);
        assertNotNull(result);
        assertNotNull(result.getNow());
        assertEquals(input.getName(), result.getName());
    }
}
