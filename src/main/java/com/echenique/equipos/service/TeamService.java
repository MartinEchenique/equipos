package com.echenique.equipos.service;

import com.echenique.equipos.dto.TeamAllInfoDto;
import com.echenique.equipos.dto.TeamDescriptionDto;
import com.echenique.equipos.exception.InvalidRequestException;
import com.echenique.equipos.exception.TeamNotFoundException;
import com.echenique.equipos.model.Team;
import com.echenique.equipos.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    public List<TeamAllInfoDto> getAllTeams(){
        return teamRepository.findAll().stream().map(TeamAllInfoDto::buildFromTeam).toList();
    }
    public TeamAllInfoDto getTeamById(Long id) throws TeamNotFoundException {
        Team teamEntity = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
        return TeamAllInfoDto.buildFromTeam(teamEntity);
    }
    public List<TeamAllInfoDto> getTeamByName(String name) {
        return teamRepository.findByNameIgnoreCaseContaining(name).stream().map(TeamAllInfoDto::buildFromTeam).toList();
    }
    public TeamAllInfoDto createNewTeam(TeamDescriptionDto teamInfo) throws InvalidRequestException {
        var team = Team.builder()
                .name(teamInfo.getName())
                .country(teamInfo.getCountry())
                .league(teamInfo.getLeague())
                .build();
        try {
            Team teamEntity = teamRepository.save(team);
            return TeamAllInfoDto.buildFromTeam(teamEntity);
        }
        catch(Exception exception){
            throw new InvalidRequestException();
        }
    }
    @Transactional
    public TeamAllInfoDto updateTeam(TeamDescriptionDto teamNewInfo, Long id) throws TeamNotFoundException {
        int modifiedTeamsAmount = teamRepository.setTeamInfoById(teamNewInfo, id);
        if(modifiedTeamsAmount == 0)throw new TeamNotFoundException();

        return TeamAllInfoDto.builder()
                .id(id)
                .league(teamNewInfo.getLeague())
                .country(teamNewInfo.getCountry())
                .name(teamNewInfo.getName())
                .build();

    }
    public void deleteTeam(Long id) throws TeamNotFoundException {
        Team teamToDelete = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
        teamRepository.delete(teamToDelete);
    }
}
