package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    private final String DB_CONFIG_PATH = "src/main/resources/db_config.properties";
    private static DbConnector INSTANCE;
    private Connection connection;
    private String DB_TYPE;
    private String DB_NAME;
    private String HOST;
    private String PORT;
    private String LOGIN;
    private String PASSWORD;

    public Connection getConnection() throws SQLException {
        if (this.connection != null) return this.connection;
        getAndSetProperties(DB_CONFIG_PATH);
        return this.connection = DriverManager.getConnection(DB_TYPE + HOST + ":" + PORT + "/" + DB_NAME, LOGIN, PASSWORD);
    }

    private void getAndSetProperties(String configPath) {
        Properties properties = new Properties();
        try (FileInputStream fileStream = new FileInputStream(configPath)) {
            properties.load(fileStream);
            DB_TYPE = properties.getProperty("db_type");
            DB_NAME = properties.getProperty("db_name");
            HOST = properties.getProperty("host_name");
            PORT = properties.getProperty("port");
            LOGIN = properties.getProperty("user_name");
            PASSWORD = properties.getProperty("user_password");

            System.out.println(DB_TYPE + HOST + ":" + PORT + "/" + DB_NAME + "\t login = " + LOGIN + "\t pass = " + PASSWORD);

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound!!!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DbConnector() {
    }

    public static DbConnector getINSTANCE() { //LAZY
        return INSTANCE == null ? INSTANCE = new DbConnector() : INSTANCE;
    }
}