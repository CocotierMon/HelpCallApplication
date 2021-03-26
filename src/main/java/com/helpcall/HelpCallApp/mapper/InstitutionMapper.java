package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstitutionMapper {

    public Institution mapToInstitution(final InstitutionDto institutionDto) {
        return new Institution(institutionDto.getId(), institutionDto.getName(), institutionDto.getEmail(),
                institutionDto.getPassword(), institutionDto.getLat(), institutionDto.getLon(), institutionDto.getDescription(),
                institutionDto.getIsInstitution(), mapToNeedList(institutionDto.getNeeds()));
    }

    public List<Need> mapToNeedList(List<NeedDto> needDtoList) {
        return needDtoList.stream()
                .map(needDto -> new Need(
                        needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                        needDto.getLat(), needDto.getLon(), needDto.getEndTime(),
                        mapToVolunteerList(needDto.getVolunteers())))
                .collect(Collectors.toList());
    }

    public List<Volunteer> mapToVolunteerList(List<VolunteerDto> volunteerDtoList) {
        return volunteerDtoList.stream()
                .map(volunteer -> new Volunteer(volunteer.getId(), volunteer.getName(), volunteer.getEmail(),
                        volunteer.getPassword(), volunteer.getLat(), volunteer.getLon(), volunteer.getDescription(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public InstitutionDto mapToInstitutionDto(final Institution institution) {
        return new InstitutionDto(institution.getId(), institution.getName(), institution.getEmail(),
                institution.getPassword(), institution.getLat(), institution.getLon(), institution.getDescription(),
                institution.getIsInstitution(), mapToNeedListDto(institution.getNeeds()));
    }

    public List<NeedDto> mapToNeedListDto(List<Need> needList) {
        return needList.stream()
                .map(need -> new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                        need.getLat(), need.getLon(), need.getEndTime(),
                        mapToVolunteerDtoList(need.getVolunteers())))
                .collect(Collectors.toList());
    }

    public List<VolunteerDto> mapToVolunteerDtoList(List<Volunteer> volunteerList) {
        return volunteerList.stream()
                .map(volunteerDto -> new VolunteerDto(volunteerDto.getId(), volunteerDto.getName(), volunteerDto.getEmail(),
                        volunteerDto.getPassword(), volunteerDto.getLat(), volunteerDto.getLon(), volunteerDto.getDescription(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public List<Institution> mapToInstitutionList(List<InstitutionDto> institutionDtoList) {
        return institutionDtoList.stream()
                .map(institution -> new Institution(institution.getId(), institution.getName(), institution.getEmail(),
                        institution.getPassword(), institution.getLat(), institution.getLon(), institution.getDescription(),
                        institution.getIsInstitution(), mapToNeedList(institution.getNeeds())))
                .collect(Collectors.toList());
    }

    public List<InstitutionDto> mapToInstitutionDtoList(List<Institution> institutionList) {
        return institutionList.stream()
                .map(institutionDto -> new InstitutionDto(institutionDto.getId(), institutionDto.getName(), institutionDto.getEmail(),
                        institutionDto.getPassword(), institutionDto.getLat(), institutionDto.getLon(), institutionDto.getDescription(),
                        institutionDto.getIsInstitution(), mapToNeedListDto(institutionDto.getNeeds())))
                .collect(Collectors.toList());
    }
}
