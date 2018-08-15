package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

@Component
public class MockLogger {
  private ThreadLocal<MockMessage> currentMessage = new ThreadLocal<>();

  private List<MockMessage> messages = new Vector<>();

  // methods for mock logging

  private void setupCurrentMessage() {
    if (currentMessage.get() == null) {
      MockMessage message = new MockMessage();
      currentMessage.set(message);
      messages.add(message);
    }
  }

  public void logEndpoint(String endpoint, String method) {
    setupCurrentMessage();
    currentMessage.get().endpoint = endpoint;
    currentMessage.get().method = method;
  }

  public void logRawRequest(String rawHeaders, String rawHtml) {
    setupCurrentMessage();
    currentMessage.get().requestTimestamp = LocalDateTime.now();
    currentMessage.get().requestRawHeaders = rawHeaders;
    currentMessage.get().requestRawHtml = rawHtml;
  }

  public <R> void logParsedRequest(R request) {
    setupCurrentMessage();
    currentMessage.get().requestMessageParsed = request;
  }

  public void logRawResponse(String rawHeaders, String rawHtml) {
    setupCurrentMessage();
    currentMessage.get().responseTimestamp = LocalDateTime.now();
    currentMessage.get().responseRawHeaders = rawHeaders;
    currentMessage.get().responseRawHtml = rawHtml;
    currentMessage.get().isFault = false;
  }

  public <R> void logParsedResponse(R response) {
    setupCurrentMessage();
    currentMessage.get().responseMessageParsed = response;
  }

  public void logRawFault(String rawHeaders, String rawHtml) {
    setupCurrentMessage();
    currentMessage.get().responseTimestamp = LocalDateTime.now();
    currentMessage.get().responseRawHeaders = rawHeaders;
    currentMessage.get().responseRawHtml = rawHtml;
    currentMessage.get().isFault = true;
  }

  public void finalise() {
    currentMessage.remove();
  }

  // methods for retrieving

  public List<MockMessage> getAllMessages() {
    return Collections.unmodifiableList(messages);
  }

  public int getFaultCount() {
    return (int) (messages.stream().filter(MockMessage::isFault).count());
  }

  public void reset() {
    messages.clear();
  }
}
