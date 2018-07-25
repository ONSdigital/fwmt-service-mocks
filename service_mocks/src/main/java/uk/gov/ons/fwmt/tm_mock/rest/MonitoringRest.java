package uk.gov.ons.fwmt.tm_mock.rest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringRest {
  @RequestMapping(value = "/samples", method = RequestMethod.POST, produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 415, message = "Unsupported Media Type"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
  })
  public String test() {
    return "Test";
  }
}
