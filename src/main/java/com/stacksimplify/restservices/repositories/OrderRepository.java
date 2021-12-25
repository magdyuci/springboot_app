package com.stacksimplify.restservices.repositories;

import com.stacksimplify.restservices.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
