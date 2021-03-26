package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolunteerMapper {

    public Volunteer mapToVolunteer(final VolunteerDto volunteerDto) {
        return new Volunteer(volunteerDto.getId(), volunteerDto.getName(), volunteerDto.getEmail(),
                volunteerDto.getPassword(), volunteerDto.getLat(), volunteerDto.getLon(), volunteerDto.getDescription(),
                mapToNeedList(volunteerDto.getNeeds()));
    }

    public List<Need> mapToNeedList(List<NeedDto> needDtoList) {
        return needDtoList.stream()
                .map(needDto -> new Need(
                        needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                        needDto.getLat(), needDto.getLon(), needDto.getEndTime(), mapToInstitution(needDto.getInstitution())))
                .collect(Collectors.toList());
    }

    public Institution mapToInstitution(final InstitutionDto institutionDto) {
        return new Institution(institutionDto.getId(), institutionDto.getName(), institutionDto.getEmail(),
                institutionDto.getLat(), institutionDto.getLon());
    }

    public VolunteerDto mapToVolunteerDto(final Volunteer volunteer) {
        return new VolunteerDto(volunteer.getId(), volunteer.getName(), volunteer.getEmail(),
                volunteer.getPassword(), volunteer.getLat(), volunteer.getLon(), volunteer.getDescription(),
                mapToNeedListDto(volunteer.getNeeds()));
    }

    public List<NeedDto> mapToNeedListDto(List<Need> needList) {
        return needList.stream()
                .map(need -> new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                        need.getLat(), need.getLon(), need.getEndTime(), mapToInstitutionDto(need.getInstitution())))
                .collect(Collectors.toList());
    }

    public InstitutionDto mapToInstitutionDto(final Institution institution) {
        return new InstitutionDto(institution.getId(), institution.getName(), institution.getEmail(),
                institution.getPassword(), institution.getLat(), institution.getLon(), institution.getDescription(),
                institution.getIsInstitution());
    }

    public List<Volunteer> mapToVolunteerList(List<VolunteerDto> volunteerDtoList) {
        return volunteerDtoList.stream()
                .map(volunteer -> new Volunteer(volunteer.getId(), volunteer.getName(), volunteer.getEmail(),
                        volunteer.getPassword(), volunteer.getLat(), volunteer.getLon(), volunteer.getDescription(),
                        mapToNeedList(volunteer.getNeeds())))
                .collect(Collectors.toList());
    }

    public List<VolunteerDto> mapToVolunteerDtoList(List<Volunteer> volunteerList) {
        return volunteerList.stream()
                .map(volunteerDto -> new VolunteerDto(volunteerDto.getId(), volunteerDto.getName(), volunteerDto.getEmail(),
                        volunteerDto.getPassword(), volunteerDto.getLat(), volunteerDto.getLon(), volunteerDto.getDescription(),
                        mapToNeedListDto(volunteerDto.getNeeds())))
                .collect(Collectors.toList());
    }
}
