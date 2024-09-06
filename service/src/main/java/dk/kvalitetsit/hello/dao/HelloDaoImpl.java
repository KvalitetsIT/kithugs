package dk.kvalitetsit.hello.dao;

import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class HelloDaoImpl implements HelloDao {
    private static final Logger logger = LoggerFactory.getLogger(HelloDaoImpl.class);
    private final NamedParameterJdbcTemplate template;

    public HelloDaoImpl(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insert(HelloEntity helloEntity) {
        logger.info("Inserting new entry in database.");

        var sql = "insert into hello_table(name) values(:name)";

        var parameterMap = Map.of("name", helloEntity.name());

        template.update(sql, parameterMap);
    }

    @Override
    public List<HelloEntity> findAll() {
        var sql = "select * from hello_table";

        return template.query(sql, new DataClassRowMapper<>(HelloEntity.class));
    }

    @Override
    public List<HelloEntity> findOne(HelloEntity helloEntity) {
        var sql = "select * from hello_table where name=:name";
        var parameterMap = Map.of("name", helloEntity.name());

        var results = template.query(sql, parameterMap, new DataClassRowMapper<>(HelloEntity.class));

        return results;
    }
}
