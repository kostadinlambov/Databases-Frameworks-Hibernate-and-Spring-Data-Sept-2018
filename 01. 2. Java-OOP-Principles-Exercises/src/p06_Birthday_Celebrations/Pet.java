package p06_Birthday_Celebrations;

public class Pet implements Identifiable, Birthable {
    private String name;
    private String birthdate;

    public Pet(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public void checkId(String id) {

    }

    @Override
    public void printBirthdate(String year) {
        if(this.birthdate.endsWith(year))
        System.out.println(this.birthdate);
    }
}
