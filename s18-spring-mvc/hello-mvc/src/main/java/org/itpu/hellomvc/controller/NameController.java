package org.itpu.hellomvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/name/")
public class NameController {

  @GetMapping
  public String getName(@ModelAttribute String name) {
    return name;
  }
}
