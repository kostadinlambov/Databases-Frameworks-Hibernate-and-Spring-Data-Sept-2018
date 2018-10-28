package p05_Border_Control;

public abstract class BaseCitizen {
    private String id;

    protected BaseCitizen(String id) {
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }
}
