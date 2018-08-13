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
import uk.gov.ons.fwmt.service_mocks.resource_service.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resources/users")
public class UserController {

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<UserDTO>> getUsers() {
    UserDTO user = new UserDTO("1234","John.Smith",false,"");
    List<UserDTO> result = new ArrayList<>();
    result.add(user);
    return ResponseEntity.ok(result);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(userDTO);
  }

  @DeleteMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<UserDTO> deleteUser(@RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping(value = "/auth/{authNo}", produces = "application/json")
  public ResponseEntity<UserDTO> getUserByAuthNo(@PathVariable("authNo") String authNo) {
    UserDTO result = new UserDTO(authNo,"John.Smith",true,"");
    return ResponseEntity.ok(result);
  }

  @GetMapping(value = "/alternative/{altAuthNo}", produces = "application/json")
  public ResponseEntity<UserDTO> getUserByAltAuthNo(@PathVariable("altAuthNo") String altAuthNo){
    UserDTO result = new UserDTO("1234","John.Smith",true, altAuthNo);
    return ResponseEntity.ok(result);
  }

}
