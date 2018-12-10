package alararestaurant.service;

import alararestaurant.domain.dtos.orderImportDtos.ItemDto;
import alararestaurant.domain.dtos.orderImportDtos.OrderImportDto;
import alararestaurant.domain.dtos.orderImportDtos.OrderImportRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static alararestaurant.common.Constants.*;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDERS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\orders.xml";

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(ORDERS_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();
        OrderImportRootDto orderImportRootDto = this.xmlParser.parseXml(OrderImportRootDto.class, ORDERS_FILE_PATH);

        for (OrderImportDto orderImportDto : orderImportRootDto.getOrderImportDtos()) {
            if (!this.validationUtil.isValid(orderImportDto)) {
                importResult.append(INVALID_DATA_FORMAT).append(System.lineSeparator());
                continue;
            }

            Employee employee = this.employeeRepository.findByName(orderImportDto.getEmployee()).orElse(null);

            if(employee == null){
                importResult.append(INVALID_DATA_FORMAT).append(System.lineSeparator());
                continue;
            }

            List<ItemDto> itemDtos = orderImportDto.getItemRootDto().getItemDtos();

            List<OrderItem> orderItems = new ArrayList<>();
            boolean hasInvalidItem = false;

            for ( ItemDto itemDto: orderImportDto.getItemRootDto().getItemDtos()) {
                Item item = this.itemRepository.findByName(itemDto.getName()).orElse(null);
                if(item == null){
                    importResult.append(INVALID_DATA_FORMAT).append(System.lineSeparator());
                    hasInvalidItem = true;
                    break;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItems.add(orderItem);
            }

            if(hasInvalidItem){
                continue;
            }

            LocalDateTime dateTime = LocalDateTime.parse(orderImportDto.getDateTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            Order order = this.modelMapper.map(orderImportDto, Order.class);
            order.setDateTime(dateTime);
            order.setOrderItems(orderItems);
            order.setEmployee(employee);

            Order savedOrder = this.orderRepository.saveAndFlush(order);

            if(savedOrder != null){

                orderItems.forEach(orderItem -> {
                    orderItem.setOrder(savedOrder);
                    OrderItem savedOrderItem = this.orderItemRepository.saveAndFlush(orderItem);
                });

                savedOrder.setOrderItems(orderItems);

                importResult
                        .append(String.format(SUCCESSFUL_ORDER_IMPORT_MESSAGE, savedOrder.getCustomer(), savedOrder.getDateTime().toString()))
                        .append(System.lineSeparator());
            }
            System.out.println();
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder result = new StringBuilder();

        List<Order> orders = this.orderRepository.ordersByBurgerFlippers();

        orders.forEach(order -> {
            result.append(String.format("Name: %s", order.getEmployee().getName())).append(System.lineSeparator());
            result.append("Orders: ").append(System.lineSeparator());

            result.append(String.format("\tCustomer: %s", order.getCustomer())).append(System.lineSeparator());
            result.append("\tItems: ").append(System.lineSeparator());

            order.getOrderItems().forEach(orderItem -> {
                result.append(String.format("\t\tName: %s", orderItem.getItem().getName())).append(System.lineSeparator());
                result.append(String.format("\t\tPrice: %s", orderItem.getItem().getPrice())).append(System.lineSeparator());
                result.append(String.format("\t\tQuantity: %s", orderItem.getQuantity())).append(System.lineSeparator());
                result.append(System.lineSeparator());
            });
        });



        return result.toString().trim();

    }
}
