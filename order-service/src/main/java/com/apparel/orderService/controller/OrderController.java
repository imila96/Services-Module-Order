package com.apparel.orderService.controller;

import com.apparel.orderService.entity.Order;
import com.apparel.orderService.entity.OrderEntity;
import com.apparel.orderService.kafka.OrderProducer;
import com.apparel.orderService.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class OrderController {


    private OrderProducer orderProducer;
    private OrderRepository orderRepository;

    public OrderController(OrderProducer orderProducer, OrderRepository orderRepository) {
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
    }


    @PostMapping("/orders")
    public String placeOrder(@RequestBody OrderEntity orderEntity){

        orderEntity.setCreatedAt(LocalDateTime.now());
        orderRepository.save(orderEntity);

        Order orderEvent = new Order();
        orderEvent.setOrderId(orderEntity.getOrderId());
        orderEvent.setQty(orderEntity.getQty());

        orderProducer.sendMessage(orderEvent,"create");

        return "Order placed sucessfully";
    }
}

