package com.echenique.equipos.dto;

import com.echenique.equipos.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeamAllInfoDto {
    private Long id;
    @JsonProperty(value = "nombre")
    private String name;
    @JsonProperty(value = "liga")
    private String league;
    @JsonProperty(value = "pais")
    private String country;
    public static TeamAllInfoDto buildFromTeam(Team team){
        return TeamAllInfoDto.builder()
                .name(team.getName())
                .country(team.getCountry())
                .league(team.getLeague())
                .id(team.getId())
                .build();
    }
}
