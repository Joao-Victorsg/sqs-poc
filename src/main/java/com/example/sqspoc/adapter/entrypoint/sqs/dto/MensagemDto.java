package com.example.sqspoc.adapter.entrypoint.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonDeserialize(builder = MensagemDto.MensagemDtoBuilder.class)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MensagemDto(

        @JsonProperty
        String idMensagem,
        @JsonProperty
        String tipoMensagem,
        @JsonProperty
        PayloadDto payloadDto
){
}
