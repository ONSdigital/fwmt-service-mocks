package uk.gov.ons.fwmt.service_mocks.resource_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldPeriodDTO {
  LocalDate startDate;
  LocalDate endDate;
  String fieldPeriod;
}
