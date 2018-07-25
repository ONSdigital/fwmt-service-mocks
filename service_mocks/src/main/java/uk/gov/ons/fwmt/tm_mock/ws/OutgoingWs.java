package uk.gov.ons.fwmt.tm_mock.ws;

import com.consiliumtechnologies.schemas.mobile._2008._10.locationmessages.SubmitLocationRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.commonmessages.SubmitDocumentRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.CompleteVisitRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.RequestVisitRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitStatusRequest;
import com.consiliumtechnologies.schemas.mobile._2009._07.formsmessages.SubmitFormResultRequest;
import com.consiliumtechnologies.schemas.mobile._2009._09.compositemessages.CompositeVisitRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uk.gov.ons.fwmt.tm_mock.logging.Logger;

import javax.xml.bind.JAXBElement;

@Slf4j
@Endpoint
public class OutgoingWs {
  // mirrored in @Value("outgoing-ws-namespace")
  private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging";

  @Autowired
  Logger messageLogger;

  private void report(String messageType) {
    log.debug("Found message of type {}", messageType);
    //    messageLogger.messages.add(request);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateVisitStatusRequest")
  @ResponsePayload
  public JAXBElement<UpdateVisitStatusRequest> sendUpdateVisitStatusRequestOutput(
      HttpEntity<JAXBElement<UpdateVisitStatusRequest>> request) {
    report("SendUpdateVisitStatusRequestOutput");
    JAXBElement<UpdateVisitStatusRequest> jaxb = request.getBody();
    jaxb.setValue(null);
    return jaxb;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "completeVisitRequest")
  @ResponsePayload
  // TODO find a way to do this without returning the input
  // http://forum.spring.io/forum/spring-projects/web-services/42740-responding-with-an-empty-soap-body
  public JAXBElement<CompleteVisitRequest> sendCompleteVisitStatusRequestOutputLowercase(
      @RequestPayload JAXBElement<CompleteVisitRequest> request) {
    report("SendCompleteVisitStatusRequestOutput");
    request.setValue(null);
    return request;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "requestVisitRequest")
  @ResponsePayload
  public JAXBElement<RequestVisitRequest> sendRequestVisitRequestOutput(
      @RequestPayload JAXBElement<RequestVisitRequest> request) {
    report("SendRequestVisitRequestOutput");
    request.setValue(null);
    return request;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitDocumentRequest")
  @ResponsePayload
  public JAXBElement<SubmitDocumentRequest> sendSubmitDocumentRequestOutput(
      @RequestPayload JAXBElement<SubmitDocumentRequest> request) {
    report("SendSubmitDocumentRequestOutput");
    request.setValue(null);
    return request;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "compositeVisitRequest")
  @ResponsePayload
  public JAXBElement<CompositeVisitRequest> sendCompositeVisitRequestOutput(
      @RequestPayload JAXBElement<CompositeVisitRequest> request) {
    report("SendCompositeVisitRequestOutput");
    request.setValue(null);
    return request;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitFormResultRequest")
  @ResponsePayload
  public JAXBElement<SubmitFormResultRequest> sendSubmitFormResultRequestOutput(
      @RequestPayload JAXBElement<SubmitFormResultRequest> request) {
    report("SendSubmitFormResultRequestOutput");
    request.setValue(null);
    return request;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitLocationRequest")
  @ResponsePayload
  public JAXBElement<SubmitLocationRequest> sendSubmitLocationRequestOutput(
      @RequestPayload JAXBElement<SubmitLocationRequest> request) {
    report("SendSubmitLocationRequestOutput");
    request.setValue(null);
    return request;
  }
}
