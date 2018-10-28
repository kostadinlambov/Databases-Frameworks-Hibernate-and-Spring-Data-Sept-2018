package p07_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] studentInput = reader.readLine().split("\\s+");
        String firstName = studentInput[0];
        String lastName = studentInput[1];
        String facultyNumber = studentInput[2];

        String[] workerInput = reader.readLine().split("\\s+");
        String workerFirstName = workerInput[0];
        String workerLastName = workerInput[1];
        Double weekSalary = Double.parseDouble(workerInput[2]);
        Double hoursPerDay = Double.parseDouble(workerInput[3]);

        try{
            Human student = new Student(firstName, lastName, facultyNumber);
            Human worker = new Worker(workerFirstName, workerLastName, weekSalary, hoursPerDay);
            System.out.println(student);
            System.out.println(worker);

        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }
}
