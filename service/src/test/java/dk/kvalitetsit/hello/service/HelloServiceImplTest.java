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
import java.util.List;
import java.util.ArrayList;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

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

        var result = helloService.helloServicePost(input);
        assertNotNull(result);
        assertNotNull(result.now());
        assertEquals(input.name(), result.name());
    }

    @Test
    void testGetNull () {
        var input = new HelloServiceInput(null);

        List<HelloEntity> mockDbResult = new ArrayList<>();

        Mockito.when(helloDao.findAll()).thenReturn(mockDbResult);

        var result = helloService.helloServiceGetByName(input);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        Mockito.verify(helloDao, times(1)).findAll();
        Mockito.verify(helloDao, times(0)).findByName(Mockito.anyString());
    }

    @Test
    void testGetNameNotInDb () {
        var name = UUID.randomUUID().toString();
        var input = new HelloServiceInput(name);

        List<HelloEntity> mockDbResult = new ArrayList<>();

        Mockito.when(helloDao.findByName(Mockito.anyString())).thenReturn(mockDbResult);

        var result = helloService.helloServiceGetByName(input);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        Mockito.verify(helloDao, times(0)).findAll();
        Mockito.verify(helloDao, times(1)).findByName(name);
    }

    @Test
    void testGetNameInDb () {
        var name = UUID.randomUUID().toString();
        var input = new HelloServiceInput(name);

        List<HelloEntity> mockDbResult = new ArrayList<>();
        mockDbResult.add(new HelloEntity((long) 1, name));

        Mockito.when(helloDao.findByName(Mockito.anyString())).thenReturn(mockDbResult);

        var result = helloService.helloServiceGetByName(input);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(name, result.get(0).name());
        assertTrue(result.get(0).now() instanceof ZonedDateTime);

        Mockito.verify(helloDao, times(0)).findAll();
        Mockito.verify(helloDao, times(1)).findByName(name);
    }

}
