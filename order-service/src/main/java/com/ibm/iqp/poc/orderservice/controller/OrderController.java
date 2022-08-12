package com.ibm.iqp.poc.orderservice.controller;


import com.ibm.iqp.poc.basedomains.dto.Order;
import com.ibm.iqp.poc.basedomains.dto.OrderEvent;
import com.ibm.iqp.poc.orderservice.kafka.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vi")
public class OrderController {
    private static  final Logger LOGGER= LoggerFactory.getLogger(OrderController.class);

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer){
        this.orderProducer=orderProducer;
    }
@PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){

    LOGGER.info("******************************************** OrderController.placeOrder called ******************************************");

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent= new OrderEvent();
        orderEvent.setStatus("PLACED");
        orderEvent.setMessage("Order is in PLACED status");

        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order place successfully.....";


    }


}
