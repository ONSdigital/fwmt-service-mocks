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
  @Autowired private WsLogger wsLogger;

  private void setupCurrentMessage() {
    if (wsLogger.currentMessage.get() == null) {
      WsMessage message = new WsMessage();
      wsLogger.currentMessage.set(message);
      wsLogger.logWsMessage(message);
    }
  }

  private void tearDownCurrentMessage() {
    wsLogger.currentMessage.remove();
  }

  @Override public boolean understands(SoapHeaderElement header) {
    return true;
  }

  @Override public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getRequest().writeTo(outputStream);
    wsLogger.currentMessage.get().requestRawHtml = outputStream.toString();
    wsLogger.currentMessage.get().requestTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    wsLogger.currentMessage.get().responseRawHtml = outputStream.toString();
    wsLogger.currentMessage.get().responseTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
    setupCurrentMessage();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    wsLogger.currentMessage.get().faultRawHtml = outputStream.toString();
    wsLogger.currentMessage.get().faultTimestamp = LocalDateTime.now();
    return true;
  }

  @Override public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
    tearDownCurrentMessage();
  }
}
