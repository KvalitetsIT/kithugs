package dk.kvalitetsit.hello.integrationtest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * A simple wrapper class which abstracts away the complexity of the datasource and allows the caller to "clear" the db
 */
public class Database {
    private final DataSource dataSource;

    public Database(String host, Integer port, String name, String username, String password) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(String.format("jdbc:mariadb://%s:%s/%s", host, port, name));
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setInitializationFailTimeout(5000);
        this.dataSource = new HikariDataSource(hikariConfig);
    }

    public DataSource getDatasource() {
        return this.dataSource;
    }

    public void clear() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SHOW FULL TABLES WHERE Table_type = 'BASE TABLE'"
        );
        for (Map<String, Object> row : rows) {
            // Safer: pick first column explicitly
            String tableName = (String) row.get(row.keySet().iterator().next());
            if (!tableName.endsWith("_seq")) {
                jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
            }
        }
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
    }
}
