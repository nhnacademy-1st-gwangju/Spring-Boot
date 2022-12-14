package com.nhnacademy.edu.springboot.student.configuration.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Endpoint(id = "counter")
public class CountEndpoint {
    private final AtomicLong counter = new AtomicLong();

    @ReadOperation
    public Long read() {
        return counter.get();
    }

    @WriteOperation
    public Long increment(@Nullable Long delta) {
        if (delta == null) {
            return counter.incrementAndGet();
        }

        return counter.addAndGet(delta);
    }

    @DeleteOperation
    public Long reset() {
        counter.set(0);
        return counter.get();
    }
}
