package com.ibm.iqp.poc.stockservice.service;




import com.ibm.iqp.poc.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

private static Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);


@KafkaListener(topics= "${spring.kafka.topic.name}",
        groupId= "${spring.kafka.consumer.group-id}")
public void consume(OrderEvent orderEvent){

    LOGGER.info(String.format("Order event received on stock service => %s",orderEvent.toString()));

}
}
