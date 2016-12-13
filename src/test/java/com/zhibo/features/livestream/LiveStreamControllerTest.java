package com.zhibo.features.livestream;

import com.jayway.jsonpath.JsonPath;
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

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                        "\"accountId\":\"1233444\"," +
                        "\"public\":true" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("aaa"))
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        MvcResult resultCollectionQuery = this.mockMvc.perform(get("/api/livestream")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();
        String respCollectionQuery = resultCollectionQuery.getResponse().getContentAsString();
        assertTrue(0 < (Integer) JsonPath.read(respCollectionQuery, "$.resources.length()"));

        result = this.mockMvc.perform(get("/api/livestream/" + id)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("aaa"))
                .andExpect(jsonPath("$.status").value("INITIALIZED"))
                .andExpect(jsonPath("$.public").value(true))
                .andReturn();
        String respBody = result.getResponse().getContentAsString();
        System.out.println(respBody); //For debug info.
        this.mockMvc.perform(delete("/api/livestream/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        this.mockMvc.perform(get("/api/livestream/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testOneToOneBetweenLiveStreamAndAccount() throws Exception {
        // Step 1 : prepare a user
        MvcResult resultCreateAccount = this.mockMvc.perform(post("/api/account")
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
        Integer accountId = JsonPath.read(resultCreateAccount.getResponse().getContentAsString(), "$.id");

        //Step 2: Create a live stream for created user in above step
        MvcResult resultCreateLiveStream = this.mockMvc.perform(post("/api/livestream")
                .content("{\n" +
                        "\"name\":\"testHostProperty\",\n" +
                        "\"account\":{\"id\":\"" +
                        accountId +
                        "\"}," +
                        "\"public\":true" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("testHostProperty"))
                .andReturn();
        String responseBody = resultCreateLiveStream.getResponse().getContentAsString();
        Integer liveStreamId = JsonPath.read(responseBody, "$.id");
        System.out.println(responseBody);//For debug purpose.

        // Step 3: query created live stream.
        this.mockMvc.perform(get("/api/livestream/" + liveStreamId)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.host.id").value(accountId));
    }

    @Test
    public void testModify() throws Exception {
        // Step 1 : prepare a user
        MvcResult resultCreateAccount = this.mockMvc.perform(post("/api/account")
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
        Integer accountId = JsonPath.read(resultCreateAccount.getResponse().getContentAsString(), "$.id");

        //Step 2: Create a live stream for created user in above step
        MvcResult resultCreateLiveStream = this.mockMvc.perform(post("/api/livestream")
                .content("{\n" +
                        "\"name\":\"testSendHeartbeat\",\n" +
                        "\"account\":{\"id\":\"" +
                        accountId +
                        "\"}," +
                        "\"public\":true" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("testSendHeartbeat"))
                .andReturn();
        String responseBody = resultCreateLiveStream.getResponse().getContentAsString();
        Integer liveStreamId = JsonPath.read(responseBody, "$.id");

        // Step 3: query created live stream.
        this.mockMvc.perform(get("/api/livestream/" + liveStreamId)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.host.id").value(accountId))
                .andExpect(jsonPath("$.status").value("INITIALIZED"));

        // Step 4: update status.
        this.mockMvc.perform(post("/api/livestream/" + liveStreamId + "/action/modify")
                .content("{\"status\":\"OK\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Step 5: verify result.
        this.mockMvc.perform(get("/api/livestream/" + liveStreamId)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.host.id").value(accountId))
                .andExpect(jsonPath("$.status").value("OK"));

        //Step 6: close
        this.mockMvc.perform(post("/api/livestream/" + liveStreamId + "/action/modify")
                .content("{\"status\":\"CLOSED\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Step 7: verify close result.
        this.mockMvc.perform(get("/api/livestream/" + liveStreamId)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.host.id").value(accountId))
                .andExpect(jsonPath("$.status").value("CLOSED"));

    }

}
