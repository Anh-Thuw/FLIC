package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.ScheduleViewDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleViewDTO> getSchedule(String email);
}
