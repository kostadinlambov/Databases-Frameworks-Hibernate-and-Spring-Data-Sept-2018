package p01_Define_an_Interface_Person;

public class Citizen implements Person {
    private String name;
    private int age;

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
