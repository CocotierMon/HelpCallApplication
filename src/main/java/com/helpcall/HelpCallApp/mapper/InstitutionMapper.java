package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.InstitutionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstitutionMapper {

    public Institution mapToInstitution(final InstitutionDto institutionDto) {
        return new Institution(institutionDto.getId(), institutionDto.getName(), institutionDto.getEmail(),
                institutionDto.getPassword(), institutionDto.getLat(), institutionDto.getLon(), institutionDto.getDescription(),
                institutionDto.getIsInstitution(), institutionDto.getNeeds());
    }

    public InstitutionDto mapToInstitutionDto(final Institution institution) {
        return new InstitutionDto(institution.getId(), institution.getName(), institution.getEmail(),
                institution.getPassword(), institution.getLat(), institution.getLon(), institution.getDescription(),
                institution.getIsInstitution(), institution.getNeeds());
    }

    public List<Institution> mapToInstitutionList(List<InstitutionDto> institutionDtoList) {
        return institutionDtoList.stream()
                .map(institution -> new Institution(institution.getId(), institution.getName(), institution.getEmail(),
                        institution.getPassword(), institution.getLat(), institution.getLon(), institution.getDescription(),
                        institution.getIsInstitution(), institution.getNeeds()))
                .collect(Collectors.toList());
    }

    public List<InstitutionDto> mapToInstitutionDtoList(List<Institution> institutionList) {
        return institutionList.stream()
                .map(institutionDto -> new InstitutionDto(institutionDto.getId(), institutionDto.getName(), institutionDto.getEmail(),
                        institutionDto.getPassword(), institutionDto.getLat(), institutionDto.getLon(), institutionDto.getDescription(),
                        institutionDto.getIsInstitution(), institutionDto.getNeeds()))
                .collect(Collectors.toList());
    }
}
