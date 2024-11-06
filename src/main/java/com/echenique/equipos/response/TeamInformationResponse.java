package com.echenique.equipos.response;

import com.echenique.equipos.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
public class TeamInformationResponse {
    @NotBlank
    private Long id;
    @NotBlank
    @JsonProperty(value = "nombre")
    private String name;
    @NotBlank
    @JsonProperty(value = "liga")
    private String league;
    @NotBlank
    @JsonProperty(value = "pais")
    private String country;
    public static TeamInformationResponse buildFromTeam(Team team){
        return TeamInformationResponse.builder()
                .name(team.getName())
                .country(team.getCountry())
                .league(team.getLeague())
                .id(team.getId())
                .build();
    }
}
