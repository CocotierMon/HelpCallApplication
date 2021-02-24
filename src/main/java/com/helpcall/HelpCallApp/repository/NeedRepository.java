package com.helpcall.HelpCallApp.repository;

import com.helpcall.HelpCallApp.domain.Need;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeedRepository extends CrudRepository <Need, Long> {

    @Override
    Need save(Need need);

    @Override
    Optional<Need> findById(Long id);

    @Override
    List<Need> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long id);
}
