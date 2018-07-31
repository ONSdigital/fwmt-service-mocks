package uk.gov.ons.fwmt.tm_mock.controller;

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
import uk.gov.ons.fwmt.tm_mock.dto.JobDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resources/jobs")
public class JobController {

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<JobDTO>> getJobs() {
    JobDTO job = new JobDTO("1234","1234",LocalDateTime.now());
    final List<JobDTO> result = new ArrayList<>();
    result.add(job);
    return ResponseEntity.ok(result);
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
    final JobDTO result = new JobDTO(jobId,"1234",LocalDateTime.now());
    return ResponseEntity.ok(result);
  }
}
