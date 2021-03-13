package com.helpcall.HelpCallApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helpcall.HelpCallApp.domain.NeedsBoard;
import com.helpcall.HelpCallApp.domain.NeedsBoardDto;
import com.helpcall.HelpCallApp.mapper.NeedsBoardMapper;
import com.helpcall.HelpCallApp.service.NeedsBoardDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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
public class NeedsBoardControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private NeedsBoardDbService service;
    @MockBean
    private NeedsBoardMapper needsBoardMapper;

    @Test
    void shouldGetNeedsBoardById() throws Exception {
        //given
        NeedsBoard nb = new NeedsBoard(1L);
        Long id = nb.getId();
        NeedsBoardDto nbDto = new NeedsBoardDto(2L);
        when(service.getNeedsBoard(id)).thenReturn(java.util.Optional.of(nb));
        when(needsBoardMapper.mapToNeedsBoardsDto(nb)).thenReturn(nbDto);

        service.saveNeedsBoard(nb);

        //when & then
        mockMvc.perform(get("/v1/needsBoards/" + nb.getId())
                .param("id", String.valueOf(nb.getId())))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(2)));
    }

    @Test
    void shouldCreateNewNeedsBoard() throws Exception {
        //given
        NeedsBoard nb = new NeedsBoard(1L);
        NeedsBoardDto nbDto = new NeedsBoardDto(2L);
        when(needsBoardMapper.mapToNeedsBoardsDto(nb)).thenReturn(nbDto);
        when(needsBoardMapper.mapToNeedsBoard(nbDto)).thenReturn(nb);

        Gson gson = new Gson();
        String json = gson.toJson(nbDto);

        //when & then
        mockMvc.perform(post("/v1/needsBoards").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateNeedsBoard() throws Exception {
        //given
        NeedsBoard nb = new NeedsBoard(1L);
        NeedsBoardDto nbDto = new NeedsBoardDto(1L);
        when(service.saveNeedsBoard(nb)).thenReturn(nb);
        when(needsBoardMapper.mapToNeedsBoard(any(NeedsBoardDto.class))).thenReturn(nb);
        when(needsBoardMapper.mapToNeedsBoardsDto(any(NeedsBoard.class))).thenReturn(nbDto);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(nbDto);

        //when & then
        mockMvc.perform(put("/v1/needsBoards").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json2))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.Id", is(1)));
    }

    @Test
    void shouldDeleteNeedsBoard() throws Exception {
        NeedsBoard nb = new NeedsBoard(1L);
        when(service.saveNeedsBoard(nb)).thenReturn(nb);
        service.saveNeedsBoard(nb);

        //when & then
        mockMvc.perform(delete("/v1/needsBoards/" + nb.getId())
                .param("id", String.valueOf(nb.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
