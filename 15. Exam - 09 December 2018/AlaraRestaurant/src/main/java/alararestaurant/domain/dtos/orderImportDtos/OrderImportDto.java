package alararestaurant.domain.dtos.orderImportDtos;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportDto {

    @XmlElement(name = "customer")
    private String customer;

    @XmlElement(name = "employee")
    private String employee;

    @XmlElement(name = "date-time")
    private String dateTime;

    @XmlElement(name = "items")
    private ItemRootDto itemRootDto;


    public OrderImportDto() {
    }


    @NotNull
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @NotNull
    @Size(min = 3, max = 30)
    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @NotNull
    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public ItemRootDto getItemRootDto() {
        return this.itemRootDto;
    }

    public void setItemRootDto(ItemRootDto itemRootDto) {
        this.itemRootDto = itemRootDto;
    }
}
