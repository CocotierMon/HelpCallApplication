package com.helpcall.HelpCallApp.controller;

import com.helpcall.HelpCallApp.domain.VolunteerDto;
import com.helpcall.HelpCallApp.exceptions.VolunteerNotFoundException;
import com.helpcall.HelpCallApp.mapper.VolunteerMapper;
import com.helpcall.HelpCallApp.service.VolunteerDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class VolunteerController {

    @Autowired
    private VolunteerDbService volunteerDbService;
    @Autowired
    private VolunteerMapper volunteerMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/volunteers", consumes = APPLICATION_JSON_VALUE)
    public void createVolunteer(@RequestBody VolunteerDto volunteerDto) {
        volunteerDbService.saveVolunteer(volunteerMapper.mapToVolunteer(volunteerDto));
    }

 //   @Secured({"ROLE_VOLUNTEER", "ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.PUT, value = "/volunteers")
    public VolunteerDto updateVolunteer(@RequestBody VolunteerDto volunteerDto) {
        return volunteerMapper.mapToVolunteerDto(volunteerDbService.saveVolunteer(
                volunteerMapper.mapToVolunteer(volunteerDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/volunteers/{id}")
    public @ResponseBody VolunteerDto getVolunteer(@PathVariable("id") Long id) throws VolunteerNotFoundException {
        return volunteerMapper.mapToVolunteerDto(volunteerDbService.getVolunteer(id)
                .orElseThrow(VolunteerNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/volunteers")
    public @ResponseBody List<VolunteerDto> getVolunteers() {
        return volunteerMapper.mapToVolunteerDtoList(volunteerDbService.getAllVolunteers());
    }

   // @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.DELETE, value = "/volunteers/{id}")
    public void deleteVolunteerById(@PathVariable("id") Long id) {
        volunteerDbService.deleteVolunteerById(id);
    }
}
