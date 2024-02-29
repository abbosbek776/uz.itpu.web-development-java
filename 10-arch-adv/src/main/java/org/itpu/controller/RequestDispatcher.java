package org.itpu.controller;

import java.util.HashMap;
import java.util.Map;
import org.itpu.command.Command;
import org.itpu.command.CourseCommand;
import org.itpu.command.StudentCommand;

public class RequestDispatcher {

  private Map<String, Command> commandMap;

  public RequestDispatcher() {
    commandMap = new HashMap<>();
    initializeCommands();
  }

  private void initializeCommands() {
    commandMap.put("course", new CourseCommand());
    commandMap.put("student", new StudentCommand());
  }

  public Command getCommand(String commandType) {
    return commandMap.get(commandType);
  }
}
