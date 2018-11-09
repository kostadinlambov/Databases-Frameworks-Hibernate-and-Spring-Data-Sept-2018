package app.entitties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student  extends Person {
    private Double averageGrade;
    private Integer attendance;
    private Set<Course> courses;

    public Student() {
    }

    @Column(name = "average_grade")
    public Double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")
    public Integer getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    @ManyToMany()
    @JoinTable(name = "students_courses",
    joinColumns = @JoinColumn(name = "stident_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
