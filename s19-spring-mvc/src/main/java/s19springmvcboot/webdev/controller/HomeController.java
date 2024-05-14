package s19springmvcboot.webdev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  Logger log = LoggerFactory.getLogger(HomeController.class);

  @GetMapping("/home")
  public String goHome() {
    log.warn("Showing index.jsp");
    return "index";
  }

  @GetMapping("/favicon.ico")
  public String getFavicon() {
    return "F";
  }
}
