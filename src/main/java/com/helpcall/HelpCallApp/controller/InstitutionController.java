package com.helpcall.HelpCallApp.controller;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.InstitutionDto;
import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.NeedDto;
import com.helpcall.HelpCallApp.exceptions.InstitutionNotFoundException;
import com.helpcall.HelpCallApp.mapper.InstitutionMapper;
import com.helpcall.HelpCallApp.mapper.NeedMapper;
import com.helpcall.HelpCallApp.service.InstitutionDbService;
import com.helpcall.HelpCallApp.service.NeedDbService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private NeedMapper needMapper;
    @Autowired
    private NeedController needController;
    @Autowired
    private NeedDbService needDbService;

    @RequestMapping(method = RequestMethod.POST, value = "/institutions", consumes = APPLICATION_JSON_VALUE)
    public void createInstitution(@RequestBody InstitutionDto institutionDto) {
        institutionDbService.saveInstitution(institutionMapper.mapToInstitutionWriteMapper(institutionDto));
    }

    //  @Secured({"ROLE_INSTITUTION", "ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.PUT, value = "/institutions")
    public InstitutionDto updateInstitution(@RequestBody InstitutionDto institutionDto) {
        System.out.println(institutionDto);
        return institutionMapper.mapToInstitutionDto(institutionDbService.saveInstitution(
                institutionMapper.mapToInstitutionWriteMapper(institutionDto)));
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

    //  @Secured({"ROLE_INSTITUTION"})
    @RequestMapping(method = RequestMethod.PUT, value = "/institutions/addNeed/{id}")
    public void addNeed(@RequestBody NeedDto needDto, @PathVariable("id") Long id) throws InstitutionNotFoundException {
        Institution institution = institutionDbService.getInstitution(id).orElseThrow(InstitutionNotFoundException::new);
        Need need = needMapper.mapToNeedWriteModel(needDto);
        institution.getNeeds().add(need);
        need.setInstitution(institution);

        needDbService.saveNeed(need);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/institutions/needs/{id}")
    public List<NeedDto> getInstitutionsNeeds(@PathVariable("id") Long id) throws InstitutionNotFoundException {
        return institutionMapper.mapToInstitutionDto(institutionDbService.getInstitution(id)
                .orElseThrow(InstitutionNotFoundException::new)).getNeeds();
    }
}
