package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.domain.VolunteerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolunteerMapper {

    public Volunteer mapToVolunteer(final VolunteerDto volunteerDto) {
        return new Volunteer(volunteerDto.getId(), volunteerDto.getName(), volunteerDto.getEmail());
    }

    public VolunteerDto mapToVolunteerDto(final Volunteer volunteer) {
        return new VolunteerDto(volunteer.getId(), volunteer.getName(), volunteer.getEmail());
    }

    public List<Volunteer> mapToVolunteerList(List<VolunteerDto> volunteerDtoList) {
        return volunteerDtoList.stream()
                .map(volunteer -> new Volunteer(volunteer.getId(), volunteer.getName(), volunteer.getEmail()))
                .collect(Collectors.toList());
    }

    public List<VolunteerDto> mapToVolunteerDtoList(List<Volunteer> volunteerList) {
        return volunteerList.stream()
                .map(volunteerDto -> new VolunteerDto(volunteerDto.getId(), volunteerDto.getName(),
                        volunteerDto.getEmail()))
                .collect(Collectors.toList());
    }
}
