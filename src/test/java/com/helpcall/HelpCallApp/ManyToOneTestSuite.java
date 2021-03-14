package com.helpcall.HelpCallApp;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.service.InstitutionDbService;
import com.helpcall.HelpCallApp.service.NeedDbService;
import com.helpcall.HelpCallApp.service.VolunteerDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
public class ManyToOneTestSuite {

    @Mock
    InstitutionDbService institutionDbService;

    @Mock
    NeedDbService needDbService;

    @Mock
    VolunteerDbService volunteerDbService;


    @Test
    void shouldCreateInstitutionWithNeeds() {
        Institution institution = new Institution();
        institution.setId(1L);

        Need need = new Need();
        need.setId(2L);
        need.setInstitution(institution);

        institution.getNeeds().add(need);

        when(institutionDbService.saveInstitution(institution)).thenReturn(institution);
        when(needDbService.saveNeed(need)).thenReturn(need);
        Institution institution1 = institutionDbService.saveInstitution(institution);
        Need need1 = needDbService.saveNeed(need);

        assertEquals(1, institution1.getId());
        assertEquals(2,need1.getId());
        System.out.println(institution1);
        System.out.println(need1);
    }

    @Test
    void shouldCreateVolunteerAndNeeds() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1L);
        Volunteer volunteer1 = new Volunteer();
        volunteer1.setId(2L);
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(volunteer);
        volunteers.add(volunteer1);

        Need need = new Need();
        need.setId(2L);
        need.setVolunteers(volunteers);

        volunteer.getNeeds().add(need);
        volunteer1.getNeeds().add(need);

        when(volunteerDbService.saveVolunteer(volunteer)).thenReturn(volunteer);
        when(volunteerDbService.saveVolunteer(volunteer1)).thenReturn(volunteer1);
        when(needDbService.saveNeed(need)).thenReturn(need);
        Volunteer volunteer2 = volunteerDbService.saveVolunteer(volunteer);
        Volunteer volunteer3 = volunteerDbService.saveVolunteer(volunteer1);
        Need need1 = needDbService.saveNeed(need);

        assertEquals(1, volunteer2.getId());
        assertEquals(2, volunteer3.getId());
        assertEquals(2,need1.getId());
        System.out.println(volunteer2);
        System.out.println(volunteer3);
        System.out.println(need1);

    }
}
