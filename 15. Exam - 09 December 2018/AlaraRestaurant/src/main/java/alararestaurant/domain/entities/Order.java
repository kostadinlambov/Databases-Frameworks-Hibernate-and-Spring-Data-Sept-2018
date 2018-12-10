package alararestaurant.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private String customer;
    private LocalDateTime dateTime;
    private OrderType type = OrderType.ForHere;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
    }

    @Column(name = "customer", nullable = false, columnDefinition = "TEXT")
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('ForHere','ToGo') default 'ForHere'", nullable = false)
    public OrderType getType() {
        return this.type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", referencedColumnName = "id",nullable = false)
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
