package com.hatio.todoapplication.CustomAnnotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;


@Aspect
@Component
public class HttpLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpLoggerAspect.class);

    @Before("@annotation(HttpLogger)")
    public void logRequestDetails(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // Log request details
            logger.info("Request URL: {}", request.getRequestURL());
            logger.info("HTTP Method: {}", request.getMethod());
            logger.info("IP Address: {}", request.getRemoteAddr());

            // Log headers
            logger.info("Headers:");
            java.util.Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                logger.info("{} : {}", headerName, request.getHeader(headerName));
            }

            // Log request parameters
            logger.info("Request Parameters:");
            java.util.Map<String, String[]> paramMap = request.getParameterMap();
            for (String paramName : paramMap.keySet()) {
                String[] paramValues = paramMap.get(paramName);
                logger.info("{} : {}", paramName, java.util.Arrays.toString(paramValues));
            }
        } else {
            logger.warn("Unable to retrieve HttpServletRequest for logging.");
        }
    }
}
