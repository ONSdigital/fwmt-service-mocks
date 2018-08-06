package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockLogger {
  public ThreadLocal<MockMessage> currentMessage = new ThreadLocal<>();

  public List<MockMessage> messages = new ArrayList<>();

  public void logWsMessage(MockMessage message) {
    messages.add(message);
  }

  public void logEndpoint(String endpoint) {
    currentMessage.get().endpoint = endpoint;
  }

  public <T> void logRequest(T request) {
    currentMessage.get().requestMessageParsed = request;
  }

  public <T> void logResponse(T response) {
    currentMessage.get().responseMessageParsed = response;
  }
}
