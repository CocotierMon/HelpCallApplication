package com.helpcall.HelpCallApp.controller;

import com.helpcall.HelpCallApp.domain.NeedsBoardDto;
import com.helpcall.HelpCallApp.exceptions.NeedBoardNotFoundException;
import com.helpcall.HelpCallApp.mapper.NeedsBoardMapper;
import com.helpcall.HelpCallApp.service.NeedsBoardDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class NeedsBoardController {

    @Autowired
    private NeedsBoardDbService needsBoardDbService;
    @Autowired
    private NeedsBoardMapper needsBoardMapper;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/needsBoards", consumes = APPLICATION_JSON_VALUE)
    public void createNeedsBoard(@RequestBody NeedsBoardDto needsBoardDto) {
        needsBoardDbService.saveNeedsBoard(needsBoardMapper.mapToNeedsBoard(needsBoardDto));
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.PUT, value = "/needsBoards")
    public NeedsBoardDto updateNeedsBoard(@RequestBody NeedsBoardDto needsBoardDto) {
        return needsBoardMapper.mapToNeedsBoardsDto(needsBoardDbService.saveNeedsBoard(
                needsBoardMapper.mapToNeedsBoard(needsBoardDto)));
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "/needsBoards/{id}")
    public @ResponseBody NeedsBoardDto getNeedsBoard(@PathVariable("id") Long id) throws NeedBoardNotFoundException {
        return needsBoardMapper.mapToNeedsBoardsDto(needsBoardDbService.getNeedsBoard(id)
                .orElseThrow(NeedBoardNotFoundException::new));
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.DELETE, value = "/needsBoards/{id}")
    public void deleteNeedsBoardById(@PathVariable("id") Long id) {
        needsBoardDbService.deleteNeedsBoardsById(id);
    }
}
