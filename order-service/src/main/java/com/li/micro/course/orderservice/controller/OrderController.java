package com.li.micro.course.orderservice.controller;

import com.li.micro.course.orderservice.dto.OrderRequest;
import com.li.micro.course.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 22.11.2023
 * Time: 21:28
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallback")
    @TimeLimiter(name = "inventory", fallbackMethod = "placeOrderFallback")
    @Retry(name = "inventory", fallbackMethod = "placeOrderFallback")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> placeOrderFallback(OrderRequest orderRequest, Exception e) {
        return CompletableFuture.supplyAsync(() -> "Order placement failed, please try again later");
    }
}
