package uk.gov.ons.fwmt.service_mocks.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ons.fwmt.service_mocks.logging.WsLogger;
import uk.gov.ons.fwmt.service_mocks.logging.WsMessage;

import java.util.List;

@RestController
@RequestMapping("monitor")
public class MonitoringRest {
  WsLogger wsLogger;

  @Autowired
  public MonitoringRest(WsLogger wsLogger) {
    this.wsLogger = wsLogger;
  }

  @GetMapping(value = "allMessages", produces = "application/json")
  public List<WsMessage> getAllMessages() {
    return wsLogger.messages;
  }

  @GetMapping(value = "faultCount", produces = "application/json")
  public int getFaultCount() {
    return (int) (wsLogger.messages.stream().filter(m -> m.faultTimestamp != null).count());
  }
}
