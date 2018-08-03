package uk.gov.ons.fwmt.service_mocks.tm.logging;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WsLogger {
  public ThreadLocal<WsMessage> currentMessage = new ThreadLocal<>();

  public List<WsMessage> messages = new ArrayList<>();

  public void logWsMessage(WsMessage message) {
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
