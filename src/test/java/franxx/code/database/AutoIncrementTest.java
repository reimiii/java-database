package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {
    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();

        String sql = """
                insert into comments (email, comment) values (?, ?);
                """;

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        statement.setString(1, "Ze@Mail.com");
        statement.setString(2, "WHoOOOAA");


        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();

        if (keys.next()) {
            System.out.println("Id Comment: " + keys.getInt(1));
        }


        keys.close();
        statement.close();
        connection.close();
    }
}
