package p05_Border_Control;

public class Robot extends BaseCitizen{
    private String model;

    public Robot(String model, String id) {
        super(id);
        this.setModel(model);
    }

    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        this.model = model;
    }

//    public Integer getId() {
//        return this.id;
//    }
//
//    private void setId(Integer id) {
//        this.id = id;
//    }
}
