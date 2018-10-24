package uk.gov.ons.fwmt.service_mocks.logging;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MockMessage {

  public String endpoint;
  public String method;

  // request
  public String requestTimestamp;
  public String requestRawHeaders;
  public String requestRawHtml;
  public Object requestMessageParsed;

  // response
  public String responseTimestamp;
  public String responseRawHeaders;
  public String responseRawHtml;
  public Object responseMessageParsed;
  public boolean isFault;

}
