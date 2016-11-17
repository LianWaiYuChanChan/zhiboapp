package com.zhibo.features.livestream;

import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jichao on 2016/11/17.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:test-account-controller-context.xml")
public class LiveStreamControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void Crud() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/livestream")
                .content("{\n" +
                        "\"name\":\"aaa\",\n" +
                        "\"accountId\":\"1233444\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("aaa"))
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        Assert.assertEquals(new Integer(0), id);
    }

}
