package p09_Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String animalType = reader.readLine();
            if ("Beast!".equalsIgnoreCase(animalType)) {
                break;
            }

            String[] token = reader.readLine().split("\\s+");

            try {
                String name = token[0];
                int age = Integer.parseInt(token[1]);
                String gender = null;

                if (token.length == 3) {
                    gender = token[2];
                }

                switch (animalType) {
                    case "Dog":
                        SoundProducible dog = new Dog(name, age, gender);
                        System.out.println(dog);
                        dog.produceSound();
                        break;
                    case "Cat":
                        SoundProducible cat = new Cat(name, age, gender);
                        System.out.println(cat);
                        cat.produceSound();
                        break;
                    case "Frog":
                        SoundProducible frog = new Frog(name, age, gender);
                        System.out.println(frog);
                        frog.produceSound();
                        break;
                    case "Kitten":
                        SoundProducible kitten = new Kitten(name, age);
                        System.out.println(kitten);
                        kitten.produceSound();
                        break;
                    case "Tomcat":
                        SoundProducible tomcat = new Tomcat(name, age);
                        System.out.println(tomcat);
                        tomcat.produceSound();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid input!");
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }
}
