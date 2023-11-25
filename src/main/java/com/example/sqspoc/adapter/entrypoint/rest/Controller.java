package com.example.sqspoc.adapter.entrypoint.rest;


import com.example.sqspoc.adapter.output.sqs.Publisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final Publisher publisher;

    @PostMapping(value = "/enviarMensagem")
    public void enviarMensagem() throws JsonProcessingException {
        publisher.send();
    }

}
