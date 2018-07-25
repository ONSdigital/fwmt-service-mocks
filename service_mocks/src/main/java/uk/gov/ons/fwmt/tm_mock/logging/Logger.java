package uk.gov.ons.fwmt.tm_mock.logging;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Logger {
  public List<Message> messages = new ArrayList<>();
}
