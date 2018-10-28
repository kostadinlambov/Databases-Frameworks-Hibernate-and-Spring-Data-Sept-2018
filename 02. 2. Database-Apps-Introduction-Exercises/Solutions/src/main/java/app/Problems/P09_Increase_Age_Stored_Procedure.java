package app.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class P09_Increase_Age_Stored_Procedure implements Runnable {
    private Connection connection;

    public P09_Increase_Age_Stored_Procedure(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int id =  Integer.parseInt(reader.readLine());
            callProcedure(id);
            printMinion(id);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void callProcedure(int id) throws SQLException {
        String procedure = "CALL usp_get_older(?)";
        CallableStatement callableStatement = connection.prepareCall(procedure);
        callableStatement.setInt(1, id);

        callableStatement.execute();
    }

    private void printMinion(int minionId) throws SQLException {
        String query = "SELECT m.id, m.name, m.age FROM minions AS m WHERE m.id = ?";
        PreparedStatement printMinionsPrepSt = this.connection.prepareStatement(query);
        printMinionsPrepSt.setInt(1, minionId);
        ResultSet minionResult = printMinionsPrepSt.executeQuery();

        if(minionResult.next()){
            int id = minionResult.getInt(1);
            String name = minionResult.getString(2);
            int age = minionResult.getInt(3);
            System.out.println(String.format("%d. %s %d", id, name, age));
        }
    }
}
