package franxx.code.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {
    @Test
    void testDatabaseMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());

        ResultSet resultSet = metaData.getTables("java_database", null, null, null);

        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");

            System.out.println("Table: " + tableName);
        }

        resultSet.close();
        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        String sql = "insert into comments(email, comment) values (?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = statement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());
//        System.out.println(parameterMetaData.getParameterType(1)); error not support
//        System.out.println(parameterMetaData.getParameterTypeName(1));

        statement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sample_time");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            String columnName = resultSetMetaData.getColumnName(i);
            int columnType = resultSetMetaData.getColumnType(i);
            String columnTypeName = resultSetMetaData.getColumnTypeName(i);

            System.out.println("Name: " + columnName);
            System.out.println("Type: " + columnType); // java.sql.Types.
            System.out.println("Type Name: " + columnTypeName);

            System.out.println();
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
