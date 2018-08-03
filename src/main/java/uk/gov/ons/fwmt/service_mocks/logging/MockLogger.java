package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

@Component
public class MockLogger {
  public ThreadLocal<MockMessage> currentMessage = new ThreadLocal<>();

  public List<MockMessage> messages = new Vector<>();

  public void setupCurrentMessage() {
    if (currentMessage.get() == null) {
      MockMessage message = new MockMessage();
      currentMessage.set(message);
      messages.add(message);
    }
  }

  public void tearDownCurrentMessage() {
    currentMessage.remove();
  }

  public void logEndpoint(String endpoint) {
    currentMessage.get().endpoint = endpoint;
  }

  public void logRawRequest(String headers, String rawHtml) {
    MockMessage message = currentMessage.get();
    message.requestTimestamp = LocalDateTime.now();
    message.requestRawHeaders = headers;
    message.requestRawHtml = rawHtml;
  }

  public void logParsedRequest(Object o) {
    currentMessage.get().requestMessageParsed = o;
  }

  public void logRawResponse(String headers, String rawHtml) {
    MockMessage message = currentMessage.get();
    message.responseTimestamp = LocalDateTime.now();
    message.responseRawHeaders = headers;
    message.responseRawHtml = rawHtml;
  }

  public void logParsedResponse(Object o) {
    currentMessage.get().responseMessageParsed = o;
  }

  public void logRawFault(String headers, String rawHtml) {
    MockMessage message = currentMessage.get();
    message.responseTimestamp = LocalDateTime.now();
    message.responseRawHeaders = headers;
    message.responseRawHtml = rawHtml;
    message.faulted = true;
  }
}
