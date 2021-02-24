package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.domain.VolunteerDto;
import com.helpcall.HelpCallApp.mapper.VolunteerMapper;
import com.helpcall.HelpCallApp.repository.VolunteerRepository;
import com.helpcall.HelpCallApp.service.VolunteerDbService;
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
@Transactional
public class VolunteerControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerDbService service;
    @Autowired
    private VolunteerMapper volunteerMapper;

    @Test
    @Transactional
    void shouldGetAllVolunteers() throws Exception {
        //given
        Volunteer vol = new Volunteer();
        vol.setName("test1");
        vol.setEmail("test1@test1");
        volunteerRepository.save(vol);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/volunteers"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        List<Volunteer> volunteers = Arrays.asList(objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), Volunteer[].class));
        assertThat(volunteers).isNotNull();
    }

    @Test
    @Transactional
    void shouldGetVolunteerById() throws Exception {
        //given
        Volunteer vol = new Volunteer();
        vol.setName("test2");
        vol.setEmail("test2@test2");
        volunteerRepository.save(vol);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/volunteers/" + vol.getId())
                .param("id", String.valueOf(vol.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Volunteer volunteer = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Volunteer.class);
        assertThat(volunteer).isNotNull();
        assertThat(volunteer.getId()).isEqualTo(vol.getId());
        assertThat(volunteer.getName()).isEqualTo(vol.getName());
        assertThat(volunteer.getEmail()).isEqualTo("test2@test2");
    }

    @Test
    @Transactional
    void shouldCreateNewVolunteer() throws Exception {
        //given
        Volunteer vol = new Volunteer();
        vol.setName("test3");
        vol.setEmail("test3@test3");
        VolunteerDto volunteerDto = volunteerMapper.mapToVolunteerDto(vol);

        //when
        Gson gson = new Gson();
        String json = gson.toJson(volunteerDto);

        //Then
        mockMvc.perform(post("/v1/volunteers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void shouldUpdateVolunteer() throws Exception {
        //given
        Volunteer vol = new Volunteer();
        vol.setName("test4");
        vol.setEmail("test4@test4");
        volunteerRepository.save(vol);

        //when
        Volunteer vol2 = new Volunteer();
        vol2.setId(vol.getId());
        vol2.setName("test44");
        vol2.setEmail("test44@test44");
        VolunteerDto volDto2 = volunteerMapper.mapToVolunteerDto(vol2);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(volDto2);

        //then
        mockMvc.perform(put("/v1/volunteers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    void shouldDeleteVolunteer() throws Exception {
        Volunteer vol = new Volunteer();
        vol.setName("test5");
        vol.setEmail("test5@test5");
        volunteerRepository.save(vol);

        //when & then
        mockMvc.perform(delete("/v1/volunteers/" + vol.getId())
                .param("id", String.valueOf(vol.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
