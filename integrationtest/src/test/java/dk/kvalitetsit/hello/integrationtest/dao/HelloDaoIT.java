package dk.kvalitetsit.hello.integrationtest.dao;

import dk.kvalitetsit.hello.dao.HelloDao;
import dk.kvalitetsit.hello.dao.HelloDaoImpl;
import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import dk.kvalitetsit.hello.integrationtest.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HelloDaoIT extends BaseTest {
    private static final HelloDao helloDao = new HelloDaoImpl(appDatabase.getDatasource());

    @Test
    public void testFindAll() {
        var input = HelloEntity.createInstance(UUID.randomUUID().toString());

        helloDao.insert(input);

        var result = helloDao.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(input.name(), result.get(0).name());
        assertNotNull(result.get(0).id());
    }

    @Test
    public void testFindByName() {
        var name = UUID.randomUUID().toString();
        var entity = HelloEntity.createInstance(name);

        helloDao.insert(entity);

        var result = helloDao.findByName(name);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(name, result.get(0).name());
        assertNotNull(result.get(0).id());
    }

    @Test
    public void testFindByNameWhenNameDoesNotExist() {
        var name = UUID.randomUUID().toString();

        var result = helloDao.findByName(name);

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
