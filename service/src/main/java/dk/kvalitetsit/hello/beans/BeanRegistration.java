package dk.kvalitetsit.hello.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dk.kvalitetsit.hello.configuration.ServiceConfiguration;
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
    public DataSource dataSource() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(configuration.db().url());
        hikariConfig.setUsername(configuration.db().username());
        hikariConfig.setPassword(configuration.db().password());
        return new HikariDataSource(hikariConfig);
    }
}
