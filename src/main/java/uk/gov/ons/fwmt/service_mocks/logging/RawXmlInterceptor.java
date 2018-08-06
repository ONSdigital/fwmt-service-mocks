package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.SoapEndpointInterceptor;
import uk.gov.ons.fwmt.service_mocks.logging.MockLogger;
import uk.gov.ons.fwmt.service_mocks.logging.MockMessage;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Component
public class RawXmlInterceptor implements SoapEndpointInterceptor {
  @Autowired private MockLogger mockLogger;

  private void setupCurrentMessage() {
    if (mockLogger.currentMessage.get() == null) {
      MockMessage message = new MockMessage();
      mockLogger.currentMessage.set(message);
      mockLogger.logWsMessage(message);
    }
  }

  private void tearDownCurrentMessage() {
    mockLogger.currentMessage.remove();
  }

  @Override public boolean understands(SoapHeaderElement header) {
    return true;
  }

  @Override public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getRequest().writeTo(outputStream);
    mockLogger.currentMessage.get().requestRawHtml = outputStream.toString();
    mockLogger.currentMessage.get().requestTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    mockLogger.currentMessage.get().responseRawHtml = outputStream.toString();
    mockLogger.currentMessage.get().responseTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    mockLogger.currentMessage.get().faultRawHtml = outputStream.toString();
    mockLogger.currentMessage.get().faultTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
    tearDownCurrentMessage();
  }
}
