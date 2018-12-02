package kl.gamestore.services;

import kl.gamestore.domain.dtos.order.OrderAddItemDto;
import kl.gamestore.domain.dtos.order.OrderRemoveItemDto;
import kl.gamestore.domain.entities.Order;

import java.util.List;

public interface OrderService {
    String addItem(OrderAddItemDto title, String usersEmail);

    String removeItem(OrderRemoveItemDto orderRemoveItemDto, String usersEmail);

    List<Order> findAllByUserIdAndGameId(Long userId, Long gameId);
    List<Order> findAllOrdersByUserId(Long userId);

    String buyItems(String loggedInUser);
}
