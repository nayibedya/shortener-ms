package com.example.shortener.IntegrationTest;

import com.example.shortener.AutoConfigureTest;
import com.example.shortener.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShortenerControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generate_api_it_test() throws Exception {
        var generateRequest = get("/generate?url=" + TestUtils.URL);
        ResultActions response = mockMvc.perform(generateRequest);
        response.andExpect(status().isOk());
    }

    @Test
    void getAll_api_it_test() throws Exception {
        var getRequest = get("/getAll");
        ResultActions response = mockMvc.perform(getRequest);
        response.andExpect(status().isOk());
    }

    @Test
    void redirect_api_it_test() throws Exception {
        //Given
        var generateRequest = get("/generate?url=" + TestUtils.URL);
        ResultActions generateResponse = mockMvc.perform(generateRequest);
        var url = generateResponse.andReturn().getResponse().getContentAsString();

        //When
        var getRequest = get(url);
        ResultActions redirectResponse = mockMvc.perform(getRequest);

        //Then
        redirectResponse.andExpect(redirectedUrl(TestUtils.URL));
    }
}
