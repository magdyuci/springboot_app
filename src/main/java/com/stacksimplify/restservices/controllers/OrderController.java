package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    //get all orders for a user
    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent())
            throw new UserNotFoundException("User not found");

        return userOptional.get().getOrders();
    }

    //create order
    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent())
            throw new UserNotFoundException("User not found");

        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);

    }

    @GetMapping("/{userid}/orders/{orderid}")
    public Optional<Order> getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException, NotFoundException {

        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        Optional<Order> orderOptional = orderRepository.findById(orderid);

        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Order not found");
        }
        if (orderOptional.get().getUser().getId().equals(userid)) {

            return orderOptional;
        }
        return Optional.empty();
    }
}

