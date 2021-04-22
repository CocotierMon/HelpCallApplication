package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.InstitutionDto;
import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.NeedDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NeedMapperTestSuite {

    @InjectMocks
    NeedMapper needMapper;

    @Test
    void testNeedMapper() {

        //given
        Need need = new Need(1L, "test", "test", "latitude", "longitude",
                LocalDate.now(), new Institution(), new ArrayList<>());
        Need need1 = new Need(2L, "test2", "test2@test2", "latitude", "longitude",
                LocalDate.now(), new Institution(), new ArrayList<>());
        Need need2 = new Need(3L, "test3", "test3@test3", "latitude", "longitude",
                LocalDate.now(), new Institution(), new ArrayList<>());

        NeedDto needDto = new NeedDto(1L, "test2","test2@test2", "latitude", "longitude",
                LocalDate.now(), new InstitutionDto(), new ArrayList<>());
        NeedDto needDto1 = new NeedDto(2L, "test2", "test2@test2", "latitude", "longitude",
                LocalDate.now(), new InstitutionDto(), new ArrayList<>());
        NeedDto needDto2 = new NeedDto(3L, "test3", "test3@test3", "latitude", "longitude",
                LocalDate.now(), new InstitutionDto(), new ArrayList<>());

        List<Need> needs = new ArrayList<>();
        List<Need> needs1;
        List<NeedDto> needDtos;
        List<NeedDto> needDtos1 = new ArrayList<>();

        //When
        needs.add(need);
        needs.add(need1);
        needs.add(need2);
        needDtos = needMapper.mapToNeedListDto(needs);
        needDtos1.add(needDto);
        needDtos1.add(needDto1);
        needDtos1.add(needDto2);
        needs1 = needMapper.mapToNeedList(needDtos1);

        //Then
        assertEquals(needs.size(), needDtos.size());
        assertEquals(need.getId(), needMapper.mapToNeedDto(need).getId());
        assertEquals(need.getTitle(), needMapper.mapToNeedDto(need).getTitle());
        assertEquals(need.getDescription(), needMapper.mapToNeedDto(need).getDescription());

        assertEquals(needDto.getId(), needMapper.mapToNeed(needDto).getId());
        assertEquals(needDto.getTitle(), needMapper.mapToNeed(needDto).getTitle());
        assertEquals(needDto.getDescription(), needMapper.mapToNeed(needDto).getDescription());

        assertEquals(needDtos1.size(), needs1.size());
        assertEquals(needDto.getId(), needMapper.mapToNeed(needDto).getId());
    }
}
