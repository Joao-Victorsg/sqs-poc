package com.example.sqspoc.adapter.entrypoint.sqs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;

@Builder
@JsonDeserialize(builder = PayloadDto.PayloadDtoBuilder.class)
public record PayloadDto(
        @JsonProperty
        String nome,
        @JsonProperty
        Long valor,
        @JsonProperty
        String cor
    ) {
}
