package p05_Border_Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<BaseCitizen> baseCitizens = new ArrayList<>();

        while (true){
            String[]   token = reader.readLine().split("\\s+");

            if("End".equals(token[0])){
                break;
            }

            if(token.length == 3){
                String name = token[0];
                Integer age = Integer.parseInt(token[1]);
                String id = token[2];
                Citizen citizen = new Citizen(name,age,id);
                baseCitizens.add(citizen);
            }

            if(token.length == 2){
                String model = token[0];
                String id = token[1];
                Robot robot = new Robot(model,id);
                baseCitizens.add(robot);
            }
        }

        String fakeIdDigits = reader.readLine();

        for (BaseCitizen baseCitizen : baseCitizens) {
            if(baseCitizen.getId().endsWith(fakeIdDigits)){
                System.out.println(baseCitizen.getId());
            }
        }
    }
}
