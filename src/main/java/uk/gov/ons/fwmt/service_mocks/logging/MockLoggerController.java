package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("monitor")
public class MockLoggerController {
  MockLogger mockLogger;

  @Autowired
  public MockLoggerController(MockLogger mockLogger) {
    this.mockLogger = mockLogger;
  }

  @GetMapping(value = "allMessages", produces = "application/json")
  public List<MockMessage> getAllMessages() {
    return mockLogger.messages;
  }

  @GetMapping(value = "allMessagesAfterTimestamp", produces = "application/json")
  public List<MockMessage> getAllMessagesAfterTimestamp(LocalDateTime time) {
    return mockLogger.messages.stream()
        .filter(m -> time.isBefore(m.getRequestTimestamp()) || time.isBefore(m.getResponseTimestamp()))
        .collect(Collectors.toList());
  }

  @GetMapping(value = "faultCount", produces = "application/json")
  public int getFaultCount() {
    return (int) (mockLogger.messages.stream().filter(m -> m.faulted).count());
  }

}
