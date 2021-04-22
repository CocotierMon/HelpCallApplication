package com.helpcall.HelpCallApp.service;

import com.helpcall.HelpCallApp.domain.VolunteersWall;
import com.helpcall.HelpCallApp.repository.VolunteersNeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteersNeedDbService {

    @Autowired
    private VolunteersNeedRepository needRepository;

    public List<VolunteersWall> getVolunteersNeeds(final Long id) {return needRepository.getVolunteersNeeds(id);}

}
