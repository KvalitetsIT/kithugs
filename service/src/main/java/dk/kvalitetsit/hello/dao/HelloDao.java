package dk.kvalitetsit.hello.dao;

import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@EnableTransactionManagement
public class HelloDao {
    private static final Logger logger = LoggerFactory.getLogger(HelloDao.class);
    private final NamedParameterJdbcTemplate template;

    public HelloDao(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(HelloEntity helloEntity) {
        logger.info("Inserting new entry in database.");

        var sql = "insert into hello_table(name) values(:name)";

        var parameterMap = Map.of("name", helloEntity.name());

        template.update(sql, parameterMap);
    }

    public List<HelloEntity> findAll() {
        var sql = "select * from hello_table";

        return template.query(sql, new DataClassRowMapper<>(HelloEntity.class));
    }
}
