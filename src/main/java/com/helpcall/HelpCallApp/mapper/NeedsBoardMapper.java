package com.helpcall.HelpCallApp.mapper;

import com.helpcall.HelpCallApp.domain.NeedsBoard;
import com.helpcall.HelpCallApp.domain.NeedsBoardDto;
import org.springframework.stereotype.Component;

@Component
public class NeedsBoardMapper {

    public NeedsBoard mapToNeedsBoard(final NeedsBoardDto needsBoardDto) {
        return new NeedsBoard(needsBoardDto.getId(), needsBoardDto.getNeeds());
    }

    public NeedsBoardDto mapToNeedsBoardsDto(final NeedsBoard needsBoard) {
        return new NeedsBoardDto(needsBoard.getId(), needsBoard.getNeeds());
    }
}
