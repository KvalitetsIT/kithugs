package dk.kvalitetsit.hello.controller;

import org.openapitools.model.HelloRequest;
import dk.kvalitetsit.hello.service.HelloService;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import dk.kvalitetsit.hello.service.model.HelloServiceOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {
    @InjectMocks
    private HelloController helloController;
    @Mock
    private HelloService helloService;

    @Test
    void testCallController() {
        var name = UUID.randomUUID().toString();

        var expectedDate = ZonedDateTime.now();

        List<HelloServiceOutput> mockServiceResponseList = new ArrayList<>();
        mockServiceResponseList.add(new HelloServiceOutput(name, expectedDate));

        Mockito.when(helloService.helloServiceGetAll()).thenReturn(mockServiceResponseList);

        var result = helloController.v1HelloGet(name);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(name, result.getBody().get(0).getName());
        assertEquals(expectedDate.toOffsetDateTime(), result.getBody().get(0).getNow());

        Mockito.verify(helloService, times(1)).helloServiceGetAll();
    }

    @Test
    void testPostController() {
        var name = UUID.randomUUID().toString();

        var expectedDate = ZonedDateTime.now();
        Mockito.when(helloService.helloServicePost(Mockito.any())).then(a -> new HelloServiceOutput(a.getArgument(0, HelloServiceInput.class).name(), expectedDate));
        
        var result = helloController.v1HelloPost(name);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(name, result.getBody().getName());
        assertEquals(expectedDate.toOffsetDateTime(), result.getBody().getNow());

        var inputArgumentCaptor = ArgumentCaptor.forClass(HelloServiceInput.class);
        Mockito.verify(helloService, times(1)).helloServicePost(inputArgumentCaptor.capture());

        assertNotNull(inputArgumentCaptor.getValue());
        assertEquals(name, inputArgumentCaptor.getValue().name());
    }

}
