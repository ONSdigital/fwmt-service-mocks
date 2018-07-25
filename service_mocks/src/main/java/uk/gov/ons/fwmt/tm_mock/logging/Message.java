package uk.gov.ons.fwmt.tm_mock.logging;

import java.time.LocalDateTime;

public class Message {
  LocalDateTime timestamp;

  public String rawHtml;

  public Message(LocalDateTime timestamp, String rawHtml) {

  }
}
