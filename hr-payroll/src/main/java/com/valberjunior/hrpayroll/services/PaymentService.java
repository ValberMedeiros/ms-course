package com.valberjunior.hrpayroll.services;

import com.valberjunior.hrpayroll.entities.Payment;
import com.valberjunior.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    private RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment getPayment(Long workerId, int days) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", ""+workerId);

        var worker = restTemplate
                .getForObject(
                        workerHost + "/workers/{id}",
                        Worker.class,
                        uriVariables
                );
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
