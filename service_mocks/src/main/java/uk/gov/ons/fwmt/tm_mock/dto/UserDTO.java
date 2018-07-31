package uk.gov.ons.fwmt.tm_mock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  public String authNo;
  public String tmUsername;
  public boolean active;
  public String alternateAuthNo;
}
