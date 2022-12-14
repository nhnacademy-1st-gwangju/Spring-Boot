package com.nhnacademy.edu.springboot.student.configuration.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private final AtomicBoolean up = new AtomicBoolean(true);

    public void setUp(boolean up) {
        this.up.set(up);
    }

    @Override
    public Health health() {
        if (this.up.get()) {
            return Health.up().build();
        }
        return Health.down()
                .withDetails(Map.of("Custom", "failed"))
                .build();
    }
}
