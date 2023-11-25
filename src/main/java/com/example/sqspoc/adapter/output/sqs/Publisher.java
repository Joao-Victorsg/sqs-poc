package com.example.sqspoc.adapter.output.sqs;

import com.example.sqspoc.adapter.entrypoint.sqs.dto.Mensagem;
import com.example.sqspoc.adapter.entrypoint.sqs.dto.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@RequiredArgsConstructor
public class Publisher {

    private final ObjectMapper objectMapper;

    private final SqsAsyncClient sqsAsyncClient;

    public void send() throws JsonProcessingException {

        final var json = objectMapper.writeValueAsString(Mensagem.builder()
                .idMensagem("123")
                .tipoMensagem("teste")
                .payload(Payload.builder()
                        .nome("Vasco")
                        .valor(123L)
                        .cor("Azul")
                        .build())
                .build());


        final var message = SendMessageRequest.builder()
                .messageBody(json)
                .queueUrl("http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/teste")
                .build();

        sqsAsyncClient.sendMessage(message);
    }

}
