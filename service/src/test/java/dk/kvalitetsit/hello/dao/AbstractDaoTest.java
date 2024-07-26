package dk.kvalitetsit.hello.dao;

import dk.kvalitetsit.hello.configuration.TestConfiguration;
import dk.kvalitetsit.hello.configuration.DatabaseConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MariaDBContainer;

@ExtendWith(SpringExtension.class)
@PropertySource("test.properties")
@ContextConfiguration(
        classes = { TestConfiguration.class, DatabaseConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
abstract public class AbstractDaoTest {
    private static Object initialized = null;

    @BeforeAll
   public static void setupMariadbJdbcUrl() {
        if (initialized == null) {
            var username = "hellouser";
            var password = "secret1234";

            MariaDBContainer mariadb = new MariaDBContainer("mariadb:10.6")
                    .withDatabaseName("hellodb")
                    .withUsername(username)
                    .withPassword(password);
            mariadb.start();

            String jdbcUrl = mariadb.getJdbcUrl();
            System.setProperty("jdbc.url", jdbcUrl);
            System.setProperty("jdbc.user", username);
            System.setProperty("jdbc.pass", password);

            initialized = new Object();
        }
    }
}

