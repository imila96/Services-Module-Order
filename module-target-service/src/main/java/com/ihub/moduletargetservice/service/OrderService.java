package com.ihub.moduletargetservice.service;


import com.ihub.moduletargetservice.entity.Order;
import com.ihub.moduletargetservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {

        orderRepository.save(order);
    }

}