package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.LessonTimetableDTO;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.mapper.user.TimetableMapper;
import com.flic.courseRegister.repository.LessonRepository;
import com.flic.courseRegister.service.user.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final LessonRepository lessonRepository;
    private final TimetableMapper timetableMapper;

    @Override
    public List<LessonTimetableDTO> getTimetable() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = authentication.getPrincipal();
        Long userId = ((com.flic.courseRegister.security.UserDetailsImpl) principal).getUser().getId();

        return lessonRepository.findLessonsByUserId(userId)
                .stream()
                .map(timetableMapper::toDto)
                .collect(Collectors.toList());
    }
}


