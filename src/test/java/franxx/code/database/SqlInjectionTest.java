package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {
    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";

        String sql = "select * from admin where username = '" + username +
                "' and password = '" + password + "'";

        System.out.println(sql);

        ResultSet set = statement.executeQuery(sql);

        if (set.next()) {
            // success login
            System.out.println("Login Success: " + set.getString("username"));
        } else {
            // failed login
            System.out.println("Login Failed: ");
        }

        connection.close();
        statement.close();
        set.close();
    }
}
