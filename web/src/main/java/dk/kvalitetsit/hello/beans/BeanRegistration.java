package dk.kvalitetsit.hello.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dk.kvalitetsit.hello.configuration.ServiceConfiguration;
import dk.kvalitetsit.hello.dao.HelloDao;
import dk.kvalitetsit.hello.dao.HelloDaoImpl;
import dk.kvalitetsit.hello.service.HelloService;
import dk.kvalitetsit.hello.service.HelloServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(ServiceConfiguration.class)
public class BeanRegistration {
    private final ServiceConfiguration configuration;

    public BeanRegistration(ServiceConfiguration configuration) {
        this.configuration = configuration;
    }

    @Bean
    public HelloService helloService(HelloDao helloDao) {
        return new HelloServiceImpl(helloDao);
    }

    @Bean
    public HelloDao helloDao(DataSource dataSource) {
        return new HelloDaoImpl(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(configuration.db().url());
        hikariConfig.setUsername(configuration.db().username());
        hikariConfig.setPassword(configuration.db().password());
        return new HikariDataSource(hikariConfig);
    }
}
