package com.helpcall.HelpCallApp.controller;

import com.helpcall.HelpCallApp.domain.InstitutionDto;
import com.helpcall.HelpCallApp.exceptions.InstitutionNotFoundException;
import com.helpcall.HelpCallApp.mapper.InstitutionMapper;
import com.helpcall.HelpCallApp.service.InstitutionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class InstitutionController {

    @Autowired
    private InstitutionDbService institutionDbService;
    @Autowired
    private InstitutionMapper institutionMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/institutions", consumes = APPLICATION_JSON_VALUE)
    public void createInstitution(@RequestBody InstitutionDto institutionDto) {
        institutionDbService.saveInstitution(institutionMapper.mapToInstitution(institutionDto));
    }

  //  @Secured({"ROLE_INSTITUTION", "ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.PUT, value = "/institutions")
    public InstitutionDto updateInstitution(@RequestBody InstitutionDto institutionDto) {
        return institutionMapper.mapToInstitutionDto(institutionDbService.saveInstitution(
                institutionMapper.mapToInstitution(institutionDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/institutions/{id}")
    public InstitutionDto getInstitution(@PathVariable("id") Long id) throws InstitutionNotFoundException {
        return institutionMapper.mapToInstitutionDto(institutionDbService.getInstitution(id)
                .orElseThrow(InstitutionNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/institutions")
    public List<InstitutionDto> getInstitutions() {
        return institutionMapper.mapToInstitutionDtoList(institutionDbService.getAllInstitutions());
    }

 //   @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.DELETE, value = "/institutions/{id}")
    public void deleteInstitutionById(@PathVariable("id") Long id) {
        institutionDbService.deleteInstitutionById(id);
    }
}
