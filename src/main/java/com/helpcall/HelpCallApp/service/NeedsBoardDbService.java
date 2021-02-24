package com.helpcall.HelpCallApp.service;

import com.helpcall.HelpCallApp.domain.NeedsBoard;
import com.helpcall.HelpCallApp.repository.NeedsBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NeedsBoardDbService {

    private NeedsBoardRepository needsBoardRepository;

    @Autowired
    public NeedsBoardDbService(NeedsBoardRepository needsBoardRepository) {
        this.needsBoardRepository = needsBoardRepository;
    }

    public NeedsBoard saveNeedsBoard(final NeedsBoard needsBoard) {
        return needsBoardRepository.save(needsBoard);
    }

    public Optional<NeedsBoard> getNeedsBoard(final Long id) {
        return needsBoardRepository.findById(id);
    }

    public void deleteNeedsBoardsById(final Long id) {
        needsBoardRepository.deleteById(id);
    }
}
