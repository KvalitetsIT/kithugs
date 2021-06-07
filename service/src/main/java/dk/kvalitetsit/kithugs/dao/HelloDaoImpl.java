package dk.kvalitetsit.kithugs.dao;

import dk.kvalitetsit.kithugs.dao.entity.HelloEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

public class HelloDaoImpl implements HelloDao {
    private static final Logger logger = LoggerFactory.getLogger(HelloDaoImpl.class);
    private final DataSource dataSource;

    public HelloDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(HelloEntity helloEntity) {
        logger.info("Inserting new entry in database.");

        var sql = "insert into hello_table(name) values(:name)";
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        var parameterMap = new HashMap<String, Object>();
        parameterMap.put("name", helloEntity.getName());

        template.update(sql, parameterMap);
    }

    @Override
    public List<HelloEntity> findAll() {
        var sql = "select * from hello_table";

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template.query(sql, new BeanPropertyRowMapper<>(HelloEntity.class));
    }
}
