package p06_Birthday_Celebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Birthable> birthableList = new ArrayList<>();

        while(true){
            String[] tokens = reader.readLine().split("\\s+");

            if("end".equalsIgnoreCase(tokens[0])){
                break;
            }

            if("Citizen".equalsIgnoreCase(tokens[0])){
                String name = tokens[1];
                int age = Integer.parseInt(tokens[2]);
                String id = tokens[3];
                String birthdate = tokens[4];

                Birthable citizen = new Citizen(name, age, id, birthdate);

                birthableList.add(citizen);
            }else if("Pet".equalsIgnoreCase(tokens[0])){
                String petsName = tokens[1];
                String petsBirthdate = tokens[2];

                Birthable pet = new Pet(petsName, petsBirthdate);
                birthableList.add(pet);
            }
        }

        String birthdate = reader.readLine();

        for (Birthable element : birthableList) {
            element.printBirthdate(birthdate);
        }
    }
}
