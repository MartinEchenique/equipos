package com.echenique.equipos.service;

import com.echenique.equipos.exception.InvalidRequestException;
import com.echenique.equipos.exception.TeamNotFoundException;
import com.echenique.equipos.model.Team;
import com.echenique.equipos.repository.TeamRepository;
import com.echenique.equipos.request.TeamDescriptionRequest;
import com.echenique.equipos.response.TeamInformationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
    @Mock
    private TeamRepository teamRepository;
    @InjectMocks
    private TeamService teamService;
    private final Long teamId1 = 1L;
    private final Long teamId2 = 1L;

    private final Team okTeamBelgrano = Team.builder().id(teamId1)
            .name("Belgrano").league("AFA").country("Argentina").build();
    private final Team okTeamBoca = Team.builder().id(teamId2)
            .name("Boca").league("AFA").country("Argentina").build();
    private final TeamInformationResponse okTeamInformationBelgrano = TeamInformationResponse.builder()
            .id(teamId1)
            .name("Belgrano")
            .league("AFA")
            .country("Argentina")
            .build();
    private final TeamInformationResponse okTeamInformationBoca = TeamInformationResponse.builder()
            .id(teamId2)
            .name("Boca")
            .league("AFA")
            .country("Argentina")
            .build();
    private final TeamDescriptionRequest teamDescriptionRequest = TeamDescriptionRequest.builder()
            .name("Boca")
            .league("AFA")
            .country("Argentina")
            .build();


    private List<Team> listOfTeams = List.of(okTeamBelgrano, okTeamBoca);
    private List<TeamInformationResponse> listOfTeamsInformatio = List.of(okTeamInformationBelgrano, okTeamInformationBoca);
    @Test
    void whenGetAllTeamsIsOk(){
        when(teamRepository.findAll()).thenReturn(listOfTeams);
        List<TeamInformationResponse> response = teamService.getAllTeams();
        Assertions.assertEquals(listOfTeamsInformatio, response);
    }
    @Test
    void whenGetTeamByIdIsOk(){
        when(teamRepository.findById(okTeamBelgrano.getId())).thenReturn(Optional.of(okTeamBelgrano));
        TeamInformationResponse response = teamService.getTeamById(okTeamBelgrano.getId());
        Assertions.assertEquals(okTeamInformationBelgrano, response);
    }
    @Test
    void whenGetTeamByIdEmptyResponse(){
        when(teamRepository.findById(okTeamBelgrano.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(TeamNotFoundException.class, () -> teamService.getTeamById(teamId1));
    }
    @Test
    void whenGetTeamByNameIsOk(){
        when(teamRepository.findByNameIgnoreCaseContaining("B")).thenReturn(listOfTeams);
        List<TeamInformationResponse> response = teamService.getTeamByName("B");
        Assertions.assertEquals(listOfTeamsInformatio, response);
    }
    @Test
    void whenCreateNewTeamIsOk(){
        when(teamRepository.save(any())).thenReturn(okTeamBoca);
        TeamInformationResponse response = teamService.createNewTeam(teamDescriptionRequest);
        Assertions.assertEquals(okTeamInformationBoca, response);
    }
    @Test
    void whenCreateNewTeamThrows(){
        when(teamRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(InvalidRequestException.class, () -> teamService.createNewTeam(teamDescriptionRequest));
    }
    @Test
    void wheUpdateTeamIsOk(){
        when(teamRepository.setTeamInfoById(teamDescriptionRequest, teamId2)).thenReturn(1);
        TeamInformationResponse response = teamService.updateTeam(teamDescriptionRequest,teamId2);
        Assertions.assertEquals(okTeamInformationBoca, response);
    }
    @Test
    void wheUpdateTeamNotFound(){
        when(teamRepository.setTeamInfoById(teamDescriptionRequest, teamId2)).thenReturn(0);
        Assertions.assertThrows(TeamNotFoundException.class, () -> teamService.updateTeam(teamDescriptionRequest,teamId2));
    }
    @Test
    void whenDeleteTeamNotFound(){
        when(teamRepository.findById(teamId2)).thenReturn(Optional.empty());
        Assertions.assertThrows(TeamNotFoundException.class, () -> teamService.deleteTeam(teamId2));

    }
}