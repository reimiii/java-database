package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementTest {
    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();

        String username = "admin'; #";
        String password = "salam";
        String sql = """
                select * from admin where username = ? and password = ?;
                """;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet set = statement.executeQuery();

        if (set.next()) {
            // Success
            System.out.println("Login Success: " +
                    set.getString("username"));
        } else {
            // Failed
            System.out.println("Login Failed");
        }

        connection.close();
        statement.close();
        set.close();
    }
}
