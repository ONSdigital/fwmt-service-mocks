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
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/resources/users")
public class UserController {

  private List<UserDTO> validUsers = new ArrayList<>();

  public UserController() {
    validUsers.add(new UserDTO(
        "1234", "user_1", true, null
    ));
    validUsers.add(new UserDTO(
        "1111", "user_2", true, null
    ));
    validUsers.add(new UserDTO(
        "9999", "user_3", true, null
    ));
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<UserDTO>> getUsers() {
    return ResponseEntity.ok(validUsers);
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
    Optional<UserDTO> match = validUsers.stream().filter(dto -> dto.getAuthNo().equalsIgnoreCase(authNo)).findFirst();
    if (match.isPresent()) {
      return ResponseEntity.ok(match.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(value = "/alternative/{altAuthNo}", produces = "application/json")
  public ResponseEntity<UserDTO> getUserByAltAuthNo(@PathVariable("altAuthNo") String altAuthNo) {
    Optional<UserDTO> match = validUsers.stream().filter(dto -> dto.getAlternateAuthNo().equalsIgnoreCase(altAuthNo))
        .findFirst();
    if (match.isPresent()) {
      return ResponseEntity.ok(match.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
