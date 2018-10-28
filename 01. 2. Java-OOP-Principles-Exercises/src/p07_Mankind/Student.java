package p07_Mankind;

public class Student extends Human {
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    public String getFacultyNumber() {
        return this.facultyNumber;
    }

    private void setFacultyNumber(String facultyNumber) {
        if(facultyNumber.length() < 5 || facultyNumber.length() > 10){
            throw new IllegalArgumentException("Invalid faculty number!");
        }

        this.facultyNumber = facultyNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());

        sb.append(String.format("Faculty number: %s", this.getFacultyNumber())).append(System.lineSeparator());

        return sb.toString();
    }
}
