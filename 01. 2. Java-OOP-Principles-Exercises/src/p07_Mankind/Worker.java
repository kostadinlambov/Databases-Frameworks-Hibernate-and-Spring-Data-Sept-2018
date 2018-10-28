package p07_Mankind;

public class Worker extends Human {
    private double weekSalary;
    private double hoursPerDay;

    public Worker(String firstName, String lastName, double weekSalary, double hoursPerDay) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setHoursPerDay(hoursPerDay);
    }

    public double getWeekSalary() {
        return this.weekSalary;
    }

    private void setWeekSalary(double weekSalary) {
        if (weekSalary < 10.0) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public double getHoursPerDay() {
        return this.hoursPerDay;
    }

    private void setHoursPerDay(double hoursPerDay) {
        if (hoursPerDay < 1 || hoursPerDay > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.hoursPerDay = hoursPerDay;
    }

    public double salaryPerHour() {
        return this.getWeekSalary() / (this.getHoursPerDay() * 7);
    }


    @Override
    public void setLastName(String lastName) {
        if (lastName.length() < 4) {
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }

        super.setLastName(lastName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());

        sb.append(String.format("Week Salary: %.2f", this.getWeekSalary())).append(System.lineSeparator())
                .append(String.format("Hours per day: %.2f", this.getHoursPerDay())).append(System.lineSeparator())
                .append(String.format("Salary per hour: %.2f", this.salaryPerHour()));

        return sb.toString();
    }
}


