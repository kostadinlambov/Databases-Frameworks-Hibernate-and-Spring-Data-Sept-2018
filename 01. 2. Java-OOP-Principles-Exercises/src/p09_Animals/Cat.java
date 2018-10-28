package p09_Animals;

public class Cat extends AnimalImpl {
    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }


    @Override
    public void produceSound() {
        System.out.println("MiauMiau");
    }


}
