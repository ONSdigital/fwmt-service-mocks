package uk.gov.ons.fwmt.service_mocks.tm.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.DeleteMessageRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.DeleteMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.GetMessageRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.GetMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.QueryMessagesRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.QueryMessagesResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ResetMessageRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ResetMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.RetryMessageRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.RetryMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendAddFolioContentRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendAddFolioContentRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendAddJobTasksRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendAddJobTasksRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendAddVisitTasksRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendAddVisitTasksRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateAppointmentRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateAppointmentRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateBulletinRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateBulletinRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreatePatientRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreatePatientRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateReferralRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateReferralRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateVisitRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateVisitRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendDeleteBulletinRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendDeleteBulletinRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendDeleteJobRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendDeleteJobRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendDischargeReferralRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendDischargeReferralRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendForceRecallVisitRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendForceRecallVisitRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendGenerateFolioContentRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendGenerateFolioContentRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendSaveAvailabilityRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendSaveAvailabilityRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateJobHeaderRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateJobHeaderRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateReferralRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateReferralRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitScheduleRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitScheduleRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.TransformAndSendRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.TransformAndSendResponse;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uk.gov.ons.fwmt.service_mocks.tm.logging.WsLogger;

import javax.xml.bind.JAXBElement;

@Slf4j
@Endpoint
public class MessageQueueWs {
  private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2007/07/messaging";

  private ObjectFactory objectFactory = new ObjectFactory();

