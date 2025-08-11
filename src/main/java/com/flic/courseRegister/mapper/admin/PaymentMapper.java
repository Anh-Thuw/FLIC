package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "enrolmentId", target = "enrollmentId")
    PaymentDTO toDto(Payment payment);
}
