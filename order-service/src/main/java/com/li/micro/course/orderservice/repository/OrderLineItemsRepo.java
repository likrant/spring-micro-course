package com.li.micro.course.orderservice.repository;

import com.li.micro.course.orderservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 22.11.2023
 * Time: 21:34
 */
@Repository
public interface OrderLineItemsRepo extends JpaRepository<OrderLineItems, Long> {
}
