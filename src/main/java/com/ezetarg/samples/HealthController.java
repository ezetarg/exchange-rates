package com.ezetarg.samples;

import org.slf4j.Logger;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(HealthController.class);

    private final ApplicationEventPublisher eventPublisher;

    public HealthController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/complete-normally")
    public String completeNormally() {
        return "Hello from Controller " + Math.random();
    }

    @GetMapping("/i-will-sleep-for-30sec")
    public String destroy() throws Exception {
        logger.info("------------------ Sleeping for 30 sec");
        Thread.sleep(30000);
        return "sleep complete";
    }

    @GetMapping("/readiness/accepting")
    public String markReadinesAcceptingTraffic() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        return "Readiness marked as ACCEPTING_TRAFFIC";
    }

    @GetMapping("/readiness/refuse")
    public String markReadinesRefusingTraffic() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC);
        return "Readiness marked as REFUSING_TRAFFIC";
    }

    @GetMapping("/liveness/correct")
    public String markLivenessCorrect() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.CORRECT);
        return "Liveness marked as CORRECT";
    }

    @GetMapping("/liveness/broken")
    public String markLivenessBroken() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.BROKEN);
        return "Liveness marked as BROKEN";
    }
}