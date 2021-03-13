package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.NeedDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NeedMapper {

    public Need mapToNeed(final NeedDto needDto) {
        return new Need(needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                needDto.getLat(), needDto.getLon(), needDto.getEndTime(), needDto.getInstitution(),
                needDto.getVolunteers(), needDto.getNeedsBoards());
    }

    public NeedDto mapToNeedDto(final Need need) {
        return new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                need.getLat(), need.getLon(), need.getEndTime(), need.getInstitution(),
                need.getVolunteers(), need.getNeedsBoards());
    }

    public List<Need> mapToNeedList(List<NeedDto> needDtoList) {
        return needDtoList.stream()
                .map(needDto -> new Need(
                        needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                        needDto.getLat(), needDto.getLon(), needDto.getEndTime(), needDto.getInstitution(),
                        needDto.getVolunteers(), needDto.getNeedsBoards()))
                .collect(Collectors.toList());
    }

    public List<NeedDto> mapToNeedListDto(List<Need> needList) {
        return needList.stream()
                .map(need -> new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                        need.getLat(), need.getLon(), need.getEndTime(), need.getInstitution(),
                        need.getVolunteers(), need.getNeedsBoards()))
                .collect(Collectors.toList());
    }
}
