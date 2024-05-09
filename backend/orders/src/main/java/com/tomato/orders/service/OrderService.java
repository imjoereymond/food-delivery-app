package com.tomato.orders.service;

import com.tomato.orders.modal.OrderDetails;

public interface OrderService {

    OrderDetails saveOrder(OrderDetails orderDetails);
}
