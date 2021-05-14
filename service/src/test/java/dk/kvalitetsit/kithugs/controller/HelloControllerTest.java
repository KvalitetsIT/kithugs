package dk.kvalitetsit.kithugs.controller;

import dk.kvalitetsit.kithugs.service.HelloService;
import org.junit.Before;
import org.mockito.Mockito;

public class HelloControllerTest {
    private HelloController smsController;
    private HelloService smsService;

    @Before
    public void setup() {
        smsService = Mockito.mock(HelloService.class);

        smsController = new HelloController(smsService);
    }
}
