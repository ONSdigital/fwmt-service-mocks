package uk.gov.ons.fwmt.service_mocks.resource_service.controller;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/queue")
public class QueueController {

  @Value("${rabbitmq.fwmt.host}")
  private String rabbitmqFwmtHost;
  
  @Value("${rabbitmq.fwmt.username}")
  private String rabbitmqFwmtUsername;
  
  @Value("${rabbitmq.fwmt.password}")
  private String rabbitmqFwmtPassword;
  
  @Value("${rabbitmq.fwmt.port}")
  private int rabbitmqFwmtPort;
  
  @Value("${rabbitmq.fwmt.virtualHost}")
  private String rabbitmqFwmtVirtualHost;

  @Value("${rabbitmq.rm.host}")
  private String rabbitmqRmHost;
  
  @Value("${rabbitmq.rm.username}")
  private String rabbitmqRmUsername;
  
  @Value("${rabbitmq.rm.password}")
  private String rabbitmqRmPassword;
  
  @Value("${rabbitmq.rm.port}")
  private int rabbitmqRmPort;
  
  @Value("${rabbitmq.rm.virtualHost}")
  private String rabbitmqRmVirtualHost;
  
  @GetMapping(value = "/message", produces = "application/json")
  public ResponseEntity<String> getMessageOffQueue(@RequestParam("qname") String qname) {
    Connection connection = null;
    Channel channel = null;
    ConnectionFactory factory = null;
    
    try {
      if(qname.toUpperCase().contains("ACTION.FIELD")){
        log.info("Building RM RabbitMQ Factory");
      }else{
        factory = getFwmtConnectionFactory();
        log.info("Building FWMT RabbitMQ Factory");
      }
      connection = factory.newConnection();
      channel = connection.createChannel();

      GetResponse response = channel.basicGet(qname, true);
      if (response == null) {
        return ResponseEntity.notFound().build();
      } else {
        byte[] body = response.getBody();
        log.info("recieved msg from Queue: " + qname);
        return ResponseEntity.ok(new String(body));
      }
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    } finally {
      try {
        if (channel != null)
          channel.close();
        if (connection != null)
          connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @GetMapping(value = "/count/{qname}", produces = "application/json")
  public ResponseEntity<Long> getMessageCount(@PathVariable("qname") String qname) {
    Connection connection = null;
    Channel channel = null;
    ConnectionFactory factory = null;
    
    try {
      if(qname.toUpperCase().contains("ACTION.FIELD")){
        log.info("Building RM RabbitMQ Factory");
      }else{
        factory = getFwmtConnectionFactory();
        log.info("Building FWMT RabbitMQ Factory");
      }
      connection = factory.newConnection();
      channel = connection.createChannel();

      long messageCount = channel.messageCount(qname);
      log.info("recieved msg count from Queue: " + qname);
      return ResponseEntity.ok(new Long(messageCount));
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    } finally {
      try {
        if (channel != null)
          channel.close();
        if (connection != null)
          connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  private ConnectionFactory getFwmtConnectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(rabbitmqFwmtHost);
    factory.setUsername(rabbitmqFwmtUsername);
    factory.setPassword(rabbitmqFwmtPassword);
    factory.setVirtualHost(rabbitmqFwmtVirtualHost);
    return factory;
  }
  
  private ConnectionFactory getRmConnectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(rabbitmqRmHost);
    factory.setUsername(rabbitmqRmUsername);
    factory.setPassword(rabbitmqRmPassword);
    factory.setVirtualHost(rabbitmqRmVirtualHost);
    return factory;
  }
  
  @PostMapping(value = "/")
  public ResponseEntity<Boolean> addMessage(@RequestParam("exchange") String exchange, @RequestParam("routingkey") String routingkey, @RequestBody String message ){
    Connection connection = null;
    Channel channel = null;
    ConnectionFactory factory = null;
    
    try {
      if(routingkey.toUpperCase().contains("ACTION.FIELD")){
        factory = getRmConnectionFactory();
        log.info("Building RM RabbitMQ Factory");
      }else{
        factory = getFwmtConnectionFactory();
        log.info("Building FWMT RabbitMQ Factory");
      }
      
      connection = factory.newConnection();
      channel = connection.createChannel();

      BasicProperties.Builder builder = new BasicProperties.Builder();
      builder.contentType("text/xml");
      BasicProperties properties = builder.build();

      channel.basicPublish("", routingkey, properties, message.getBytes());
      log.info("Published to exchange: " + exchange);

      return ResponseEntity.ok(true);
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    } finally {
      try {
          if (channel!=null) channel.close();
          if (connection!=null) connection.close();
        } catch (IOException | TimeoutException e) {
          e.printStackTrace();
        }
    }
  }  
  
  @DeleteMapping()
  public ResponseEntity<Boolean> deleteMessage(@RequestParam("qname") String qname){
    Connection connection = null;
    Channel channel = null;
    ConnectionFactory factory = null;
    
    try {
      if(qname.toUpperCase().contains("ACTION.FIELD")){
        log.info("Building RM RabbitMQ Factory");
      }else{
        factory = getFwmtConnectionFactory();
        log.info("Building FWMT RabbitMQ Factory");
      }
      connection = factory.newConnection();
      channel = connection.createChannel();
      channel.queuePurge(qname);
      log.info("Purged Queue: " + qname);

      return ResponseEntity.ok(true);
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    } finally {
      try {
          if (channel!=null) channel.close();
          if (connection!=null) connection.close();
        } catch (IOException | TimeoutException e) {
          e.printStackTrace();
        }
    }
  }
  
}
