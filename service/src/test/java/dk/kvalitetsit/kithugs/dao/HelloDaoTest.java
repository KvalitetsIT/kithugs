package dk.kvalitetsit.kithugs.dao;

import dk.kvalitetsit.kithugs.dao.entity.HelloEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.Assert.*;

public class HelloDaoTest extends AbstractDaoTest {
    @Autowired
    private HelloDao helloDao;

    @Test
    public void testByMessageId() {
        var input = new HelloEntity();
        input.setName(UUID.randomUUID().toString());

        helloDao.insert(input);

        var result = helloDao.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(input.getName(), result.get(0).getName());
        assertNotNull(result.get(0).getId());
    }
}
