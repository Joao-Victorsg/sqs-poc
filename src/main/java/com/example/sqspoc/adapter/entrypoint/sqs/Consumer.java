package com.example.sqspoc.adapter.entrypoint.sqs;

import com.example.sqspoc.adapter.entrypoint.sqs.dto.Mensagem;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @SqsListener(queueNames = "teste",factory = "sqsMessageListenerContainerFactory")
    public void sqsListener(Mensagem mensagem) {
        System.out.println(mensagem);
    }
}