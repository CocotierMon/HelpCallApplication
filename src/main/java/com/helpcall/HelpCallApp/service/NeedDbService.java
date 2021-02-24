package com.helpcall.HelpCallApp.service;

import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.repository.NeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NeedDbService {

    private NeedRepository needRepository;

    @Autowired
    public NeedDbService(NeedRepository needRepository) {
        this.needRepository = needRepository;
    }

    public Need saveNeed(final Need need) {
        return needRepository.save(need);
    }

    public Optional<Need> getNeed(final Long id) {
        return needRepository.findById(id);
    }

    public List<Need> getAllNeeds() {
        return needRepository.findAll();
    }

    public void deleteNeedById(final Long id) {
        needRepository.deleteById(id);
    }
}
