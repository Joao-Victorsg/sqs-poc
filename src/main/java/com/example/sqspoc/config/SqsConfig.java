package com.example.sqspoc.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.awspring.cloud.sqs.config.SqsListenerConfigurer;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import io.awspring.cloud.sqs.support.converter.MessagingMessageConverter;
import io.awspring.cloud.sqs.support.converter.SqsMessagingMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .findAndRegisterModules()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Bean
    public MappingJackson2MessageConverter mapper(ObjectMapper objectMapper){
        final var payloadConverter = new MappingJackson2MessageConverter();
        payloadConverter.setObjectMapper(objectMapper);
        payloadConverter.setPrettyPrint(true);
        payloadConverter.setSerializedPayloadClass(String.class);
        return payloadConverter;
    }

    @Bean
    public MessagingMessageConverter messageConverter(MappingJackson2MessageConverter payloadConverter){
        final var messageConverter = new SqsMessagingMessageConverter();
        messageConverter.setPayloadMessageConverter(payloadConverter);
        return messageConverter;
    }

    @Bean
    SqsListenerConfigurer configurer(ObjectMapper objectMapper){
        return registrar -> registrar.setObjectMapper(objectMapper);
    }


    @Bean
    public SqsAsyncClient getClient(@Value("${cloud.aws.sqs.endpoint}") String sqsEndpoint, @Value("${cloud.aws.region.static}") String awsRegion){
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(sqsEndpoint))
                .region(Region.of(awsRegion))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Bean
    public SqsMessageListenerContainerFactory sqsMessageListenerContainerFactory(SqsAsyncClient sqsAsyncClient,MessagingMessageConverter messageConverter){
        return SqsMessageListenerContainerFactory.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure(options -> options.messageConverter(messageConverter)
                        .acknowledgementMode(AcknowledgementMode.MANUAL))
                .build();
    }
}
