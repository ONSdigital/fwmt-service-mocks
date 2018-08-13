package uk.gov.ons.fwmt.service_mocks.tm.logging;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MockMessage {

  public String endpoint;

  // request
  public LocalDateTime requestTimestamp;
  public String requestRawHeaders;
  public String requestRawHtml;
  public Object requestMessageParsed;

  // response
  public LocalDateTime responseTimestamp;
  public String responseRawHeaders;
  public String responseRawHtml;
  public Object responseMessageParsed;
  public boolean isFault;

}
