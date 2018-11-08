package com.minkov;

import com.minkov.db.EntityDbContext;
import com.minkov.db.base.DbContext;
import com.minkov.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/soft_uni_simple";

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection connection = getConnection();

        DbContext<User> usersDbContext =
                getDbContext(connection, User.class);

//        usersDbContext.find()
//                .forEach(System.out::println);
        User user = usersDbContext.findFirst();
        System.out.println(user);
//        user.setLastName("Wse taq");S
//
//        usersDbContext.persist(user);
        //        DbContext<Department> departmentDbContext
//                = getDbContext(connection, Department.class);
//        departmentDbContext.find()
//                .forEach(System.out::println);

        connection.close();
    }

    private static <T> DbContext<T> getDbContext(Connection connection, Class<T> klass) {
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
