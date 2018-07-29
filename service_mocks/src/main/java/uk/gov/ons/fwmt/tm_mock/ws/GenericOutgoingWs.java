package uk.gov.ons.fwmt.tm_mock.ws;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.WebServiceAdapterOutputRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uk.gov.ons.fwmt.tm_mock.logging.WsLogger;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Slf4j
@Endpoint
public class GenericOutgoingWs {
  private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2007/07/messaging";

  @Autowired
  WsLogger wsLogger;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAdapterOutput")
  @ResponsePayload
  public void sendAdapterOutput(@RequestPayload JAXBElement<WebServiceAdapterOutputRequest> request) {
    wsLogger.logEndpoint("SendMessage");
    wsLogger.logRequest(request.getValue());
    wsLogger.logResponse(null);
  }
}