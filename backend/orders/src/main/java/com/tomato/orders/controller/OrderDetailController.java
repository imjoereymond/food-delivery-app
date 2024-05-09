package com.tomato.orders.controller;

import com.tomato.orders.modal.OrderDetails;
import com.tomato.orders.repository.OrderDetailsRepository;
import com.tomato.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailController.class);

    private OrderService orderService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @PostMapping("new")
    public ResponseEntity<OrderDetails> saveOrder(@RequestBody OrderDetails orderDetails) {
        OrderDetails savedOrder = orderService.saveOrder(orderDetails);
        LOGGER.info("Order Stored Successfully");
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);

    }

    @GetMapping("getData/{email}")
    public ResponseEntity<OrderDetails> getData(@PathVariable(value = "email") String email) {
        OrderDetails getData = orderDetailsRepository.findByEmail(email).get();
        LOGGER.info("Order Retrieved Successfully");
        return new ResponseEntity<>(getData, HttpStatus.OK);
    }
}
