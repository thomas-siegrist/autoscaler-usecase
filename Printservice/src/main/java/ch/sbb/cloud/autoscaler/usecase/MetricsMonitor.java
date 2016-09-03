package ch.sbb.cloud.autoscaler.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.RabbitMetricsPublisher;

/**
 * Created by micic on 03.09.16.
 */
@Component
public class MetricsMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(MetricsMonitor.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 5000)
    public void reportRabbitMqMetrics() {
        LOG.info("Timer expired for RabbitMQ metrics. Calling method to report metrics.");

        RabbitMetricsPublisher metricsProvider = new RabbitMetricsPublisher(
                "usecase",
                "printservice",
                rabbitTemplate
        );

        metricsProvider.publishMQDepth("print-queue");
    }
}
