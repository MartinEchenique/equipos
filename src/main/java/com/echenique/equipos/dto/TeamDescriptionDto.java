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
public class TeamDescriptionDto {

        @JsonProperty("nombre")
        private String name;
        @JsonProperty("liga")
        private String league;
        @JsonProperty("pais")
        private String country;
        public static TeamDescriptionDto buildFromTeam(Team team){
                return TeamDescriptionDto.builder()
                        .name(team.getName())
                        .country(team.getCountry())
                        .league(team.getLeague())
                        .build();
        }

}
