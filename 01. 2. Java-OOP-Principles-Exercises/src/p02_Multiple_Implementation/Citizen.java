package p02_Multiple_Implementation;

public class Citizen implements Person, Birthable, Identifiable {
    private String name;
    private int age;
    private String birtdate;
    private String id;


    public Citizen(String name, int age,  String id, String birtdate) {
        this.name = name;
        this.age = age;
        this.birtdate = birtdate;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthdate() {
        return this.birtdate;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
