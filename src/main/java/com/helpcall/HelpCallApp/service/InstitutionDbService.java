package com.helpcall.HelpCallApp.service;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionDbService {

    @Autowired
    private InstitutionRepository institutionRepository;

    public Institution saveInstitution(final Institution institution) {
        return institutionRepository.save(institution);
    }

    public Optional<Institution> getInstitution(final Long id) {
        return institutionRepository.findById(id);
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public void deleteInstitutionById(final Long id) {
         institutionRepository.deleteById(id);
    }
}
