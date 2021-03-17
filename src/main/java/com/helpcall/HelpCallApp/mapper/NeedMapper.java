package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NeedMapper {

    public Need mapToNeed(final NeedDto needDto) {
        return new Need(needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                needDto.getLat(), needDto.getLon(), needDto.getEndTime(), new Institution(),
                new ArrayList<>());
    }

    public NeedDto mapToNeedDto(final Need need) {
        return new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                need.getLat(), need.getLon(), need.getEndTime(), new InstitutionDto(),
                new ArrayList<>());
    }

    public List<Need> mapToNeedList(List<NeedDto> needDtoList) {
        return needDtoList.stream()
                .map(needDto -> new Need(
                        needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                        needDto.getLat(), needDto.getLon(), needDto.getEndTime(), new Institution(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public List<NeedDto> mapToNeedListDto(List<Need> needList) {
        return needList.stream()
                .map(need -> new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                        need.getLat(), need.getLon(), need.getEndTime(), new InstitutionDto(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }
}
