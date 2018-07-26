package uk.gov.ons.fwmt.tm_mock.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.SoapEndpointInterceptor;
import uk.gov.ons.fwmt.tm_mock.logging.WsLogger;
import uk.gov.ons.fwmt.tm_mock.logging.WsMessage;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Component
public class RawXmlInterceptor implements SoapEndpointInterceptor {
  public static ThreadLocal<WsMessage> currentMessage = new ThreadLocal<>();

  @Autowired private WsLogger wsLogger;

  private void setupCurrentMessage() {
    if (currentMessage.get() == null) {
      WsMessage message = new WsMessage();
      currentMessage.set(message);
      wsLogger.logWsMessage(message);
    }
  }

  private void tearDownCurrentMessage() {
    currentMessage.remove();
  }

  @Override public boolean understands(SoapHeaderElement header) {
    return true;
  }

  @Override public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getRequest().writeTo(outputStream);
    currentMessage.get().requestRawHtml = outputStream.toString();
    currentMessage.get().requestTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    currentMessage.get().responseRawHtml = outputStream.toString();
    currentMessage.get().responseTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    currentMessage.get().faultRawHtml = outputStream.toString();
    currentMessage.get().faultTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
    tearDownCurrentMessage();
  }
}
