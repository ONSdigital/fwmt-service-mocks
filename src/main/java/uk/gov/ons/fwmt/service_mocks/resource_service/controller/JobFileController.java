package uk.gov.ons.fwmt.service_mocks.resource_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.service_mocks.resource_service.dto.JobFileDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/resources/jobFile")
public class JobFileController {

  @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<JobFileDTO> storeJobFile(@RequestParam("file") MultipartFile file, boolean validated) {
    final JobFileDTO result = new JobFileDTO(file.getOriginalFilename(), LocalDateTime.now(), LocalDateTime.now(),
        true);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @GetMapping(value = "/{fileName}")
  public ResponseEntity<Resource> getJobFileByfileName(@PathVariable("fileName") String fileName) throws IOException {
    InputStream initialStream = new FileInputStream(new File("src/main/resources/sample_GFF_2018-07-01T19-09-54Z.csv"));
    final byte[] fileBytes = new byte[initialStream.available()];
    final ByteArrayResource resource = new ByteArrayResource(fileBytes);
    return ResponseEntity.ok().contentLength(fileBytes.length).contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
  }
}
