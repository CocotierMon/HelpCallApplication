package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.NeedsBoard;
import com.helpcall.HelpCallApp.domain.NeedsBoardDto;
import com.helpcall.HelpCallApp.mapper.NeedsBoardMapper;
import com.helpcall.HelpCallApp.repository.NeedsBoardRepository;
import com.helpcall.HelpCallApp.service.NeedsBoardDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "adm", password = "adm", roles = "ADMIN")
public class NeedsBoardControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NeedsBoardRepository needsBoardRepository;
    @Autowired
    private NeedsBoardDbService service;
    @Autowired
    private NeedsBoardMapper needsBoardMapper;

    @Test
    @Transactional
    void shouldGetNeedsBoardById() throws Exception {
        //given
        NeedsBoard nb = new NeedsBoard();
        needsBoardRepository.save(nb);

        //when & then
        MvcResult mvcResult = mockMvc.perform(get("/v1/needsBoards/" + nb.getId())
                .param("id", String.valueOf(nb.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        NeedsBoard nb2 = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), NeedsBoard.class);
        assertThat(nb2).isNotNull();
        assertThat(nb2.getId()).isEqualTo(nb.getId());
    }

    @Test
    @Transactional
    void shouldCreateNewNeedsBoard() throws Exception {
        //given
        NeedsBoard nb = new NeedsBoard();
        NeedsBoardDto nbDto = needsBoardMapper.mapToNeedsBoardsDto(nb);

        //when
        Gson gson = new Gson();
        String json = gson.toJson(nbDto);

        //Then
        mockMvc.perform(post("/v1/needsBoards").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void shouldUpdateNeedsBoard() throws Exception {
        //given
        NeedsBoard nb = new NeedsBoard();
        needsBoardRepository.save(nb);

        //when
        NeedsBoard nb2 = new NeedsBoard();
        nb2.setId(nb.getId());
        NeedsBoardDto nbDto2 = needsBoardMapper.mapToNeedsBoardsDto(nb2);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(nbDto2);

        //then
        mockMvc.perform(put("/v1/needsBoards").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    void shouldDeleteNeedsBoard() throws Exception {
        NeedsBoard nb = new NeedsBoard();
        needsBoardRepository.save(nb);

        //when & then
        mockMvc.perform(delete("/v1/needsBoards/" + nb.getId())
                .param("id", String.valueOf(nb.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
