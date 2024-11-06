package com.echenique.equipos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DefaultExceptionResponse {
    @JsonProperty(value = "mensaje")
    private String message;
    @JsonProperty(value = "codigo")
    private Integer code;
}
