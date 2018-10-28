package p05_Border_Control;

public class Citizen extends BaseCitizen{
    private String name;
    private Integer age;
//    private Integer id;

    public Citizen(String name, Integer age, String id) {
        super(id);
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    private void setAge(Integer age) {
        this.age = age;
    }

//    public Integer getId() {
//        return this.id;
//    }
//
//    private void setId(Integer id) {
//        this.id = id;
//    }
}
