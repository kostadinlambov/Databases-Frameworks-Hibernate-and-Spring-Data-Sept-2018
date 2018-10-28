package app.Problems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P02_Get_Villains_Names implements Runnable{

    private Connection connection;

    public P02_Get_Villains_Names(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            this.getVillainsNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getVillainsNames() throws SQLException {
        String query = "SELECT v.name, count(v2.minion_id) AS `count`" +
                " FROM villains AS v JOIN minions_villains v2 " +
                "on v.id = v2.villain_id " +
                "GROUP BY v.name " +
                "HAVING `count` > ? " +
                "ORDER BY `count` DESC";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.setInt(1, 15);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            System.out.println(String.format("%s %d",
                    resultSet.getString(1),
                    resultSet.getInt(2)));
        }
    }
}
