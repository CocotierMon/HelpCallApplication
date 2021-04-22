package com.helpcall.HelpCallApp.mapper;
import com.helpcall.HelpCallApp.domain.VolunteersWall;
import com.helpcall.HelpCallApp.domain.VolunteersWallDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolunteersWallMapper {

    public VolunteersWallDto mapToVnDto(final VolunteersWall volunteersWall) {
        return new VolunteersWallDto(volunteersWall.getTitle(), volunteersWall.getEndTime(), volunteersWall.getInstitutionName());
    }

    public VolunteersWall mapToVnList(final  VolunteersWallDto volunteersWallDto) {
        return new VolunteersWall(volunteersWallDto.getTitle(), volunteersWallDto.getEndTime(),
                volunteersWallDto.getInstitutionName());
    }

    public List<VolunteersWallDto> mapToVnListDto(final List<VolunteersWall> volunteersWalls) {
        return volunteersWalls.stream()
                .map(volunteersWall -> new VolunteersWallDto(volunteersWall.getTitle(), volunteersWall.getEndTime(),
                        volunteersWall.getInstitutionName())).collect(Collectors.toList());
    }

    public List<VolunteersWall> mapToVnList(final List<VolunteersWallDto> volunteersWallDtos) {
        return volunteersWallDtos.stream()
                .map(volunteersWallDto -> new VolunteersWall(volunteersWallDto.getTitle(),
                        volunteersWallDto.getEndTime(), volunteersWallDto.getInstitutionName()))
                .collect(Collectors.toList());
    }
}
