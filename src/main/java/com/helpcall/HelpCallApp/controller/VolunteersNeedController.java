package com.helpcall.HelpCallApp.controller;

import com.helpcall.HelpCallApp.domain.VolunteersWallDto;
import com.helpcall.HelpCallApp.mapper.VolunteersWallMapper;
import com.helpcall.HelpCallApp.service.VolunteersNeedDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class VolunteersNeedController {

    @Autowired
    VolunteersNeedDbService volunteersNeedDbService;
    @Autowired
    VolunteersWallMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/wall/{id}")
    public @ResponseBody List<VolunteersWallDto> getVolunteersNeeds(@PathVariable("id") Long id) {
        return mapper.mapToVnListDto(volunteersNeedDbService.getVolunteersNeeds(id));
    }
}
