package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.dao.HelloDao;
import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class HelloServiceImplTest {
    @InjectMocks
    private HelloServiceImpl helloService;
    @Mock
    private HelloDao helloDao;

    @Test
    void testValidInput() {
        var input = new HelloServiceInput(UUID.randomUUID().toString());
        Mockito.doNothing().when(helloDao).insert(Mockito.any(HelloEntity.class));

        var result = helloService.helloServiceBusinessLogic(input);
        assertNotNull(result);
        assertNotNull(result.now());
        assertEquals(input.name(), result.name());
    }
}
