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
import uk.gov.ons.fwmt.service_mocks.resource_service.dto.JobDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/resources/jobs")
public class JobController {

  private List<JobDTO> validJobs = new ArrayList<>();

  public JobController() {
    validJobs.add(new JobDTO(
        "job-001", "1234", LocalDateTime.of(2018, 1, 1, 7, 0)
    ));
    validJobs.add(new JobDTO(
        "job-002", "1111", LocalDateTime.of(2018, 1, 2, 7, 0)
    ));
    validJobs.add(new JobDTO(
        "job-003", "9999", LocalDateTime.of(2018, 1, 3, 7, 0)
    ));
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<JobDTO>> getJobs() {
    return ResponseEntity.ok(validJobs);
  }

  @PostMapping(produces = "application/json", consumes = "application/json")
  public ResponseEntity createJob(@RequestBody JobDTO jobDTO) {
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping(produces = "application/json", consumes = "application/json")
  public ResponseEntity updateJob(@RequestBody JobDTO jobDTO) {
    return ResponseEntity.ok(jobDTO);
  }

  @DeleteMapping(produces = "application/json", consumes = "application/json")
  public ResponseEntity<JobDTO> deleteJob(@RequestBody JobDTO jobDTO) {
    return ResponseEntity.ok(jobDTO);
  }

  @GetMapping(value = "/{jobId}", produces = "application/json")
  public ResponseEntity<JobDTO> getJobByJobId(@PathVariable("jobId") String jobId) {
    Optional<JobDTO> match = validJobs.stream().filter(dto -> dto.getTmJobId().equalsIgnoreCase(jobId)).findFirst();
    if (match.isPresent()) {
      return ResponseEntity.ok(match.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
