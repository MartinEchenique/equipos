package com.echenique.equipos.service;

import com.echenique.equipos.response.TeamInformationResponse;
import com.echenique.equipos.request.TeamDescriptionRequest;
import com.echenique.equipos.exception.DefaultExceptionDescription;
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

    public List<TeamInformationResponse> getAllTeams(){
        return teamRepository.findAll().stream().map(TeamInformationResponse::buildFromTeam).toList();
    }

    public TeamInformationResponse getTeamById(Long id) throws TeamNotFoundException {
        Team teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(DefaultExceptionDescription.builder()
                        .action("GETTING TEAM BY ID")
                        .detail("FOR ID " + id)
                        .build()));
        return TeamInformationResponse.buildFromTeam(teamEntity);
    }

    public List<TeamInformationResponse> getTeamByName(String name) {
        return teamRepository.findByNameIgnoreCaseContaining(name).stream().map(TeamInformationResponse::buildFromTeam).toList();
    }
    public TeamInformationResponse createNewTeam(TeamDescriptionRequest teamInfo) throws InvalidRequestException {
        var team = Team.builder()
                .name(teamInfo.getName())
                .country(teamInfo.getCountry())
                .league(teamInfo.getLeague())
                .build();
        try {
            Team teamEntity = teamRepository.save(team);
            return TeamInformationResponse.buildFromTeam(teamEntity);
        }
        catch(Exception exception){
            throw new InvalidRequestException(DefaultExceptionDescription.builder()
                    .action("CREATING NEW TEAM")
                    .detail("FOR REQUEST " + teamInfo)
                    .build());
        }
    }

    @Transactional
    public TeamInformationResponse updateTeam(TeamDescriptionRequest teamNewInfo, Long id) throws TeamNotFoundException {
        int modifiedTeamsAmount = teamRepository.setTeamInfoById(teamNewInfo, id);
        if(modifiedTeamsAmount == 0){
            throw new TeamNotFoundException(DefaultExceptionDescription.builder()
                .action("UPDATING TEAM")
                .detail("FOR ID " + id)
                .build());
        }

        return TeamInformationResponse.builder()
                .id(id)
                .league(teamNewInfo.getLeague())
                .country(teamNewInfo.getCountry())
                .name(teamNewInfo.getName())
                .build();

    }

    public void deleteTeam(Long id) throws TeamNotFoundException {
        Team teamToDelete = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(DefaultExceptionDescription.builder()
                .action("DELETING TEAM BY ID")
                .detail("FOR ID " + id)
                .build()));
        teamRepository.delete(teamToDelete);
    }
}
