package com.echenique.equipos.controller;

import com.echenique.equipos.dto.TeamAllInfoDto;
import com.echenique.equipos.dto.TeamDescriptionDto;
import com.echenique.equipos.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class TeamsController {
    private final TeamService teamService;
    @GetMapping()
    public ResponseEntity<List<TeamAllInfoDto>> getAllTeams(){
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamAllInfoDto> getTeamById(@PathVariable Long id ){
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    @GetMapping(value = "/buscar")
    public ResponseEntity<List<TeamAllInfoDto>> getTeamByName(@RequestParam String nombre ){
        return ResponseEntity.ok(teamService.getTeamByName(nombre));
    }
    @PostMapping
    public ResponseEntity<TeamAllInfoDto> createNewTeam(@RequestBody TeamDescriptionDto teamDescriptionDto){
        return ResponseEntity.ok(teamService.createNewTeam(teamDescriptionDto));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<TeamAllInfoDto> updateTeam(@PathVariable Long id, @RequestBody TeamDescriptionDto teamDescriptionDto){
        return ResponseEntity.ok( teamService.updateTeam(teamDescriptionDto, id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
