package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.InstitutionDto;
import com.helpcall.HelpCallApp.mapper.InstitutionMapper;
import com.helpcall.HelpCallApp.service.InstitutionDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
public class InstitutionControllerTestSuite {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    InstitutionDbService service;
    @MockBean
    InstitutionMapper institutionMapper;

    @Test
    void shouldGetAllInstitutions() throws Exception {
        //given
        Institution inst = new Institution();
        inst.setName("test1");
        inst.setEmail("test1@test1");
        service.saveInstitution(inst);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/institutions"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        List<Institution> institutions = Arrays.asList(objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), Institution[].class));
        assertThat(institutions).isNotNull();
    }

    @Test
    void shouldGetInstitutionById() throws Exception {
        //given
        Institution inst = new Institution(1L, "test2", "test2");
        Long id = inst.getId();
        InstitutionDto institutionDto = new InstitutionDto(2L, "test22", "test22");
        when(service.getInstitution(id)).thenReturn(java.util.Optional.of(inst));
        when(institutionMapper.mapToInstitutionDto(inst)).thenReturn(institutionDto);

        service.saveInstitution(inst);

        //when & then
        mockMvc.perform(get("/v1/institutions/" + inst.getId())
                .param("id", String.valueOf(inst.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(2)))
                .andExpect(jsonPath("$.Name", is("test22")))
                .andExpect(jsonPath("$.Email", is("test22")));
    }

    @Test
    void shouldCreateNewInstitution() throws Exception {
        //given
        Institution inst = new Institution(1L, "test3", "test3");
        InstitutionDto institutionDto = new InstitutionDto(2L, "test33", "test33");

        when(institutionMapper.mapToInstitutionDto(inst)).thenReturn(institutionDto);
        when(institutionMapper.mapToInstitution(institutionDto)).thenReturn(inst);

        Gson gson = new Gson();
        String json = gson.toJson(institutionDto);

        //when & then
        mockMvc.perform(post("/v1/institutions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateInstitution() throws Exception {
        //given
        Institution inst = new Institution(1L, "test4", "test4");
        InstitutionDto institutionDto = new InstitutionDto(1L, "test44", "test44");
        when(service.saveInstitution(inst)).thenReturn(inst);
        when(institutionMapper.mapToInstitutionWriteMapper(any(InstitutionDto.class))).thenReturn(inst);
        when(institutionMapper.mapToInstitutionDto(any(Institution.class))).thenReturn(institutionDto);

        Gson gson = new Gson();
        String json = gson.toJson(institutionDto);

        //when & then
        mockMvc.perform(put("/v1/institutions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().is(200))
             //   .andExpect(jsonPath("$.Id", is(1)))
                .andExpect(jsonPath("$.Name", is("test44")))
                .andExpect(jsonPath("$.Email", is("test44")));
    }

    @Test
    void shouldDeleteInstitution() throws Exception {
        Institution inst = new Institution(1L, "test5", "test5");
        when(service.saveInstitution(inst)).thenReturn(inst);
        service.saveInstitution(inst);

        //when & then
        mockMvc.perform(delete("/v1/institutions/" + inst.getId())
                .param("id", String.valueOf(inst.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}