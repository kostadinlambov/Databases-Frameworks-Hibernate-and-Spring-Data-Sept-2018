package app;

import app.Problems.*;

import java.sql.Connection;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }


    public void run() {
        // Problem 2. Get Villains’ Names
        P02_Get_Villains_Names p02 = new P02_Get_Villains_Names(this.connection);
        p02.run();

        // Problem 3. Get Minion Names
        // P03_Get_Minion_Names p03 = new P03_Get_Minion_Names(this.connection);
        //p03.run();

        // Problem 4. Add Minion
        // P04_Add_Minion p04 = new P04_Add_Minion(this.connection);
        //p04.run();

        // Problem 5. Change Town Names Casing
        // P05_Change_Town_Names_Casing p05 = new P05_Change_Town_Names_Casing(this.connection);
        // p05.run();

        // Problem 6. Remove Villain
        // P06_Remove_Villain p06 = new P06_Remove_Villain(this.connection);
        // p06.run();

        // Problem 7. Print All Minion Names
        // P07_Print_All_Minion_Names p07 = new P07_Print_All_Minion_Names(this.connection);
        // p07.run();

        // Problem 8. Increase Minions Age
        // P08_Increase_Minions_Age p08 = new P08_Increase_Minions_Age(this.connection);
        // p08.run();

        // Problem 8. Increase Minions Age
        // P09_Increase_Age_Stored_Procedure p09 = new P09_Increase_Age_Stored_Procedure(this.connection);
        // p09.run();
    }
}
