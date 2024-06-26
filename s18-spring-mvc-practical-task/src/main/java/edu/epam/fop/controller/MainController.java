package edu.epam.fop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String sayHello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("message", "Hello World! " + name    );
        return "login";
    }
}
