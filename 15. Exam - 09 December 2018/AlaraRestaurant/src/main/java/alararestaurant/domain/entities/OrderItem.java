package alararestaurant.domain.entities;


import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity{
    private Integer quantity;
    private Order order;
    private Item item;


    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id" , nullable = false)
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
