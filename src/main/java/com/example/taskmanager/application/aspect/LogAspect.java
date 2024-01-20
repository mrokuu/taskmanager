package com.example.taskmanager.application.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

@Aspect
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    private final String SERVICE_POINTCUT = "execution(* app.service.*.*(..))";

    @Around(SERVICE_POINTCUT)
    public Object logAdvice(ProceedingJoinPoint jp) throws Throwable {

        LOG.info("[METHOD] --------> {}", jp.getSignature().toShortString());

        Object[] signatureArgs = jp.getArgs();
        for (Object signatureArg : signatureArgs) {
            LOG.info("[ARGS] --------> {}: {}", signatureArg.getClass().getSimpleName(), signatureArg.toString());
        }

        Instant startTime = Instant.now();
        Object obj = jp.proceed();
        Instant endTime = Instant.now();

        LOG.info("[METRICS] --------------------> {}, time: {} {} ", jp.getSignature().toShortString(),
                Duration.between(startTime, endTime).getSeconds(), "sec.");

        return obj;
    }
}
