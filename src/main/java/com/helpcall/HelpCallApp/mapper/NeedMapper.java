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
                needDto.getLat(), needDto.getLon(), needDto.getEndTime(), mapToInstitution(needDto.getInstitution()),
                mapToVolunteerList(needDto.getVolunteers()));
    }

    public Institution mapToInstitution(final InstitutionDto institutionDto) {
        return new Institution(institutionDto.getId(), institutionDto.getName(), institutionDto.getDescription(),
                institutionDto.getEmail(), institutionDto.getPassword(),
                institutionDto.getLat(), institutionDto.getLon(), institutionDto.getIsInstitution(),
                mapToNeedList(institutionDto.getNeeds()));
    }

    public Need mapToNeedWriteModel(final NeedDto needDto) {
        return new Need(needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                needDto.getLat(), needDto.getLon(), needDto.getEndTime());
    }

    public List<Volunteer> mapToVolunteerList(List<VolunteerDto> volunteerDtoList) {
        return volunteerDtoList.stream()
                .map(volunteer -> new Volunteer(volunteer.getId(), volunteer.getName(), volunteer.getEmail(),
                        volunteer.getPassword(), volunteer.getLat(), volunteer.getLon(), volunteer.getDescription(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public NeedDto mapToNeedDto(final Need need) {
        return new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                need.getLat(), need.getLon(), need.getEndTime(), mapToInstitutionDto(need.getInstitution()),
                mapToVolunteerDtoList(need.getVolunteers()));
    }

    public NeedDto mapToNeedDtoWrite(final Need need) {
        return new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                need.getLat(), need.getLon(), need.getEndTime(), mapToInstitutionDto(need.getInstitution()));
    }

    public InstitutionDto mapToInstitutionDto(final Institution institution) {
        return new InstitutionDto(institution.getId(), institution.getName(), institution.getEmail(),
                institution.getPassword(), institution.getLat(), institution.getLon(), institution.getDescription(),
                institution.getIsInstitution(), new ArrayList<>());
    }

    public List<VolunteerDto> mapToVolunteerDtoList(List<Volunteer> volunteerList) {
        return volunteerList.stream()
                .map(volunteerDto -> new VolunteerDto(volunteerDto.getId(), volunteerDto.getName(), volunteerDto.getEmail(),
                        volunteerDto.getPassword(), volunteerDto.getLat(), volunteerDto.getLon(), volunteerDto.getDescription(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public List<Need> mapToNeedList(List<NeedDto> needDtoList) {
        return needDtoList.stream()
                .map(needDto -> new Need(
                        needDto.getId(), needDto.getTitle(), needDto.getDescription(),
                        needDto.getLat(), needDto.getLon(), needDto.getEndTime(), mapToInstitution(needDto.getInstitution()),
                        mapToVolunteerList(needDto.getVolunteers())))
                .collect(Collectors.toList());
    }

    public List<NeedDto> mapToNeedListDto(List<Need> needList) {
        return needList.stream()
                .map(need -> new NeedDto(need.getId(), need.getTitle(), need.getDescription(),
                        need.getLat(), need.getLon(), need.getEndTime(), mapToInstitutionDto(need.getInstitution()),
                        mapToVolunteerDtoList(need.getVolunteers())))
                .collect(Collectors.toList());
    }
}
