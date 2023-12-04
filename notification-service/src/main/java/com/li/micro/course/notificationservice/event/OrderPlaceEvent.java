package com.li.micro.course.notificationservice.event;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 03.12.2023
 * Time: 19:39
 */
public class OrderPlaceEvent {
    private String orderNumber;

    public OrderPlaceEvent() {
    }

    public OrderPlaceEvent(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
