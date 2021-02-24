package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.NeedDto;
import com.helpcall.HelpCallApp.mapper.NeedMapper;
import com.helpcall.HelpCallApp.repository.NeedRepository;
import com.helpcall.HelpCallApp.service.NeedDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "adm", password = "adm", roles = "ADMIN")
@Transactional
public class NeedControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NeedRepository needRepository;
    @Autowired
    private NeedDbService service;
    @Autowired
    private NeedMapper needMapper;

    @Test
    @Transactional
    void shouldGetAllNeeds() throws Exception {
        //given
        Need need = new Need();
        need.setTitle("test1");
        need.setDescription("test1@test1");
        needRepository.save(need);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/needs"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        List<Need> needs = Arrays.asList(objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), Need[].class));
        assertThat(needs).isNotNull();
    }

    @Test
    @Transactional
    void shouldGetNeedById() throws Exception {
        //given
        Need need = new Need();
        need.setTitle("test2");
        need.setDescription("test2@test2");
        needRepository.save(need);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/needs/" + need.getId())
                .param("id", String.valueOf(need.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Need needs1 = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Need.class);
        assertThat(needs1).isNotNull();
        assertThat(needs1.getId()).isEqualTo(need.getId());
        assertThat(needs1.getTitle()).isEqualTo(need.getTitle());
        assertThat(needs1.getDescription()).isEqualTo("test2@test2");
    }

    @Test
    @Transactional
    void shouldCreateNewNeed() throws Exception {
        //given
        Need need = new Need();
        need.setTitle("test3");
        need.setDescription("test3@test3");
        NeedDto needDto = needMapper.mapToNeedDto(need);

        //when
        Gson gson = new Gson();
        String json = gson.toJson(needDto);

        //Then
        mockMvc.perform(post("/v1/needs").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void shouldUpdateNeed() throws Exception {
        //given
        Need need = new Need();
        need.setTitle("test4");
        need.setDescription("test4@test4");
        needRepository.save(need);

        //when
        Need need2 = new Need();
        need2.setId(need.getId());
        need2.setTitle("test44");
        need2.setDescription("test44@test44");
        NeedDto needDto2 = needMapper.mapToNeedDto(need2);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(needDto2);

        //then
        mockMvc.perform(put("/v1/needs").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    void shouldDeleteNeed() throws Exception {
        Need need = new Need();
        need.setTitle("test5");
        need.setDescription("test5@test5");
        needRepository.save(need);

        //when & then
        mockMvc.perform(delete("/v1/needs/" + need.getId())
                .param("id", String.valueOf(need.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
