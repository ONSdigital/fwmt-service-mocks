package uk.gov.ons.fwmt.tm_mock.logging;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WsLogger {
  private List<WsMessage> messages = new ArrayList<>();

  public void logWsMessage(WsMessage message) {
    messages.add(message);
  }
}
