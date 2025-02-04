package dk.kvalitetsit.hello.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource(@Value("${jdbc.url}") String jdbcUrl, @Value("${jdbc.user}") String jdbcUser, @Value("${jdbc.pass}") String jdbcPass) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcUser);
        hikariConfig.setPassword(jdbcPass);
        return new HikariDataSource(hikariConfig);
    }
}
