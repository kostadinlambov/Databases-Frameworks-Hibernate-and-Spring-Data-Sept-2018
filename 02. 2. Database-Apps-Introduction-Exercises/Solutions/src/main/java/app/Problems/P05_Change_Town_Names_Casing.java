package app.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class P05_Change_Town_Names_Casing implements Runnable{
    private Connection connection;

    public P05_Change_Town_Names_Casing(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String countryName = reader.readLine();
            changeTownNames(countryName);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeTownNames(String countryName) throws SQLException {
        List<String> townsList = new ArrayList<>();

        String updateTownNameQuery = "UPDATE towns\n" +
                "SET towns.name =  upper(towns.name)\n" +
                "WHERE towns.country = ?";
        PreparedStatement updateNamePrepSt = this.connection.prepareStatement(updateTownNameQuery);
        updateNamePrepSt.setString(1, countryName);

        int countUpdatedTowns = updateNamePrepSt.executeUpdate();

        if(countUpdatedTowns > 0){

            String getTownsQuery = "SELECT * FROM towns AS t WHERE t.country = ?";
            PreparedStatement getTownsPrepSt = this.connection.prepareStatement(getTownsQuery);
            getTownsPrepSt.setString(1, countryName);

            ResultSet getTownsResult = getTownsPrepSt.executeQuery();

            while(getTownsResult.next()){
                String townName = getTownsResult.getString(2);
                townsList.add(townName);
            }
        }

        if(townsList.size() > 0){
            System.out.printf("%d town names were affected. \n" +
                    "%s\n",townsList.size(), townsList.toString() );
        }else{
            System.out.println("No town names were affected.");
        }
    }
}
