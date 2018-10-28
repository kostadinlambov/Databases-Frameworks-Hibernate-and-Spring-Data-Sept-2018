package p07_Mankind;

public abstract class Human {
    private String firstName;
    private String lastName;

    protected Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if(!Character.isUpperCase(firstName.charAt(0))){
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        }

        if(firstName.length() < 4){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {

        if(!Character.isUpperCase(lastName.charAt(0))){
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        }

        if(lastName.length() < 3){
            throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
        }

        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        sb.append(String.format("First Name: %s", this.getFirstName())).append(System.lineSeparator())
                .append(String.format("Last Name: %s", this.getLastName())).append(System.lineSeparator());

        return sb.toString();
    }
}
