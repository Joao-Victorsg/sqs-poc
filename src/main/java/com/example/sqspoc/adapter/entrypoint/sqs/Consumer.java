package com.example.sqspoc.adapter.entrypoint.sqs;

import com.example.sqspoc.adapter.entrypoint.sqs.dto.MensagemDto;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @SqsListener(queueNames = "teste",factory = "sqsMessageListenerContainerFactory")
    public void sqsListener(MensagemDto mensagemDto, @Headers MessageHeaders headers, Acknowledgement ack) {
        System.out.println(mensagemDto);
        System.out.println(headers);
        ack.acknowledge();
    }
}