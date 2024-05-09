package com.tomato.orders.service;

import com.tomato.orders.modal.Order;
import com.tomato.orders.modal.OrderDetails;
import com.tomato.orders.repository.OrderDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

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
        Optional<OrderDetails> orderDetails1 = orderDetailsRepository.findByEmail(orderDetails.getEmail());
        OrderDetails savedOrder = null;

        if(orderDetails1.isPresent()) {
            orderDetailsRepository.delete(orderDetails1.get());
            savedOrder = orderDetailsRepository.save(orderDetails);
        }
        else {
            savedOrder = orderDetailsRepository.save(orderDetails);
        }
        assertEquals(orderDetails.getFirstName(), savedOrder.getFirstName());
    }
}
