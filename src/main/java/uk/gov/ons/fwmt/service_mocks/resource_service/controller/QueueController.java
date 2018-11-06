package uk.gov.ons.fwmt.service_mocks.resource_service.controller;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

@RestController
@RequestMapping("/queue")
public class QueueController {

  @GetMapping(value = "/message/{qname}", produces = "application/json")
  public ResponseEntity<String> getMessageOffQueue(@PathVariable("qname") String qname) {
    Connection connection = null;
    Channel channel = null;
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      connection = factory.newConnection();
      channel = connection.createChannel();

      GetResponse response = channel.basicGet(qname, true);
      if (response == null) {
        return ResponseEntity.notFound().build();
      } else {
        byte[] body = response.getBody();
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
  public ResponseEntity<Long  > getMessageCount(@PathVariable("qname") String qname) {
    Connection connection = null;
    Channel channel = null;
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      connection = factory.newConnection();
      channel = connection.createChannel();

      long messageCount = channel.messageCount(qname);
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
}
