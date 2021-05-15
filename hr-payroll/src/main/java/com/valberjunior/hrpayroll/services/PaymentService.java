package com.valberjunior.hrpayroll.services;

import com.valberjunior.hrpayroll.entities.Payment;
import com.valberjunior.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(Long workerId, int days) {

        var worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
