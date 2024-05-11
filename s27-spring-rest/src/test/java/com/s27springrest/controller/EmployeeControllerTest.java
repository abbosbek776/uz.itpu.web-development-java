package com.s27springrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.s27springrest.dto.TestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
class EmployeeControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCommon_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/test/cmn")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        TestResponse testResponse = MAPPER.readValue(contentAsString, TestResponse.class);

        assertEquals(testResponse.getAccept(), MediaType.APPLICATION_JSON_VALUE);
        assertEquals(testResponse.getTestText(), "TEST COMMON TXT");
    }

    @Test
    public void testCommon_xml() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/test/cmn")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk()).andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        TestResponse testResponse = XML_MAPPER.readValue(contentAsString, TestResponse.class);

        assertEquals(testResponse.getAccept(), MediaType.APPLICATION_XML_VALUE);
        assertEquals(testResponse.getTestText(), "TEST COMMON TXT");
    }

    @Test
    public void testJson_xml_406() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/test/json")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(406))
                .andReturn();

        Exception ex = mvcResult.getResolvedException();
        assertTrue(ex instanceof org.springframework.web.HttpMediaTypeNotAcceptableException);
        assertEquals(406, ((HttpMediaTypeNotAcceptableException) ex).getStatusCode().value());
        assertEquals("No acceptable representation", ex.getMessage());
    }

    @Test
    public void testXml_json_406() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/test/xml")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(406))
                .andReturn();

        Exception ex = mvcResult.getResolvedException();
        assertTrue(ex instanceof org.springframework.web.HttpMediaTypeNotAcceptableException);
        assertEquals(406, ((HttpMediaTypeNotAcceptableException) ex).getStatusCode().value());
        assertEquals("No acceptable representation", ex.getMessage());
    }
}