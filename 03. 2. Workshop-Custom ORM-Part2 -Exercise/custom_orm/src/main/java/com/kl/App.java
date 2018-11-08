package com.kl;

import com.kl.db.EntityDbContext;
import com.kl.db.base.DbContext;
import com.kl.entities.Department;
import com.kl.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/soft_uni_simple";

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connection connection = getConnection();

        DbContext<User> usersDbContext =
                getDbContext(connection, User.class);

        DbContext<Department> departmentDbContext =
                getDbContext(connection, Department.class);

        User user = new User("Pesho", "Peshev");
        user.setAge(25);
        user.setUcn("1234567890");

        User pena = new User("Pena", "Penewa");

        usersDbContext.persist(pena);
        usersDbContext.persist(user);


        connection.close();
    }

    private static <T> DbContext<T> getDbContext(Connection connection, Class<T> klass) throws SQLException {
        return new EntityDbContext<>(connection, klass);
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CONNECTION_STRING,
                "root",
                ""
        );
    }
}
