package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false&serverTimezone=UTC";
    //Replace with your user name
    private static final String USER = "root";
    //Replace with your password
    private static final String PASSWORD = "";

    public static void main(String[] args)  {
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);

        try(Connection connection = DriverManager.getConnection(
                URL, properties)){
            Engine engine = new Engine(connection);
            engine.run();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
