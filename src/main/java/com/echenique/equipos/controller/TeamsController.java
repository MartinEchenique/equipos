package com.echenique.equipos.controller;

import com.echenique.equipos.dto.TeamAllInfoDto;
import com.echenique.equipos.dto.TeamDescriptionDto;
import com.echenique.equipos.model.Team;
import com.echenique.equipos.repository.TeamRepository;
import com.echenique.equipos.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamsController {
    private final TeamService teamService;
    private final TeamRepository teamRepository;

    @GetMapping(value = "/equipos")
    public ResponseEntity<List<TeamAllInfoDto>> getAllTeams(){
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    @GetMapping(value = "/equipos/{id}")
    public ResponseEntity<TeamAllInfoDto> getTeamById(@PathVariable Long id ){
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    @GetMapping(value = "/equipos/buscar")
    public ResponseEntity<List<TeamAllInfoDto>> getTeamByName(@RequestParam String nombre ){
        return ResponseEntity.ok(teamService.getTeamByName(nombre));
    }
    @PostMapping(value = "/equipos")
    public ResponseEntity<TeamAllInfoDto> createNewTeam(@RequestBody TeamDescriptionDto teamDescriptionDto){
        return ResponseEntity.ok(teamService.createNewTeam(teamDescriptionDto));
    }
    @PutMapping(value = "/equipos/{id}")
    public ResponseEntity<TeamAllInfoDto> updateTeam(@PathVariable Long id, @RequestBody TeamDescriptionDto teamDescriptionDto){
        return ResponseEntity.ok( teamService.updateTeam(teamDescriptionDto, id));
    }
    @DeleteMapping(value = "/equipos/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
