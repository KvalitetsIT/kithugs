package dk.kvalitetsit.kithugs.configuration;

import dk.kvalitetsit.kithugs.dao.HelloDao;
import dk.kvalitetsit.kithugs.dao.HelloDaoImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class TestConfiguration {
    // Configure beans used for test

    @Bean
    public HelloDao helloDao(DataSource dataSource) {
        return new HelloDaoImpl(dataSource);
    }
}
