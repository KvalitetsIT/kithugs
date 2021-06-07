package dk.kvalitetsit.kithugs.controller;

import dk.kvalitetsit.kithugs.api.HelloRequest;
import dk.kvalitetsit.kithugs.controller.exception.BadRequestException;
import dk.kvalitetsit.kithugs.service.HelloService;
import dk.kvalitetsit.kithugs.service.model.HelloServiceInput;
import dk.kvalitetsit.kithugs.service.model.HelloServiceOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

public class HelloControllerTest {
    private HelloController helloController;
    private HelloService helloService;

    @Before
    public void setup() {
        helloService = Mockito.mock(HelloService.class);

        helloController = new HelloController(helloService);
    }

    @Test
    public void testCallController() {
        var input = new HelloRequest();
        input.setName(UUID.randomUUID().toString());

        var expectedDate = ZonedDateTime.now();
        Mockito.when(helloService.helloServiceBusinessLogic(Mockito.any())).then(a -> {
            HelloServiceOutput output = new HelloServiceOutput();
            output.setNow(expectedDate);
            output.setName(a.getArgument(0, HelloServiceInput.class).getName());
            return output;
        });

        var result = helloController.hello(input);

        assertNotNull(result);
        assertEquals(input.getName(), result.getName());
        assertEquals(expectedDate, result.getNow());

        var inputArgumentCaptor = ArgumentCaptor.forClass(HelloServiceInput.class);
        Mockito.verify(helloService, times(1)).helloServiceBusinessLogic(inputArgumentCaptor.capture());

        assertNotNull(inputArgumentCaptor.getValue());
        assertEquals(input.getName(), inputArgumentCaptor.getValue().getName());
    }

    @Test(expected = BadRequestException.class)
    public void testNullInput() {
        helloController.hello(null);
    }
}
