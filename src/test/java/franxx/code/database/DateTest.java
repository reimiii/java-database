package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTest {
    @Test
    void testDate() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();

        String sql = """
                insert into sample_time (sample_date, sample_time, sample_timestamp) 
                values (?, ?, ?);
                """;

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDate(1, new Date(System.currentTimeMillis()));
        statement.setTime(2, new Time(System.currentTimeMillis()));
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Test
    void testDateQuery() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();

        String sql = """
                select * from sample_time;
                """;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Date date = resultSet.getDate("sample_date");
            Time time = resultSet.getTime("sample_time");
            Timestamp timestamp = resultSet.getTimestamp("sample_timestamp");

            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Timestamp: " + timestamp);
            System.out.println();
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
