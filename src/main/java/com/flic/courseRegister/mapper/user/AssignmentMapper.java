package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;
import com.flic.courseRegister.entity.Assignment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {
    AssignmentViewDTO toViewDto(Assignment assignment);
    List<AssignmentViewDTO> toViewDtoList(List<Assignment> assignments);
}