package app.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class P08_Increase_Minions_Age implements Runnable {
    private Connection connection;

    public P08_Increase_Minions_Age(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int[] idArr = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            updateMinions(idArr);
            printAllMinions();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void printAllMinions() throws SQLException {
        String query = "SELECT m.id, m.name, m.age FROM minions AS m";
        PreparedStatement printMinionsPrepSt = this.connection.prepareStatement(query);
        ResultSet resultSet = printMinionsPrepSt.executeQuery();

        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.println(String.format("%d. %s %d", id, name, age));
        }
    }

    private void updateMinions(int[] idArr) throws SQLException {
        String query = "UPDATE minions SET name = upper(name), age = age + 1 WHERE id = ?";
        PreparedStatement updatePrepSt = this.connection.prepareStatement(query);
        for (int id : idArr) {
            updatePrepSt.setInt(1, id);
            updatePrepSt.executeUpdate();
        }
    }
}
