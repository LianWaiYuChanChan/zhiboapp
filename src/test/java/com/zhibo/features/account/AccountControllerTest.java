package com.zhibo.features.account;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jichao on 2016/10/29.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:test-account-controller-context.xml")
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCollectionQuery() throws Exception {
        this.mockMvc.perform(get("/api/account")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.resources[0].name").value("Good"));
    }

    @Test
    public void testModify() throws Exception {
        String originalName = "";
        MvcResult result = this.mockMvc.perform(get("/api/account/0")).andReturn();

        String content = result.getResponse().getContentAsString();
        originalName = JsonPath.read(content, "$.name");
        this.mockMvc.perform(post("/api/account/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"changedname\"}")
        )
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get("/api/account/0")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("changedname"));

        //Roll back the name
        this.mockMvc.perform(post("/api/account/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"" + originalName + "\"}")
        )
                .andExpect(status().isNoContent());

        this.mockMvc.perform(get("/api/account/0")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(originalName));

    }

    @Test
    public void Crud() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/account")
                .content("{\n" +
                        "\"name\":\"aaa\",\n" +
                        "\"phoneNumber\":\"1233444\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("aaa"))
                .andReturn();

        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        Assert.assertEquals(new Integer(1), id);

        this.mockMvc.perform(delete("/api/account/"+id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        this.mockMvc.perform(get("/api/account/"+id))
                .andExpect(status().isNotFound());
    }

}