package app.Problems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class P07_Print_All_Minion_Names implements Runnable {

    private Connection connection;

    public P07_Print_All_Minion_Names(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            this.printMinionsNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printMinionsNames() throws SQLException {
        ArrayDeque<String> minionsQuee = new ArrayDeque<>();

        String query = "SELECT m.name FROM minions AS m";
        PreparedStatement allMinionsPrepSt = connection.prepareStatement(query);
        ResultSet resultSet = allMinionsPrepSt.executeQuery();

        while (resultSet.next()) {
            String minionName = resultSet.getString(1);
            minionsQuee.addLast(minionName);
        }

        int size = minionsQuee.size();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                System.out.println(minionsQuee.removeFirst());
            } else {
                System.out.println(minionsQuee.removeLast());
            }
        }
    }
}
