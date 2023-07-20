package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {
    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "insert into comments(email, comment) values (?, ?);";

        for (int i = 0; i < 100; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "the@mail.com");
            statement.setString(2, "NyaHallowww every nyannn");

            statement.executeUpdate();
            statement.close();
        }

        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "insert into comments(email, comment) values (?, ?);";

        for (int i = 0; i < 100; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "the@mail.com");
            statement.setString(2, "NyaHallowww every nyannn");

            statement.executeUpdate();
            statement.close();
        }

        connection.rollback();
        connection.close();
    }
}
