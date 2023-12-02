package com.li.micro.course.orderservice.service;

import com.li.micro.course.orderservice.dto.InventoryResponse;
import com.li.micro.course.orderservice.dto.OrderLineItemsDto;
import com.li.micro.course.orderservice.dto.OrderRequest;
import com.li.micro.course.orderservice.model.Order;
import com.li.micro.course.orderservice.model.OrderLineItems;
import com.li.micro.course.orderservice.repository.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 22.11.2023
 * Time: 21:28
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepo orderRepo;
    private final WebClient.Builder webClientBuilder;

    public OrderService(OrderRepo orderRepo, WebClient.Builder webClientBuilder) {
        this.orderRepo = orderRepo;
        this.webClientBuilder = webClientBuilder;
    }

    public String placeOrder(OrderRequest orderRequest) {
        List<String> skuCodes = orderRequest.getOrderLineItemsDtoList().stream().map(OrderLineItemsDto::getSkuCode).toList();
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("sku-code", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allInStock = inventoryResponseArray == null || Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if (Boolean.FALSE.equals(allInStock)) {
            throw new IllegalArgumentException("Products are not in stock");
        }

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToOrderLineItems).toList());
        orderRepo.save(order);
        return "Order placed successfully";
    }

    OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        return orderLineItems;
    }
}
