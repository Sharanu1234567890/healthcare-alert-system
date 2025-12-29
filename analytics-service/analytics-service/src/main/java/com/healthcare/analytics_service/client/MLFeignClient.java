package com.healthcare.analytics_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "python-ml", url = "${ml.service.url:http://python-ml-service:5000}")
public interface MLFeignClient {
    @PostMapping("/predict")
    Map<String, Object> predict(@RequestBody Map<String, Object> vitals);
}