package ch.sbb.cloud.autoscaler.usecase;

import ch.sbb.cloud.autoscaler.service.HttpMetricsPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
class MetricsMonitor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MetricsMonitor.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private HttpMetricsPublisher httpMetricsPublisher;

    @PostConstruct
    public void init() {
        httpMetricsPublisher = new HttpMetricsPublisher("usecase", "frontendservice", rabbitTemplate);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        httpMetricsPublisher.registerServiceEntry(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        httpMetricsPublisher.regsterServiceExit(request);
    }

    @Scheduled(fixedRate = 5000)
    public void reportHttpMetrics() {
        LOG.info("Timer expired for HTTP metrics. Calling method to report metrics.");
        httpMetricsPublisher.publishAvgServerSideResponseTimeAndClearMetrics();
        httpMetricsPublisher.publishNumberOfConnections();
    }
}