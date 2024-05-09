package com.tomato.orders.service;

import com.tomato.orders.modal.OrderDetails;
import com.tomato.orders.repository.OrderDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderDetails saveOrder(OrderDetails orderDetails) {
        Optional<OrderDetails> orderDetails1 = orderDetailsRepository.findByEmail(orderDetails.getEmail());

        if(orderDetails1.isPresent()) {
            orderDetailsRepository.delete(orderDetails1.get());
            return orderDetailsRepository.save(orderDetails);
        }
        OrderDetails savedOrder = orderDetailsRepository.save(orderDetails);
        return savedOrder;
    }
}
