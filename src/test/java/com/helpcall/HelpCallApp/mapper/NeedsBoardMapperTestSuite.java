package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.NeedsBoard;
import com.helpcall.HelpCallApp.domain.NeedsBoardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NeedsBoardMapperTestSuite {

    @InjectMocks
    NeedsBoardMapper needsBoardMapper;

    @Test
    void testNeedsBoardMapper() {
        //given
        NeedsBoard needsBoard = new NeedsBoard(1L);

        NeedsBoardDto needsBoardDto = new NeedsBoardDto(2L);

        //When
        NeedsBoardDto needsBoardDto1 = needsBoardMapper.mapToNeedsBoardsDto(needsBoard);

        NeedsBoard needsBoard1 = needsBoardMapper.mapToNeedsBoard(needsBoardDto);

        //Then
        assertEquals(needsBoard1.getId(), needsBoardDto.getId());
        assertEquals(needsBoardDto1.getId(), needsBoard.getId());
    }
}
