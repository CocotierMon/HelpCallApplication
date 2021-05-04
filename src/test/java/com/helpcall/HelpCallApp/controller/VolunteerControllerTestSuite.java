package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.domain.VolunteerDto;
import com.helpcall.HelpCallApp.mapper.VolunteerMapper;
import com.helpcall.HelpCallApp.service.VolunteerDbService;
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
public class VolunteerControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private VolunteerDbService service;
    @MockBean
    private VolunteerMapper volunteerMapper;

    @Test
    void shouldGetAllVolunteers() throws Exception {
        //given
        Volunteer vol = new Volunteer();
        vol.setName("test1");
        vol.setEmail("test1@test1");
        service.saveVolunteer(vol);

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
    void shouldGetVolunteerById() throws Exception {
        //given
        Volunteer vol = new Volunteer(1L, "test2", "test2", 51.01, 51.01);
        Long id = vol.getId();
        VolunteerDto volunteerDto = new VolunteerDto(2L, "test22", "test22");
        when(service.getVolunteer(id)).thenReturn(java.util.Optional.of(vol));
        when(volunteerMapper.mapToVolunteerDto(vol)).thenReturn(volunteerDto);

        service.saveVolunteer(vol);

        //when & then
        mockMvc.perform(get("/v1/volunteers/" + vol.getId())
                .param("id", String.valueOf(vol.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(2)))
                .andExpect(jsonPath("$.Name", is("test22")))
                .andExpect(jsonPath("$.Email", is("test22")));
    }

    @Test
    void shouldCreateNewVolunteer() throws Exception {
        //given
        Volunteer vol = new Volunteer(1L, "test3", "test3", 51.01, 51.01);
        VolunteerDto volunteerDto = new VolunteerDto(2L, "test33", "test33");
        when(volunteerMapper.mapToVolunteerDto(vol)).thenReturn(volunteerDto);
        when(volunteerMapper.mapToVolunteer(volunteerDto)).thenReturn(vol);

        Gson gson = new Gson();
        String json = gson.toJson(volunteerDto);

        //when & then
        mockMvc.perform(post("/v1/volunteers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateVolunteer() throws Exception {
        //given
        Volunteer vol = new Volunteer(1L, "test4", "test4", 51.01, 51.01);
        VolunteerDto volunteerDto = new VolunteerDto(1L, "test44", "test44");
        when(service.saveVolunteer(vol)).thenReturn(vol);
        when(volunteerMapper.mapToVolunteer(any(VolunteerDto.class))).thenReturn(vol);
        when(volunteerMapper.mapToVolunteerDto(any(Volunteer.class))).thenReturn(volunteerDto);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(volunteerDto);

        //when & then
        mockMvc.perform(put("/v1/volunteers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(1)))
                .andExpect(jsonPath("$.Name", is("test44")))
                .andExpect(jsonPath("$.Email", is("test44")));
    }

    @Test
    void shouldDeleteVolunteer() throws Exception {
        Volunteer vol = new Volunteer(1L, "test5", "test5", 51.01, 51.01);
        when(service.saveVolunteer(vol)).thenReturn(vol);
        service.saveVolunteer(vol);

        //when & then
        mockMvc.perform(delete("/v1/volunteers/" + vol.getId())
                .param("id", String.valueOf(vol.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
