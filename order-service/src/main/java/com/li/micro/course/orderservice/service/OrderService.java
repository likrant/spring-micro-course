package com.li.micro.course.orderservice.service;

import com.li.micro.course.orderservice.dto.OrderLineItemsDto;
import com.li.micro.course.orderservice.dto.OrderRequest;
import com.li.micro.course.orderservice.model.Order;
import com.li.micro.course.orderservice.model.OrderLineItems;
import com.li.micro.course.orderservice.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 22.11.2023
 * Time: 21:28
 */
@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToOrderLineItems).toList());
        orderRepo.save(order);
    }

    OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        return orderLineItems;
    }
}
