package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    private final String DB_CONFIG_PATH = "/db_config.properties";
    private static DbConnector INSTANCE;
    private Connection connection;
    private String DB_TYPE;
    private String DB_NAME;
    private String HOST;
    private String PORT;
    private String LOGIN;
    private String PASSWORD;

    public synchronized Connection getConnection() {
        if (this.connection != null) return this.connection;
        try {
            getAndSetProperties(DB_CONFIG_PATH);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return this.connection = DriverManager.getConnection(DB_TYPE + HOST + ":" + PORT + "/" + DB_NAME, LOGIN, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void getAndSetProperties(String configPath) throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream(configPath));

        DB_TYPE = properties.getProperty("db_type");
        DB_NAME = properties.getProperty("db_name");
        HOST = properties.getProperty("host_name");
        PORT = properties.getProperty("port");
        LOGIN = properties.getProperty("user_name");
        PASSWORD = properties.getProperty("user_password");

        System.out.println(DB_TYPE + HOST + ":" + PORT + "/" + DB_NAME + "\t login = " + LOGIN + "\t pass = " + PASSWORD);

    }

    private DbConnector() {
    }

    public static DbConnector getINSTANCE() { //LAZY
        return INSTANCE == null ? INSTANCE = new DbConnector() : INSTANCE;
    }
}