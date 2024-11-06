package com.echenique.equipos.repository;

import com.echenique.equipos.request.TeamDescriptionRequest;
import com.echenique.equipos.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByNameIgnoreCaseContaining(String name);
    @Modifying
    @Query("update Team t set t.name = :#{#team.name}, t.league = :#{#team.league}, t.country = :#{#team.country} where t.id = :#{#id}")
    int setTeamInfoById(@Param("team") TeamDescriptionRequest team, @Param("id") Long id);
}
