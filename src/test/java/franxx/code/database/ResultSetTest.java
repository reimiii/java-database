package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {
    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                select * from customers
                """;
        ResultSet set = statement.executeQuery(sql);

        while (set.next()) {
            String id = set.getString("id");
            String name = set.getString("name");
            String email = set.getString("email");

            System.out.println(
                    String.join("| ", id, name, email)
            );
        }

        connection.close();
        statement.close();
        set.close();
    }
}
