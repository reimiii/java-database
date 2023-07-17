package franxx.code.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_database");
        config.setUsername("root");
        config.setPassword("password");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);


        try {
            HikariDataSource source = new HikariDataSource(config);
            Connection connection = source.getConnection();

            connection.close();
            source.close();
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testUtil() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
    }
}
