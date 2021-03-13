package com.helpcall.HelpCallApp.controller;

import com.helpcall.HelpCallApp.domain.NeedDto;
import com.helpcall.HelpCallApp.exceptions.NeedNotFoundException;
import com.helpcall.HelpCallApp.mapper.NeedMapper;
import com.helpcall.HelpCallApp.service.NeedDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class NeedController {

    @Autowired
    private NeedDbService needDbService;
    @Autowired
    private NeedMapper needMapper;

    // @Secured({"ROLE_INSTITUTION", "ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/needs", consumes = APPLICATION_JSON_VALUE)
    public void createNeed(@RequestBody NeedDto needDto) {
        needDbService.saveNeed(needMapper.mapToNeed(needDto));
    }

   // @Secured({"ROLE_INSTITUTION", "ROLE_ADMIN"})
   @RequestMapping(method = RequestMethod.PUT, value = "/needs")
   public NeedDto updateNeed(@RequestBody NeedDto needDto) {
       return needMapper.mapToNeedDto(needDbService.saveNeed(
               needMapper.mapToNeed(needDto)));
   }

    @RequestMapping(method = RequestMethod.GET, value = "/needs/{id}")
    public NeedDto getNeed(@PathVariable("id") Long id) throws NeedNotFoundException {
        return needMapper.mapToNeedDto(needDbService.getNeed(id)
                .orElseThrow(NeedNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/needs")
    public List<NeedDto> getNeeds() {
        return needMapper.mapToNeedListDto(needDbService.getAllNeeds());
    }

    //@Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.DELETE, value = "/needs/{id}")
    public void deleteNeedById(@PathVariable("id") Long id) {
        needDbService.deleteNeedById(id);
    }
}
