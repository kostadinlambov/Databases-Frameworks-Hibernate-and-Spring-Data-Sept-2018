package app.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P06_Remove_Villain implements Runnable {

    private Connection connection;

    public P06_Remove_Villain(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int villainId = Integer.parseInt(reader.readLine());
            removeVillain(villainId);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeVillain(int villainId) throws SQLException {
        String selectVillainQuery = "SELECT * FROM villains AS v WHERE v.id = ?";
        PreparedStatement selectVillainPrepSt = connection.prepareStatement(selectVillainQuery);
        selectVillainPrepSt.setInt(1, villainId);

        ResultSet selectVillainResult = selectVillainPrepSt.executeQuery();

        if(selectVillainResult.next()){
            String deleteMappingTableEntriesQuery = "DELETE FROM minions_villains WHERE villain_id = ?";
            PreparedStatement deleteMappingTableEntriesPrepSt = connection.prepareStatement(deleteMappingTableEntriesQuery);
            deleteMappingTableEntriesPrepSt.setInt(1, villainId);

            int deletedRowsCount = deleteMappingTableEntriesPrepSt.executeUpdate();
            String deleteVillainQuery = "DELETE FROM villains WHERE id = ?";
            PreparedStatement deleteVillainPrepSt = connection.prepareStatement(deleteVillainQuery);
            deleteVillainPrepSt.setInt(1, villainId);

            int deletedVillainCount = deleteVillainPrepSt.executeUpdate();

            if(deletedVillainCount > 0){
                    String villainName = selectVillainResult.getString(2);
                    System.out.printf("%s was deleted\n", villainName);
                    System.out.printf("%d minions released\n", deletedRowsCount);
            }
        }else{
            System.out.println("No such villain was found");
        }

    }
}
