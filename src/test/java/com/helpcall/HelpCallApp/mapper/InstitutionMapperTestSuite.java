package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.InstitutionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InstitutionMapperTestSuite {

    @InjectMocks
    InstitutionMapper institutionMapper;

    @Test
    void testInstitutionMapper() {
        //given
        Institution institution = new Institution(1L, "test", "test@test", "test", 51.01,
                51.01, "description","isInstitution", new ArrayList<>());
        Institution institution1 = new Institution(2L, "test2", "test2@test2", "test", 51.01,
                51.01, "description","isInstitution", new ArrayList<>());
        Institution institution2 = new Institution(3L, "test3", "test3@test3", "test", 51.01,
                51.01, "description","isInstitution", new ArrayList<>());

        InstitutionDto institutionDto = new InstitutionDto(1L, "test2", "test2@test2", "test", 51.01,
                51.01, "description","isInstitution", new ArrayList<>());
        InstitutionDto institutionDto1 = new InstitutionDto(2L, "test2", "test2@test2", "test", 51.01,
                51.01, "description","isInstitution", new ArrayList<>());
        InstitutionDto institutionDto2 = new InstitutionDto(3L, "test3", "test3@test3", "test", 51.01,
                51.01, "description","isInstitution", new ArrayList<>());

        List<Institution> institutions = new ArrayList<>();
        List<Institution> institutions1;
        List<InstitutionDto> institutionDtos;
        List<InstitutionDto> institutionDtos1 = new ArrayList<>();

        //When
        institutions.add(institution);
        institutions.add(institution1);
        institutions.add(institution2);
        institutionDtos = institutionMapper.mapToInstitutionDtoList(institutions);
        institutionDtos1.add(institutionDto);
        institutionDtos1.add(institutionDto1);
        institutionDtos1.add(institutionDto2);
        institutions1 = institutionMapper.mapToInstitutionList(institutionDtos1);

        //Then
        assertEquals(institutions.size(), institutionDtos.size());
        assertEquals(institution.getId(), institutionMapper.mapToInstitutionDto(institution).getId());
        assertEquals(institution.getName(), institutionMapper.mapToInstitutionDto(institution).getName());
        assertEquals(institution.getEmail(), institutionMapper.mapToInstitutionDto(institution).getEmail());

        assertEquals(institutionDto.getId(), institutionMapper.mapToInstitution(institutionDto).getId());
        assertEquals(institutionDto.getName(), institutionMapper.mapToInstitution(institutionDto).getName());
        assertEquals(institutionDto.getEmail(), institutionMapper.mapToInstitution(institutionDto).getEmail());

        assertEquals(institutionDtos1.size(), institutions1.size());
        assertEquals(institutionDto.getId(), institutionMapper.mapToInstitution(institutionDto).getId());
    }
}
