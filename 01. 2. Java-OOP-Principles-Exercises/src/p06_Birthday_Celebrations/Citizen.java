package p06_Birthday_Celebrations;

public class Citizen implements Identifiable, Birthable {
    private String name;
    private int age;
    private String id;
    private String birthdate;

    public Citizen(String name, int age, String id, String birthdate) {
        this.birthdate = birthdate;
        this.setName(name);
        this.setAge(age);
        this.setId(id);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public void checkId(String id) {
        if (this.id.endsWith(id)) {
            System.out.println(this.id);
        }
    }

    @Override
    public void printBirthdate(String year) {
        if (this.birthdate.endsWith(year))
            System.out.println(this.birthdate);

    }
}
