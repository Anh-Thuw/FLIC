package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.entity.Payment;
import com.flic.courseRegister.mapper.admin.PaymentMapper;
import com.flic.courseRegister.repository.PaymentRepository;
import com.flic.courseRegister.service.admin.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAllWithDependencies();
        return payments.stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }
}