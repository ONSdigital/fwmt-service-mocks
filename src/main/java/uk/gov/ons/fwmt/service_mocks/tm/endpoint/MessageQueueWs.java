package uk.gov.ons.fwmt.service_mocks.tm.endpoint;

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
import uk.gov.ons.fwmt.service_mocks.logging.MockLogger;

import javax.xml.bind.JAXBElement;

@Slf4j
@Endpoint
public class MessageQueueWs {
  private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging";

  private ObjectFactory objectFactory = new ObjectFactory();

  @Autowired
  private MockLogger mockLogger;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendMessageRequest")
  @ResponsePayload
  public JAXBElement<SendMessageResponse> sendMessage(@RequestPayload JAXBElement<SendMessageRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendMessage");
    mockLogger.logParsedRequest(request.getValue());

    SendMessageResponse response = new SendMessageResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createSendMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "TransformAndSendRequest")
  @ResponsePayload
  public JAXBElement<TransformAndSendResponse> transformAndSendMessage(
      @RequestPayload JAXBElement<TransformAndSendRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "transformAndSendMessage");
    mockLogger.logParsedRequest(request.getValue());

    TransformAndSendResponse response = new TransformAndSendResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createTransformAndSendResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "QueryMessagesRequest")
  @ResponsePayload
  public JAXBElement<QueryMessagesResponse> query(@RequestPayload JAXBElement<QueryMessagesRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "query");
    mockLogger.logParsedRequest(request.getValue());

    QueryMessagesResponse response = new QueryMessagesResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createQueryMessagesResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMessageRequest")
  @ResponsePayload
  public JAXBElement<GetMessageResponse> get(@RequestPayload JAXBElement<GetMessageRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "get");
    mockLogger.logParsedRequest(request.getValue());

    GetMessageResponse response = new GetMessageResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createGetMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteMessageRequest")
  @ResponsePayload
  public JAXBElement<DeleteMessageResponse> delete(@RequestPayload JAXBElement<DeleteMessageRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "delete");
    mockLogger.logParsedRequest(request.getValue());

    DeleteMessageResponse response = new DeleteMessageResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createDeleteMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RetryMessageRequest")
  @ResponsePayload
  public JAXBElement<RetryMessageResponse> retry(@RequestPayload JAXBElement<RetryMessageRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "retry");
    mockLogger.logParsedRequest(request.getValue());

    RetryMessageResponse response = new RetryMessageResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createRetryMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ResetMessageRequest")
  @ResponsePayload
  public JAXBElement<ResetMessageResponse> reset(@RequestPayload JAXBElement<ResetMessageRequest> request) {
    mockLogger.logEndpoint("MessageQueueWs", "reset");
    mockLogger.logParsedRequest(request.getValue());

    ResetMessageResponse response = new ResetMessageResponse();

    mockLogger.logParsedResponse(response);
    return objectFactory.createResetMessageResponse(response);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateVisitRequestMessage")
  @ResponsePayload
  public SendCreateVisitRequestMessageResponse sendCreateVisitRequestMessage(
      @RequestPayload SendCreateVisitRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendCreateVisitRequestMessage");
    mockLogger.logParsedRequest(request);

    SendCreateVisitRequestMessageResponse response = new SendCreateVisitRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendForceRecallVisitRequestMessage")
  @ResponsePayload
  public SendForceRecallVisitRequestMessageResponse sendForceRecallVisitRequestMessage(
      @RequestPayload SendForceRecallVisitRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendForceRecallVisitRequestMessage");
    mockLogger.logParsedRequest(request);

    SendForceRecallVisitRequestMessageResponse response = new SendForceRecallVisitRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAddVisitTasksRequestMessage")
  @ResponsePayload
  public SendAddVisitTasksRequestMessageResponse sendAddVisitTasksRequestMessage(
      @RequestPayload SendAddVisitTasksRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendAddVisitTasksRequestMessage");
    mockLogger.logParsedRequest(request);

    SendAddVisitTasksRequestMessageResponse response = new SendAddVisitTasksRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateVisitScheduleRequestMessage")
  @ResponsePayload
  public SendUpdateVisitScheduleRequestMessageResponse sendUpdateVisitScheduleRequestMessage(
      @RequestPayload SendUpdateVisitScheduleRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendUpdateVisitScheduleRequestMessage");
    mockLogger.logParsedRequest(request);

    SendUpdateVisitScheduleRequestMessageResponse response = new SendUpdateVisitScheduleRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateVisitHeaderRequestMessage")
  @ResponsePayload
  public SendUpdateVisitHeaderRequestMessageResponse sendUpdateVisitHeaderRequestMessage(
      @RequestPayload SendUpdateVisitHeaderRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendUpdateVisitHeaderRequestMessage");
    mockLogger.logParsedRequest(request);

    SendUpdateVisitHeaderRequestMessageResponse response = new SendUpdateVisitHeaderRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateBulletinRequestMessage")
  @ResponsePayload
  public SendCreateBulletinRequestMessageResponse sendCreateBulletinRequestMessage(
      @RequestPayload SendCreateBulletinRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendCreateBulletinRequestMessage");
    mockLogger.logParsedRequest(request);

    SendCreateBulletinRequestMessageResponse response = new SendCreateBulletinRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendDeleteBulletinRequestMessage")
  @ResponsePayload
  public SendDeleteBulletinRequestMessageResponse sendDeleteBulletinRequestMessage(
      @RequestPayload SendDeleteBulletinRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendDeleteBulletinRequestMessage");
    mockLogger.logParsedRequest(request);

    SendDeleteBulletinRequestMessageResponse response = new SendDeleteBulletinRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendGenerateFolioContentRequestMessage")
  @ResponsePayload
  public SendGenerateFolioContentRequestMessageResponse sendGenerateFolioContentRequestMessage(
      @RequestPayload SendGenerateFolioContentRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendGenerateFolioContentRequestMessage");
    mockLogger.logParsedRequest(request);

    SendGenerateFolioContentRequestMessageResponse response = new SendGenerateFolioContentRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAddFolioContentRequestMessage")
  @ResponsePayload
  public SendAddFolioContentRequestMessageResponse sendAddFolioContentRequestMessage(
      @RequestPayload SendAddFolioContentRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendAddFolioContentRequestMessage");
    mockLogger.logParsedRequest(request);

    SendAddFolioContentRequestMessageResponse response = new SendAddFolioContentRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateReferralRequestMessage")
  @ResponsePayload
  public SendCreateReferralRequestMessageResponse sendCreateReferralRequestMessage(
      @RequestPayload SendCreateReferralRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendCreateReferralRequestMessage");
    mockLogger.logParsedRequest(request);

    SendCreateReferralRequestMessageResponse response = new SendCreateReferralRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreatePatientRequestMessage")
  @ResponsePayload
  public SendCreatePatientRequestMessageResponse sendCreatePatientRequestMessage(
      @RequestPayload SendCreatePatientRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendCreatePatientRequestMessage");
    mockLogger.logParsedRequest(request);

    SendCreatePatientRequestMessageResponse response = new SendCreatePatientRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateReferralRequestMessage")
  @ResponsePayload
  public SendUpdateReferralRequestMessageResponse sendUpdateReferralRequestMessage(
      @RequestPayload SendUpdateReferralRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendUpdateReferralRequestMessage");
    mockLogger.logParsedRequest(request);

    SendUpdateReferralRequestMessageResponse response = new SendUpdateReferralRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateAppointmentRequestMessage")
  @ResponsePayload
  public SendCreateAppointmentRequestMessageResponse sendCreateAppointmentRequestMessage(
      @RequestPayload SendCreateAppointmentRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendCreateAppointmentRequestMessage");
    mockLogger.logParsedRequest(request);

    SendCreateAppointmentRequestMessageResponse response = new SendCreateAppointmentRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendDischargeReferralRequestMessage")
  @ResponsePayload
  public SendDischargeReferralRequestMessageResponse sendDischargeReferralRequestMessage(
      @RequestPayload SendDischargeReferralRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendDischargeReferralRequestMessage");
    mockLogger.logParsedRequest(request);

    SendDischargeReferralRequestMessageResponse response = new SendDischargeReferralRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendCreateJobRequestMessage")
  @ResponsePayload
  public SendCreateJobRequestMessageResponse sendCreateJobRequestMessage(
      @RequestPayload SendCreateJobRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendCreateJobRequestMessage");
    mockLogger.logParsedRequest(request);

    SendCreateJobRequestMessageResponse response = new SendCreateJobRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendDeleteJobRequestMessage")
  @ResponsePayload
  public SendDeleteJobRequestMessageResponse sendDeleteJobRequestMessage(
      @RequestPayload SendDeleteJobRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendDeleteJobRequestMessage");
    mockLogger.logParsedRequest(request);

    SendDeleteJobRequestMessageResponse response = new SendDeleteJobRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAddJobTasksRequestMessage")
  @ResponsePayload
  public SendAddJobTasksRequestMessageResponse sendAddJobTasksRequestMessage(
      @RequestPayload SendAddJobTasksRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendAddJobTasksRequestMessage");
    mockLogger.logParsedRequest(request);

    SendAddJobTasksRequestMessageResponse response = new SendAddJobTasksRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendSaveAvailabilityRequestMessage")
  @ResponsePayload
  public SendSaveAvailabilityRequestMessageResponse sendSaveAvailabilityRequestMessage(
      @RequestPayload SendSaveAvailabilityRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendSaveAvailabilityRequestMessage");
    mockLogger.logParsedRequest(request);

    SendSaveAvailabilityRequestMessageResponse response = new SendSaveAvailabilityRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateJobHeaderRequestMessage")
  @ResponsePayload
  public SendUpdateJobHeaderRequestMessageResponse sendUpdateJobHeaderRequestMessage(
      @RequestPayload SendUpdateJobHeaderRequestMessage request) {
    mockLogger.logEndpoint("MessageQueueWs", "sendUpdateJobHeaderRequestMessage");
    mockLogger.logParsedRequest(request);

    SendUpdateJobHeaderRequestMessageResponse response = new SendUpdateJobHeaderRequestMessageResponse();

    mockLogger.logParsedResponse(response);
    return response;
  }
}
