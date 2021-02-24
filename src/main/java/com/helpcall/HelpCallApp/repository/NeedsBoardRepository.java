package com.helpcall.HelpCallApp.repository;

import com.helpcall.HelpCallApp.domain.NeedsBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NeedsBoardRepository extends CrudRepository<NeedsBoard, Long> {

    @Override
    NeedsBoard save(NeedsBoard needsBoard);

    @Override
    Optional<NeedsBoard> findById(Long id);

    @Override
    void deleteById(Long id);
}
