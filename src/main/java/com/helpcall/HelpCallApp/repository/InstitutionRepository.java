package com.helpcall.HelpCallApp.repository;

import com.helpcall.HelpCallApp.domain.Institution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {

    @Override
    Institution save(Institution institution);

    @Override
    Optional<Institution> findById(Long id);

    @Override
    List<Institution> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long id);
}
