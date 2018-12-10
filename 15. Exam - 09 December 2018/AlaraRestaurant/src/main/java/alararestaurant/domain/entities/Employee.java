package alararestaurant.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends  BaseEntity{
    private String name;
    private Integer age;
    private Position position;
    private List<Order> orders;

    public Employee() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return this.age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @OneToMany(targetEntity = Order.class, mappedBy = "employee")
    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
