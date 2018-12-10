package alararestaurant.domain.dtos.orderImportDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportRootDto {

    @XmlElement(name = "order")
    private List<OrderImportDto> orderImportDtos;


    public OrderImportRootDto() {
    }

    public List<OrderImportDto> getOrderImportDtos() {
        return this.orderImportDtos;
    }

    public void setOrderImportDtos(List<OrderImportDto> orderImportDtos) {
        this.orderImportDtos = orderImportDtos;
    }
}
