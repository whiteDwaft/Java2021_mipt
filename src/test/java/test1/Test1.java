package test1;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pack.db.DbInit;
import pack.db.SimpleJdbcTemplate;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
    private SimpleJdbcTemplate source = new SimpleJdbcTemplate(
            JdbcConnectionPool.create("jdbc:h2:tcp://localhost/~/test", "sa", ""));

    @BeforeEach
    void setupDB() throws IOException, SQLException {
        new DbInit(source).create();
    }

    @AfterEach
    void tearDownDB() throws SQLException, IOException {
        source.statement(stmt -> {
            stmt.execute("drop all objects;");
        });
    }

    @Test
    public void getConferenceCount() throws SQLException {
        source.statement(stmt -> {
            ResultSet resultSet = stmt.executeQuery("select count(*) from aircrafts");
            resultSet.next();
            System.out.println(resultSet.getInt(1));
        });
    }


}
