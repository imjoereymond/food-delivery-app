package com.tomato.orders.repository;

import com.tomato.orders.modal.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    Optional<OrderDetails> findByEmail(String email);
}
