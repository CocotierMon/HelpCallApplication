package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.domain.VolunteerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VolunteerMapperTestSuite {

    @InjectMocks
    VolunteerMapper volunteerMapper;

    @Test
    void testVolunteerMapper() {

        //given
        Volunteer volunteer = new Volunteer(1L, "test", "test@test", "password", "latitude",
                "longitude", "description", new ArrayList<>());
        Volunteer volunteer1 = new Volunteer(2L, "test2", "test2@test2", "password", "latitude",
                "longitude", "description", new ArrayList<>());
        Volunteer volunteer2 = new Volunteer(3L, "test3", "test3@test3", "password", "latitude",
                "longitude", "description", new ArrayList<>());

        VolunteerDto volunteerDto = new VolunteerDto(1L, "test2","test2@test2", "password", "latitude",
                "longitude", "description", new ArrayList<>());
        VolunteerDto volunteerDto1 = new VolunteerDto(2L, "test2", "test2@test2", "password", "latitude",
                "longitude", "description", new ArrayList<>());
        VolunteerDto volunteerDto2 = new VolunteerDto(3L, "test3", "test3@test3", "password", "latitude",
                "longitude", "description", new ArrayList<>());

        List<Volunteer> volunteers = new ArrayList<>();
        List<Volunteer> volunteers1;
        List<VolunteerDto> volunteerDtos;
        List<VolunteerDto> volunteerDtos1 = new ArrayList<>();

        //When
        volunteers.add(volunteer);
        volunteers.add(volunteer1);
        volunteers.add(volunteer2);
        volunteerDtos = volunteerMapper.mapToVolunteerDtoList(volunteers);
        volunteerDtos1.add(volunteerDto);
        volunteerDtos1.add(volunteerDto1);
        volunteerDtos1.add(volunteerDto2);
        volunteers1 = volunteerMapper.mapToVolunteerList(volunteerDtos1);

        //Then
        assertEquals(volunteers.size(), volunteerDtos.size());
        assertEquals(volunteer.getId(), volunteerMapper.mapToVolunteerDto(volunteer).getId());
        assertEquals(volunteer.getName(), volunteerMapper.mapToVolunteerDto(volunteer).getName());
        assertEquals(volunteer.getEmail(), volunteerMapper.mapToVolunteerDto(volunteer).getEmail());

        assertEquals(volunteerDto.getId(), volunteerMapper.mapToVolunteer(volunteerDto).getId());
        assertEquals(volunteerDto.getName(), volunteerMapper.mapToVolunteer(volunteerDto).getName());
        assertEquals(volunteerDto.getEmail(), volunteerMapper.mapToVolunteer(volunteerDto).getEmail());

        assertEquals(volunteerDtos1.size(), volunteers1.size());
        assertEquals(volunteerDto.getId(), volunteerMapper.mapToVolunteer(volunteerDto).getId());
    }

}
