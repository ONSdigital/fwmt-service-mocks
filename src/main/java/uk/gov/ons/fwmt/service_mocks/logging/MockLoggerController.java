package uk.gov.ons.fwmt.service_mocks.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("logger")
public class MockLoggerController {
  MockLogger mockLogger;

  @Autowired
  public MockLoggerController(MockLogger mockLogger) {
    this.mockLogger = mockLogger;
  }

  @GetMapping(value = "allMessages", produces = "application/json")
  public List<MockMessage> getAllMessages() {
    return mockLogger.getAllMessages();
  }

  @GetMapping(value = "faultCount", produces = "application/json")
  public int getFaultCount() {
    return mockLogger.getFaultCount();
  }

  @GetMapping(value = "reset")
  public void reset() {
    mockLogger.reset();
  }

}
