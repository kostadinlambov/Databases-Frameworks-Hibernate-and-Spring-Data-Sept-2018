package p06_Birthday_Celebrations;

public class Robot implements Identifiable {
    private String model;
    private String id;

    public Robot(String model, String id) {
        this.setModel(model);
        this.setId(id);
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public void checkId(String id) {
        if(this.id.endsWith(id)){
            System.out.println(this.id);
        }
    }
}
