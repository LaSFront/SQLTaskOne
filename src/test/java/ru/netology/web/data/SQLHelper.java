package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }
    //метод подключения к БД
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }
    // метод возвращения кода верификации (последний в списке)
    @SneakyThrows
    public static DataHelper.Verification getVerificationCode() {
        var codeSQLQuery = "SELECT code FROM auth_codes ORDER BY created LIMIT 1";
        var connection = getConn();
        var code = runner.query(connection,codeSQLQuery, new ScalarHandler<String>());
        return new DataHelper.Verification(code);
    }
    //метод очистки таблиц
    @SneakyThrows
    public static void cleanDB() {
        var connection = getConn();
        runner.execute(connection,"DELETE FROM auth_codes");
        runner.execute(connection,"DELETE FROM card_transactions");
        runner.execute(connection,"DELETE FROM cards");
        runner.execute(connection,"DELETE FROM users");
    }
}
