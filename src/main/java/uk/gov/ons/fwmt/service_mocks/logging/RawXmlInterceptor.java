package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.SoapEndpointInterceptor;

import java.io.ByteArrayOutputStream;

@Component
public class RawXmlInterceptor implements SoapEndpointInterceptor {
  @Autowired private MockLogger mockLogger;

  @Override public boolean understands(SoapHeaderElement header) {
    return true;
  }

  @Override public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getRequest().writeTo(outputStream);
    mockLogger.logRawRequest(null, outputStream.toString());
    return true;
  }

  @Override public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    mockLogger.logRawResponse(null, outputStream.toString());
    return true;
  }

  @Override public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    messageContext.getResponse().writeTo(outputStream);
    mockLogger.logRawFault(null, outputStream.toString());
    return true;
  }

  @Override public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
    mockLogger.finalise();
  }
}
