package com.s27springrest.controller;

import com.s27springrest.dto.TestResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TestResponse getTestResponseAsJson() {
        return new TestResponse("TEST TXT");
    }

    @GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus()
    public ResponseEntity<TestResponse> getTestResponseAsXml() {
//        return new TestResponse("TEST TXT");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(new TestResponse("TEST TXT"));
    }
}
