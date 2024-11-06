package com.echenique.equipos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
public class TeamDescriptionRequest {
        @JsonProperty("nombre")
        @NotNull(message="nombre can not be null")
        private String name;
        @JsonProperty("liga")
        @NotNull(message="liga can not be null")
        private String league;
        @JsonProperty("pais")
        @NotNull(message="pais can not be null")
        private String country;
}
