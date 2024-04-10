package pl.cleankod.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void addCorrelationIdToLogs() {
        MDC.put("Correlation-ID", UUID.randomUUID().toString());
    }
}