  @Autowired
  private WsLogger wsLogger;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendMessageRequest")
  @ResponsePayload
  public JAXBElement<SendMessageResponse> sendMessage(@RequestPayload JAXBElement<SendMessageRequest> request) {
    wsLogger.logEndpoint("SendMessage");
    wsLogger.logRequest(request.getValue());

    SendMessageResponse response = new SendMessageResponse();

    wsLogger.logResponse(response);
    return objectFactory.createSendMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "TransformAndSendRequest")
  @ResponsePayload
  public JAXBElement<TransformAndSendResponse> transformAndSendMessage(
      @RequestPayload JAXBElement<TransformAndSendRequest> request) {
    wsLogger.logEndpoint("TransformAndSendMessage");
    wsLogger.logRequest(request.getValue());

    TransformAndSendResponse response = new TransformAndSendResponse();

    wsLogger.logResponse(response);
    return objectFactory.createTransformAndSendResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "QueryMessagesRequest")
  @ResponsePayload
  public JAXBElement<QueryMessagesResponse> query(@RequestPayload JAXBElement<QueryMessagesRequest> request) {
    wsLogger.logEndpoint("Query");
    wsLogger.logRequest(request.getValue());

    QueryMessagesResponse response = new QueryMessagesResponse();

    wsLogger.logResponse(response);
    return objectFactory.createQueryMessagesResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMessageRequest")
  @ResponsePayload
  public JAXBElement<GetMessageResponse> get(@RequestPayload JAXBElement<GetMessageRequest> request) {
    wsLogger.logEndpoint("Get");
    wsLogger.logRequest(request.getValue());

    GetMessageResponse response = new GetMessageResponse();

    wsLogger.logResponse(response);
    return objectFactory.createGetMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteMessageRequest")
  @ResponsePayload
  public JAXBElement<DeleteMessageResponse> delete(@RequestPayload JAXBElement<DeleteMessageRequest> request) {
    wsLogger.logEndpoint("Delete");
    wsLogger.logRequest(request.getValue());

    DeleteMessageResponse response = new DeleteMessageResponse();

    wsLogger.logResponse(response);
    return objectFactory.createDeleteMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RetryMessageRequest")
  @ResponsePayload
  public JAXBElement<RetryMessageResponse> retry(@RequestPayload JAXBElement<RetryMessageRequest> request) {
    wsLogger.logEndpoint("Retry");
    wsLogger.logRequest(request.getValue());

    RetryMessageResponse response = new RetryMessageResponse();

    wsLogger.logResponse(response);
    return objectFactory.createRetryMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ResetMessageRequest")
  @ResponsePayload
  public JAXBElement<ResetMessageResponse> reset(@RequestPayload JAXBElement<ResetMessageRequest> request) {
    wsLogger.logEndpoint("Reset");
    wsLogger.logRequest(request.getValue());

    ResetMessageResponse response = new ResetMessageResponse();

    wsLogger.logResponse(response);
    return objectFactory.createResetMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateVisitRequestMessage")
  @ResponsePayload
  public SendCreateVisitRequestMessageResponse sendCreateVisitRequestMessage(
      @RequestPayload SendCreateVisitRequestMessage request) {
    wsLogger.logEndpoint("SendCreateVisitRequestMessage");
    wsLogger.logRequest(request);

    SendCreateVisitRequestMessageResponse response = new SendCreateVisitRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendForceRecallVisitRequestMessage")
  @ResponsePayload
  public SendForceRecallVisitRequestMessageResponse sendForceRecallVisitRequestMessage(
      @RequestPayload SendForceRecallVisitRequestMessage request) {
    wsLogger.logEndpoint("SendForceRecallVisitRequestMessage");
    wsLogger.logRequest(request);

    SendForceRecallVisitRequestMessageResponse response = new SendForceRecallVisitRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAddVisitTasksRequestMessage")
  @ResponsePayload
  public SendAddVisitTasksRequestMessageResponse sendAddVisitTasksRequestMessage(
      @RequestPayload SendAddVisitTasksRequestMessage request) {
    wsLogger.logEndpoint("SendAddVisitTasksRequestMessage");
    wsLogger.logRequest(request);

    SendAddVisitTasksRequestMessageResponse response = new SendAddVisitTasksRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateVisitScheduleRequestMessage")
  @ResponsePayload
  public SendUpdateVisitScheduleRequestMessageResponse sendUpdateVisitScheduleRequestMessage(
      @RequestPayload SendUpdateVisitScheduleRequestMessage request) {
    wsLogger.logEndpoint("SendUpdateVisitScheduleRequestMessage");
    wsLogger.logRequest(request);

    SendUpdateVisitScheduleRequestMessageResponse response = new SendUpdateVisitScheduleRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateVisitHeaderRequestMessage")
  @ResponsePayload
  public SendUpdateVisitHeaderRequestMessageResponse sendUpdateVisitHeaderRequestMessage(
      @RequestPayload SendUpdateVisitHeaderRequestMessage request) {
    wsLogger.logEndpoint("SendUpdateVisitHeaderRequestMessage");
    wsLogger.logRequest(request);

    SendUpdateVisitHeaderRequestMessageResponse response = new SendUpdateVisitHeaderRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateBulletinRequestMessage")
  @ResponsePayload
  public SendCreateBulletinRequestMessageResponse sendCreateBulletinRequestMessage(
      @RequestPayload SendCreateBulletinRequestMessage request) {
    wsLogger.logEndpoint("SendCreateBulletinRequestMessage");
    wsLogger.logRequest(request);

    SendCreateBulletinRequestMessageResponse response = new SendCreateBulletinRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendDeleteBulletinRequestMessage")
  @ResponsePayload
  public SendDeleteBulletinRequestMessageResponse sendDeleteBulletinRequestMessage(
      @RequestPayload SendDeleteBulletinRequestMessage request) {
    wsLogger.logEndpoint("SendDeleteBulletinRequestMessage");
    wsLogger.logRequest(request);

    SendDeleteBulletinRequestMessageResponse response = new SendDeleteBulletinRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendGenerateFolioContentRequestMessage")
  @ResponsePayload
  public SendGenerateFolioContentRequestMessageResponse sendGenerateFolioContentRequestMessage(
      @RequestPayload SendGenerateFolioContentRequestMessage request) {
    wsLogger.logEndpoint("SendGenerateFolioContentRequestMessage");
    wsLogger.logRequest(request);

    SendGenerateFolioContentRequestMessageResponse response = new SendGenerateFolioContentRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAddFolioContentRequestMessage")
  @ResponsePayload
  public SendAddFolioContentRequestMessageResponse sendAddFolioContentRequestMessage(
      @RequestPayload SendAddFolioContentRequestMessage request) {
    wsLogger.logEndpoint("SendAddFolioContentRequestMessage");
    wsLogger.logRequest(request);

    SendAddFolioContentRequestMessageResponse response = new SendAddFolioContentRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateReferralRequestMessage")
  @ResponsePayload
  public SendCreateReferralRequestMessageResponse sendCreateReferralRequestMessage(
      @RequestPayload SendCreateReferralRequestMessage request) {
    wsLogger.logEndpoint("SendCreateReferralRequestMessage");
    wsLogger.logRequest(request);

    SendCreateReferralRequestMessageResponse response = new SendCreateReferralRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreatePatientRequestMessage")
  @ResponsePayload
  public SendCreatePatientRequestMessageResponse sendCreatePatientRequestMessage(
      @RequestPayload SendCreatePatientRequestMessage request) {
    wsLogger.logEndpoint("SendCreatePatientRequestMessage");
    wsLogger.logRequest(request);

    SendCreatePatientRequestMessageResponse response = new SendCreatePatientRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateReferralRequestMessage")
  @ResponsePayload
  public SendUpdateReferralRequestMessageResponse sendUpdateReferralRequestMessage(
      @RequestPayload SendUpdateReferralRequestMessage request) {
    wsLogger.logEndpoint("SendUpdateReferralRequestMessage");
    wsLogger.logRequest(request);

    SendUpdateReferralRequestMessageResponse response = new SendUpdateReferralRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateAppointmentRequestMessage")
  @ResponsePayload
  public SendCreateAppointmentRequestMessageResponse sendCreateAppointmentRequestMessage(
      @RequestPayload SendCreateAppointmentRequestMessage request) {
    wsLogger.logEndpoint("SendCreateAppointmentRequestMessage");
    wsLogger.logRequest(request);

    SendCreateAppointmentRequestMessageResponse response = new SendCreateAppointmentRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendDischargeReferralRequestMessage")
  @ResponsePayload
  public SendDischargeReferralRequestMessageResponse sendDischargeReferralRequestMessage(
      @RequestPayload SendDischargeReferralRequestMessage request) {
    wsLogger.logEndpoint("SendDischargeReferralRequestMessage");
    wsLogger.logRequest(request);

    SendDischargeReferralRequestMessageResponse response = new SendDischargeReferralRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateJobRequestMessage")
  @ResponsePayload
  public SendCreateJobRequestMessageResponse sendCreateJobRequestMessage(
      @RequestPayload SendCreateJobRequestMessage request) {
    wsLogger.logEndpoint("SendCreateJobRequestMessage");
    wsLogger.logRequest(request);

    SendCreateJobRequestMessageResponse response = new SendCreateJobRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendDeleteJobRequestMessage")
  @ResponsePayload
  public SendDeleteJobRequestMessageResponse sendDeleteJobRequestMessage(
      @RequestPayload SendDeleteJobRequestMessage request) {
    wsLogger.logEndpoint("SendDeleteJobRequestMessage");
    wsLogger.logRequest(request);

    SendDeleteJobRequestMessageResponse response = new SendDeleteJobRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAddJobTasksRequestMessage")
  @ResponsePayload
  public SendAddJobTasksRequestMessageResponse sendAddJobTasksRequestMessage(
      @RequestPayload SendAddJobTasksRequestMessage request) {
    wsLogger.logEndpoint("SendAddJobTasksRequestMessage");
    wsLogger.logRequest(request);

    SendAddJobTasksRequestMessageResponse response = new SendAddJobTasksRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendSaveAvailabilityRequestMessage")
  @ResponsePayload
  public SendSaveAvailabilityRequestMessageResponse sendSaveAvailabilityRequestMessage(
      @RequestPayload SendSaveAvailabilityRequestMessage request) {
    wsLogger.logEndpoint("SendSaveAvailabilityRequestMessage");
    wsLogger.logRequest(request);

    SendSaveAvailabilityRequestMessageResponse response = new SendSaveAvailabilityRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateJobHeaderRequestMessage")
  @ResponsePayload
  public SendUpdateJobHeaderRequestMessageResponse sendUpdateJobHeaderRequestMessage(
      @RequestPayload SendUpdateJobHeaderRequestMessage request) {
    wsLogger.logEndpoint("SendUpdateJobHeaderRequestMessage");
    wsLogger.logRequest(request);

    SendUpdateJobHeaderRequestMessageResponse response = new SendUpdateJobHeaderRequestMessageResponse();

    wsLogger.logResponse(response);
    return response;
  }
}
