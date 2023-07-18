package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                insert into comments (email, comment) values ('me@mail', 'WUTTT');
                """;

        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();

        String sql = """
                insert into comments (email, comment) values (?, ?);
                """;

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            statement.clearParameters();
            statement.setString(1, "Ze@Mail.com");
            statement.setString(2, "WHoOOOAA");
            statement.addBatch();
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }
}
