package uk.gov.ons.fwmt.service_mocks.tm.endpoint;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.WebServiceAdapterOutputRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uk.gov.ons.fwmt.service_mocks.logging.MockLogger;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Slf4j
@Endpoint
public class GenericOutgoingWs {
  @Autowired
  MockLogger mockLogger;

  // Ideally, the method signature would be as below
  // Spring does not appear to respect SOAPAction
  // Therefore, a alternative signature is used
  // TODO fix the signature of the GenericOutgoingWs:sendAdapterOutput method
  //  @PayloadRoot(namespace = "http://schemas.consiliumtechnologies.com/services/mobile/2007/07/messaging", localPart = "SendAdapterOutput")
  //  @ResponsePayload
  //  public JAXBElement<Void> sendAdapterOutput(@RequestPayload JAXBElement<WebServiceAdapterOutputRequest> request) {
  @PayloadRoot(namespace = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging", localPart = "request")
  @ResponsePayload
  public JAXBElement<Void> sendAdapterOutput(@RequestPayload JAXBElement<WebServiceAdapterOutputRequest> request) {
    mockLogger.logEndpoint("SendMessage");
    mockLogger.logParsedRequest(request.getValue());
    JAXBElement<Void> response = new JAXBElement<>(new QName(null, "null"), Void.class, null);
    mockLogger.logParsedResponse(response);
    return response;
  }
}