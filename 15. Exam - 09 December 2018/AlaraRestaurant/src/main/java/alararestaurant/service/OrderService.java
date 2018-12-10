package alararestaurant.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface OrderService {

    Boolean ordersAreImported();

    String readOrdersXmlFile() throws IOException;

    String importOrders() throws JAXBException, FileNotFoundException;

    String exportOrdersFinishedByTheBurgerFlippers();
}
