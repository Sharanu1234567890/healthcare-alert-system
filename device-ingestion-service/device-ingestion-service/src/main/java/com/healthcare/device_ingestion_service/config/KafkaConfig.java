//package com.healthcare.device_ingestion_service.config;//package com.healthcare.ingestion.config;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.*;
//import org.springframework.kafka.core.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConfig {
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory(@Value("${spring.kafka.bootstrap-servers}") String bootstrap) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> pf) {
//        return new KafkaTemplate<>(pf);
//    }
//}