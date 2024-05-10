package com.s27springrest.controller;

import com.s27springrest.dto.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping
    public String run() {
        StringBuffer sb = new StringBuffer("loadDatabase = " + applicationContext.containsBean("LoadDatabase"));
        sb.append("<br/>");
        sb.append("initDatabase = " + applicationContext.containsBean("initDatabase"));
        sb.append("<br/>");
        sb.append("springFoxConfig = " + applicationContext.containsBean("SpringFoxConfig"));
        sb.append("<br/>");
        sb.append("api = " + applicationContext.containsBean("api"));
        sb.append("<br/>");
        return sb.toString();
    }

    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public TestResponse getTestResponseAsJson() {
        return new TestResponse("TEST JSON TXT");
    }

    @GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<TestResponse> getTestResponseAsXml() {
//        return new TestResponse("TEST TXT");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(new TestResponse("TEST XML TXT"));
    }
}
