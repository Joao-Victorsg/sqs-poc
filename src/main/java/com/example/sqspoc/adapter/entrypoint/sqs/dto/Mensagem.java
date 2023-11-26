package com.example.sqspoc.adapter.entrypoint.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonDeserialize(builder = Mensagem.MensagemBuilder.class)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Mensagem (

        @JsonProperty
        String idMensagem,
        @JsonProperty
        String tipoMensagem,
        @JsonProperty
        Payload payload
){
}
