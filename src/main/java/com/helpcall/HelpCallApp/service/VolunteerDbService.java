package com.helpcall.HelpCallApp.service;

import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerDbService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer saveVolunteer(final Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Optional<Volunteer> getVolunteer(final Long id) {
        return volunteerRepository.findById(id);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public void deleteVolunteerById(final Long id) {
        volunteerRepository.deleteById(id);
    }
}
