package com.example.sqspoc.adapter.output.sqs;

import com.example.sqspoc.adapter.entrypoint.sqs.dto.MensagemDto;
import com.example.sqspoc.adapter.entrypoint.sqs.dto.PayloadDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.MessageHeaderUtils;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class Publisher {

    private final ObjectMapper objectMapper;

    private final SqsAsyncClient sqsAsyncClient;

/*    public void send() throws JsonProcessingException {

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
    }  */

    public void send(){
        final var sqsTemplate = SqsTemplate.newAsyncTemplate(sqsAsyncClient);

        final var mensagem = MensagemDto.builder()
                .idMensagem("123")
                .tipoMensagem("teste")
                .payloadDto(PayloadDto.builder()
                                .nome("Vasco")
                                .valor(123L)
                                .cor("Azul")
                                .build())
                .build();

        final var mensagemPronta = MessageBuilder.createMessage(
                mensagem,
                new MessageHeaders(Map.of("correlationId","123","transactionId","123")))    ;

        sqsTemplate.sendAsync("teste",mensagemPronta);
    }

}
