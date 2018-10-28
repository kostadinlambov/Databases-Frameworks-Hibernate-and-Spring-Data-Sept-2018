package app.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P04_Add_Minion implements Runnable {

    private Connection connection;

    public P04_Add_Minion(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] minionsTokens = reader.readLine().split("\\s+");
            String minionName = minionsTokens[1];
            int minionsAge = Integer.parseInt(minionsTokens[2]);
            String town = minionsTokens[3];

            String[] villainTokens = reader.readLine().split("\\s+");
            String villainName = villainTokens[1];

            int townId = this.getTownId(town);
            int minionId = this.getMinionId(minionName , minionsAge, townId);
            int villainId = this.getVillainId(villainName);

            if(this.updateMappingTable(minionId, villainId)){
                System.out.printf("Successfully added %s to be minion of %s\n", minionName, villainName);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private int getMinionId(String minionName, int minionsAge, int townId) throws SQLException {
        String getTownIdQuery = "SELECT m.id FROM minions AS m WHERE m.name = ?";
        PreparedStatement getMinionIdPrepStatement = this.connection.prepareStatement(getTownIdQuery);
        getMinionIdPrepStatement.setString(1, minionName);

        ResultSet getMinionIdResult = getMinionIdPrepStatement.executeQuery();

        if(!getMinionIdResult.next()){
            this.addMinion(minionName,minionsAge, townId);
        }

        ResultSet getMinionIdUpdatedResult = getMinionIdPrepStatement.executeQuery();

        int minionId = 0;
        while (getMinionIdUpdatedResult.next()) {
            minionId = getMinionIdUpdatedResult.getInt(1);
        }

        return minionId;
    }

    private void addMinion(String minionName, int minionsAge, int town_id) throws SQLException {
        String addMinionQuery = "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?)";
        PreparedStatement addMinionPrepStatement = this.connection.prepareStatement(addMinionQuery);
        addMinionPrepStatement.setString(1, minionName);
        addMinionPrepStatement.setInt(2, minionsAge);
        addMinionPrepStatement.setInt(3, town_id);

        addMinionPrepStatement.executeUpdate();
    }

    private int getVillainId(String villainName) throws SQLException {
        String villainQuery = "SELECT * FROM villains AS v WHERE v.name = ?";
        PreparedStatement villainPrepStatement = connection.prepareStatement(villainQuery);
        villainPrepStatement.setString(1, villainName);
        ResultSet villainResult = villainPrepStatement.executeQuery();

        if(!villainResult.next()){
            this.addVillain(villainName);
        }

        ResultSet villainUpdatedResult = villainPrepStatement.executeQuery();

        int villainId = 0;
        while (villainUpdatedResult.next()) {
            villainId = villainUpdatedResult.getInt(1);
        }
        return villainId;
    }

    private void addVillain(String villainName) throws SQLException {
        String evilnessFactor = "evil";
        String insertVillainQuery = "INSERT INTO villains(name, evilness_factor) VALUES(?, 'evil')";
        PreparedStatement insertVillainPrepStatement = connection.prepareStatement(insertVillainQuery);
        insertVillainPrepStatement.setString(1, villainName);
        insertVillainPrepStatement.executeUpdate();
        System.out.printf("Villain %s was added to the database.\n", villainName);
    }

    private int getTownId(String town) throws SQLException {
        String getTownIdQuery = "SELECT t.id FROM towns AS t WHERE t.name = ?";
        PreparedStatement getTownIdPrepStatement = this.connection.prepareStatement(getTownIdQuery);
        getTownIdPrepStatement.setString(1, town);

        ResultSet getTownIdResult = getTownIdPrepStatement.executeQuery();

        if(!getTownIdResult.next()){
            this.addTown(town);
        }

        ResultSet getTownIdUpdatedResult = getTownIdPrepStatement.executeQuery();

        int townId = 0;
        while (getTownIdUpdatedResult.next()) {
            townId = getTownIdUpdatedResult.getInt(1);
        }
        return townId;
    }

    private void addTown(String town) throws SQLException {
        String addTownQuery = "INSERT INTO towns(name) VALUES(?);";
        PreparedStatement addTownPreparedStatement = this.connection.prepareStatement(addTownQuery);
        addTownPreparedStatement.setString(1, town);
        addTownPreparedStatement.executeUpdate();
        System.out.printf("Town %s was added to the database.\n", town);
        addTownPreparedStatement.close();
    }

    private boolean updateMappingTable(int minionId, int villainId) throws SQLException {
        String checkIfRowExistsQuery = "SELECT * FROM minions_villains AS mv WHERE mv.minion_id = ? AND mv.villain_id = ?";
        PreparedStatement checkIfRowExistsPrepStatement = this.connection.prepareStatement(checkIfRowExistsQuery);
        checkIfRowExistsPrepStatement.setInt(1, minionId);
        checkIfRowExistsPrepStatement.setInt(2, villainId);

        ResultSet checkIfRowExistsResult = checkIfRowExistsPrepStatement.executeQuery();

        if (!checkIfRowExistsResult.next()) {
            String updateMappingTableQuery = "INSERT INTO minions_villains(minion_id, villain_id) VALUES(?, ?)";
            PreparedStatement updateMappingTablePrepStatement = this.connection.prepareStatement(updateMappingTableQuery);
            updateMappingTablePrepStatement.setInt(1, minionId);
            updateMappingTablePrepStatement.setInt(2, villainId);

            updateMappingTablePrepStatement.executeUpdate();
            return true;
        }

        System.out.printf("Entry with minionId=%d and villainId=%d already exists in minions_villains table!\n", minionId, villainId);

        return false;
    }
}
