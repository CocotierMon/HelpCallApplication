package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.InstitutionDto;
import com.helpcall.HelpCallApp.mapper.InstitutionMapper;
import com.helpcall.HelpCallApp.repository.InstitutionRepository;
import com.helpcall.HelpCallApp.service.InstitutionDbService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "adm", password = "adm", roles = "ADMIN")
public class InstitutionControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private InstitutionDbService service;
    @Autowired
    private InstitutionMapper institutionMapper;

    @Test
    @Transactional
    void shouldGetAllInstitutions() throws Exception {
        //given
        Institution inst = new Institution();
        inst.setName("test1");
        inst.setEmail("test1@test1");
        institutionRepository.save(inst);

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
    @Transactional
    void shouldGetInstitutionById() throws Exception {
        //given
        Institution inst = new Institution();
        inst.setName("test2");
        inst.setEmail("test2@test2");
        institutionRepository.save(inst);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/institutions/" + inst.getId())
                .param("id", String.valueOf(inst.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Institution institution = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Institution.class);
        assertThat(institution).isNotNull();
        assertThat(institution.getId()).isEqualTo(inst.getId());
        assertThat(institution.getName()).isEqualTo(inst.getName());
        assertThat(institution.getEmail()).isEqualTo("test2@test2");
    }

    @Test
    @Transactional
    void shouldCreateNewInstitution() throws Exception {
        //given
        Institution inst = new Institution();
        inst.setName("test3");
        inst.setEmail("test3@test3");
        InstitutionDto institutionDto = institutionMapper.mapToInstitutionDto(inst);

        //when
        Gson gson = new Gson();
        String json = gson.toJson(institutionDto);

        //Then
        mockMvc.perform(post("/v1/institutions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void shouldUpdateInstitution() throws Exception {
        //given
        Institution inst = new Institution();
        inst.setName("test4");
        inst.setEmail("test4@test4");
        institutionRepository.save(inst);

        //when
        Institution inst2 = new Institution();
        inst2.setId(inst.getId());
        inst2.setName("test44");
        inst2.setEmail("test44@test44");
        InstitutionDto instDto2 = institutionMapper.mapToInstitutionDto(inst2);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(instDto2);

        //then
        mockMvc.perform(put("/v1/institutions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    void shouldDeleteInstitution() throws Exception {
        Institution inst = new Institution();
        inst.setName("test5");
        inst.setEmail("test5@test5");
        institutionRepository.save(inst);

        //when & then
        mockMvc.perform(delete("/v1/institutions/" + inst.getId())
                .param("id", String.valueOf(inst.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
