package com.helpcall.HelpCallApp.repository;

import com.helpcall.HelpCallApp.domain.Volunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

    @Override
    Volunteer save(Volunteer volunteer);

    @Override
    Optional<Volunteer> findById(Long id);

    @Override
    List<Volunteer> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long id);
}
