package uk.gov.ons.fwmt.tm_mock.ws;

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
  @Autowired
  WsLogger wsLogger;

  // This endpoint doesn't work because of how Spring reacts to the message, instead of the SOAPAction
  @PayloadRoot(namespace = "http://schemas.consiliumtechnologies.com/services/mobile/2007/07/messaging", localPart = "SendAdapterOutput")
  @ResponsePayload
  public JAXBElement<Void> sendAdapterOutput(@RequestPayload JAXBElement<WebServiceAdapterOutputRequest> request) {
    wsLogger.logEndpoint("SendMessage");
    wsLogger.logRequest(request.getValue());
    JAXBElement<Void> response = new JAXBElement<>(new QName(null, "null"), Void.class, null);
    wsLogger.logResponse(null);
    return response;
  }

  @PayloadRoot(namespace = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging", localPart = "request")
  @ResponsePayload
  public JAXBElement<Void> request(@RequestPayload JAXBElement<WebServiceAdapterOutputRequest> request) {
    return sendAdapterOutput(request);
  }
}