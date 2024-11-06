package com.echenique.equipos.controller;

import com.echenique.equipos.dto.TeamAllInfoDto;
import com.echenique.equipos.dto.TeamDescriptionDto;
import com.echenique.equipos.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class TeamsController {
    private final TeamService teamService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamsController.class);

    @Operation(summary = "Devuelve la lista de todos los equipos de fútbol registrados. ")
    @GetMapping()
    public ResponseEntity<List<TeamAllInfoDto>> getAllTeams(){
        LOGGER.info("GETTING ALL TEAMS");
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    @Operation(summary = "Devuelve la información del equipo correspondiente al ID proporcionado. ")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamAllInfoDto> getTeamById(@PathVariable Long id ){
        LOGGER.info("GETTING TEAM WITH ID {}" , id);
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    @Operation(summary = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda. ")
    @GetMapping(value = "/buscar")
    public ResponseEntity<List<TeamAllInfoDto>> getTeamByName(@RequestParam String nombre ){
        LOGGER.info("GETTING TEAM WITH NAME CONTAINING {}" , nombre);
        return ResponseEntity.ok(teamService.getTeamByName(nombre));
    }
    @Operation(summary = "Permite regitrar un nuevo equipo.")
    @PostMapping
    public ResponseEntity<TeamAllInfoDto> createNewTeam(@RequestBody TeamDescriptionDto teamDescriptionDto){
        LOGGER.info("CREATING NEW TEAM REQUEST: {}" , teamDescriptionDto);

        return ResponseEntity.ok(teamService.createNewTeam(teamDescriptionDto));
    }
    @Operation(summary = "Permite actualizar la informacion de un equipo existente.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TeamAllInfoDto> updateTeam(@PathVariable Long id, @RequestBody TeamDescriptionDto teamDescriptionDto){
        LOGGER.info("UPDATING TEAM REQUEST: {}" , teamDescriptionDto);
        return ResponseEntity.ok( teamService.updateTeam(teamDescriptionDto, id));
    }
    @Operation(summary = "Elimina el equipo con el ID proporcionado ")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        LOGGER.info("DELETING TEAM WITH ID: {}" , id);

        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
