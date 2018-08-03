package uk.gov.ons.fwmt.service_mocks.resource_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ons.fwmt.service_mocks.resource_service.dto.FieldPeriodDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resources/fieldPeriods")
public class FieldPeriodController {

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<FieldPeriodDTO>> getAllFieldPeriods() {
    FieldPeriodDTO fieldPeriod = new FieldPeriodDTO(LocalDate.now(), LocalDate.now(),"test");
    final List<FieldPeriodDTO> result = new ArrayList<>();
    result.add(fieldPeriod);
    return ResponseEntity.ok(result);
  }

  @GetMapping(value = "/{fieldPeriod}", produces = "application/json")
  public ResponseEntity<FieldPeriodDTO> getFieldPeriod(@PathVariable("fieldPeriod") String fieldPeriod) {
    final FieldPeriodDTO result = new FieldPeriodDTO(LocalDate.now(), LocalDate.now(),"test");
    return ResponseEntity.ok(result);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity createFieldPeriod(@RequestBody FieldPeriodDTO fieldPeriodDTO) {
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<FieldPeriodDTO> updateFieldPeriod(@RequestBody FieldPeriodDTO fieldPeriodDTO) {
    return ResponseEntity.ok(fieldPeriodDTO);
  }

  @DeleteMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<FieldPeriodDTO> deleteFieldPeriod(@RequestBody FieldPeriodDTO fieldPeriodDTO) {
    return ResponseEntity.ok(fieldPeriodDTO);
  }

}
