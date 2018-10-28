package app.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P03_Get_Minion_Names implements Runnable {
    private Connection connection;

    public P03_Get_Minion_Names(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        try {
            int id = Integer.parseInt(reader.readLine());
            this.getVillainName(sb, id);
            this.getMinionNames(sb, id);
            System.out.print(sb.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    private void getVillainName(StringBuilder sb, int id) throws SQLException {
        String query = "SELECT v.name FROM villains AS v WHERE v.id = ?;";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString(1);
            sb.append(String.format("Villain: %s%n",villainName));
        }
        if(sb.toString().equalsIgnoreCase("")){
            sb.append(String.format("No villain with ID %d exists in the database.\n", id));
        }
    }

    private void getMinionNames(StringBuilder sb, int id) throws SQLException {
        String query = "SELECT m.name, m.age " +
                "FROM villains AS v " +
                "LEFT JOIN minions_villains v2 " +
                "on v.id = v2.villain_id " +
                "LEFT JOIN minions m " +
                "on v2.minion_id = m.id " +
                "WHERE v.id = ?;";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        int index = 0;
        while (resultSet.next()) {
            String minionName = resultSet.getString(1);
            int minionAge= resultSet.getInt(2);
            if(minionName != null){
                sb.append(String.format("%d. %s %d%n",index, minionName, minionAge));
            }
            index++;
        }
    }
}
