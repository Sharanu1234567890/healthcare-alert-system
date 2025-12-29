package xcom.healthcare.notification.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue criticalQueue() {
        return new Queue("critical-alerts", true);
    }
}