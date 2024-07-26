package dk.kvalitetsit.hello.dao;

import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HelloDaoTest extends AbstractDaoTest {
    @Autowired
    private HelloDao helloDao;

    @Test
    public void testByMessageId() {
        var input = HelloEntity.createInstance(UUID.randomUUID().toString());

        helloDao.insert(input);

        var result = helloDao.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(input.name(), result.get(0).name());
        assertNotNull(result.get(0).id());
    }
}
