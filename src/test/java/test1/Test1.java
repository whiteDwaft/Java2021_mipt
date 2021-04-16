package test1;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pack.dao.*;
import pack.db.DbInit;
import pack.db.SimpleJdbcTemplate;
import pack.service.ExcelWriter;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void getTask1() throws SQLException {
        source.statement(stmt -> {
            List<Task1Dao> list = new ArrayList<>();
            String city = "";
            List<String> airCode = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("SELECT A.CITY AS CITY, B.AIRPORT_CODE AS AIRPORT_CODE FROM(" +
                    "SELECT CITY , COUNT(AIRPORT_CODE) AS COUNT FROM AIRPORTS " +
                    "GROUP BY CITY " +
                    ")A JOIN(" +
                    "SELECT CITY , AIRPORT_CODE FROM AIRPORTS " +
                    ")B ON A.CITY  = B.CITY WHERE A.COUNT > 1;");
            resultSet.next();
            String s0 = resultSet.getString("CITY");
            String code0 = resultSet.getString("AIRPORT_CODE");
            city = s0;
            airCode = new ArrayList<>();
            airCode.add(code0);
            while (resultSet.next()) {
                String s = resultSet.getString("CITY");
                String code = resultSet.getString("AIRPORT_CODE");
                if (!s.equals(city)) {
                    list.add(new Task1Dao(city, airCode));
                    city = s;
                    airCode = new ArrayList<>();
                }
                airCode.add(code);
            }
            list.add(new Task1Dao(city, airCode));
            ExcelWriter.writeReport(list);
        });
    }

    @Test
    public void getTask2() throws SQLException {
        source.statement(stmt -> {
            List<Task2Dao> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("SELECT B.CITY, count(A.status) as count  FROM (" +
                    "select DEPARTURE_AIRPORT, STATUS from FLIGHTS where STATUS  = 'Cancelled'" +
                    ")A inner join(" +
                    "select CITY, AIRPORT_CODE from AIRPORTS" +
                    ")B on A.DEPARTURE_AIRPORT  = B.AIRPORT_CODE  group by b.city order by count desc;");
            while (resultSet.next()) {
                list.add(new Task2Dao(resultSet.getString("city"), resultSet.getInt("count")));
            }
            ExcelWriter.writeReport(list);
        });
    }

    @Test
    public void getTask3() throws SQLException {
        source.statement(stmt -> {
            List<Task3Dao> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("select B.CITY , min(A.min) as minimum from " +
                    "(" +
                    "select DEPARTURE_AIRPORT, min(60 *  EXTRACT(HOUR FROM (PARSEDATETIME(ACTUAL_ARRIVAL ,'yyyy-MM-dd HH:mm:ss')  - PARSEDATETIME(ACTUAL_DEPARTURE,'yyyy-MM-dd HH:mm:ss'))) + EXTRACT(MINUTE FROM (PARSEDATETIME(ACTUAL_ARRIVAL ,'yyyy-MM-dd HH:mm:ss')  - PARSEDATETIME(ACTUAL_DEPARTURE,'yyyy-MM-dd HH:mm:ss')))) as min from FLIGHTS where " +
                    "STATUS = 'Arrived' group by DEPARTURE_AIRPORT " +
                    ")A join " +
                    "(" +
                    "select AIRPORT_CODE ,CITY  from AIRPORTS " +
                    ") B on A.DEPARTURE_AIRPORT = B.AIRPORT_CODE group by B.CITY order by minimum");
            while (resultSet.next()) {
                list.add(new Task3Dao(resultSet.getString("city"), resultSet.getInt("minimum")));
            }
            ExcelWriter.writeReport(list);
        });
    }

    @Test
    public void getTask4() throws SQLException {
        source.statement(stmt -> {
            List<Task4Dao> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("select EXTRACT(MONTH FROM (PARSEDATETIME(SCHEDULED_DEPARTURE ,'yyyy-MM-dd HH:mm:ss'))) as month , count(STATUS) as count from FLIGHTS " +
                    "where status = 'Cancelled'  group by month");
            while (resultSet.next()) {
                list.add(new Task4Dao(resultSet.getInt("month"), resultSet.getInt("count")));
            }
            ExcelWriter.writeReport(list);
        });
    }

    @Test
    public void getTask5() throws SQLException {
        source.statement(stmt -> {
            List<Task5Dao> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("select DAYOFWEEK(PARSEDATETIME(SCHEDULED_DEPARTURE ,'yyyy-MM-dd HH:mm:ss')) as day, count(FLIGHT_ID) as count from FLIGHTS " +
                    "where DEPARTURE_AIRPORT in " +
                    "( " +
                    "select AIRPORT_CODE from AIRPORTS where CITY = '{\"en\": \"Moscow\", \"ru\": \"РњРѕСЃРєРІР°\"}' " +
                    ")group by day");
            while (resultSet.next()) {
                list.add(new Task5Dao(resultSet.getInt("day"), resultSet.getInt("count")));
            }
            ExcelWriter.writeReport(list);
        });
    }


}
