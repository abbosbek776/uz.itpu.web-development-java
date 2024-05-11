package com.s27springrest.controller;

import com.s27springrest.dto.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
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

    @GetMapping(path = "/cmn", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public TestResponse getTestResponseAsCommon(@RequestHeader(HttpHeaders.ACCEPT) String accept) {
        return new TestResponse(accept, "TEST COMMON TXT");
    }

    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public TestResponse getTestResponseAsJson(@RequestHeader(HttpHeaders.ACCEPT) String accept) {
        return new TestResponse(accept, "TEST JSON TXT");
    }

    @GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public TestResponse getTestResponseAsXml(@RequestHeader(HttpHeaders.ACCEPT) String accept) {
        return new TestResponse(accept, "TEST XML TXT");
    }
}
