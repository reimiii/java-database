package franxx.code.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    @BeforeAll
    static void beforeAll() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("database connected");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("database connected");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectionCloseDefaultIPersonallyLike() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("database connected");

            connection.close();
            System.out.println("database closed");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
