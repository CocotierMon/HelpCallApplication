package com.helpcall.HelpCallApp.repository;

import com.helpcall.HelpCallApp.domain.VolunteersWall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteersNeedRepository extends CrudRepository<VolunteersWall, Long> {
    @Query(nativeQuery = true)
    List<VolunteersWall> getVolunteersNeeds(@Param("id") Long id);
}
