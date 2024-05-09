package com.tomato.orders.controller;

import com.tomato.orders.modal.Order;
import com.tomato.orders.modal.OrderDetails;
import com.tomato.orders.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderDetailControllerTest {

    @Autowired
    private OrderService orderService;

    private OrderDetails orderDetails;

    private List<Order> order;
    private Order order1;

    @BeforeEach
    public void setup() {
        orderDetails = new OrderDetails();
        order = new ArrayList<>();
        order1 = new Order();

        order1.setId(1);
        order1.setFood_id(201);
        order1.setFood_name("Panneer Pizza");
        order1.setQuantity(5);
        order.add(order1);

        orderDetails.setId(1);
        orderDetails.setFirstName("Joe");
        orderDetails.setLastName("Raymond");
        orderDetails.setEmail("joeraymond@gmail.com");
        orderDetails.setStreet("ABC Street");
        orderDetails.setCity("Dindigul");
        orderDetails.setState("TN");
        orderDetails.setCountry("India");
        orderDetails.setZipcode("624005");
        orderDetails.setPhone("9629582956");
        orderDetails.setOrders(order);
    }

    @AfterEach
    public void tearDown() {
        orderDetails = null;
        order1 = null;
        order = null;
    }

    @Test
    public void givenOrderDetailsToSaveThenShouldReturnTheSavedOrderDetails() {
        OrderDetails savedOrder = orderService.saveOrder(orderDetails);
        assertNotNull(savedOrder);
        assertEquals(orderDetails.getEmail(), savedOrder.getEmail());
    }

}
