package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.NeedDto;
import com.helpcall.HelpCallApp.mapper.NeedMapper;
import com.helpcall.HelpCallApp.service.NeedDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
//@WithMockUser(username = "adm", password = "adm", roles = "ADMIN")
public class NeedControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private NeedDbService service;
    @MockBean
    private NeedMapper needMapper;

    @Test
    void shouldGetAllNeeds() throws Exception {
        //given
        Need need = new Need();
        need.setTitle("test1");
        need.setDescription("test1@test1");
        service.saveNeed(need);

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
    void shouldGetNeedById() throws Exception {
        //given
        Need need = new Need(1L, "test2", "test2", 51.01, 51.01, LocalDate.now());
        Long id = need.getId();
        NeedDto needDto = new NeedDto(2L, "test22", "test22");
        when(service.getNeed(id)).thenReturn(java.util.Optional.of(need));
        when(needMapper.mapToNeedDto(need)).thenReturn(needDto);

        service.saveNeed(need);

        //when & then
        mockMvc.perform(get("/v1/needs/" + need.getId())
                .param("id", String.valueOf(need.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(2)))
                .andExpect(jsonPath("$.Title", is("test22")))
                .andExpect(jsonPath("$.Description", is("test22")));
    }

    @Test
    void shouldCreateNewNeed() throws Exception {
        //given
        Need need = new Need(1L, "test3", "test3", 51.01, 51.01, LocalDate.now());
        NeedDto needDto = new NeedDto(2L, "test33", "test33");
        when(needMapper.mapToNeedDto(need)).thenReturn(needDto);
        when(needMapper.mapToNeed(needDto)).thenReturn(need);

        Gson gson = new Gson();
        String json = gson.toJson(needDto);

        //when & then
        mockMvc.perform(post("/v1/needs").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateNeed() throws Exception {
        //given
        Need need = new Need(1L, "test4", "test4", 51.01, 51.01, LocalDate.now());
        NeedDto needDto = new NeedDto(1L, "test44", "test44");
        when(service.saveNeed(need)).thenReturn(need);
        when(needMapper.mapToNeed(any(NeedDto.class))).thenReturn(need);
        when(needMapper.mapToNeedDto(any(Need.class))).thenReturn(needDto);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(needDto);

        //when & then
        mockMvc.perform(put("/v1/needs").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(1)))
                .andExpect(jsonPath("$.Title", is("test44")))
                .andExpect(jsonPath("$.Description", is("test44")));
    }

    @Test
    void shouldDeleteNeed() throws Exception {
        Need need = new Need(1L, "test5", "test5", 51.01, 51.01, LocalDate.now());
        when(service.saveNeed(need)).thenReturn(need);
        service.saveNeed(need);

        //when & then
        mockMvc.perform(delete("/v1/needs/" + need.getId())
                .param("id", String.valueOf(need.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
