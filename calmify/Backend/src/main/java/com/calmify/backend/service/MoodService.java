package com.calmify.backend.service;

import com.calmify.backend.dto.MoodDto;
import com.calmify.backend.dto.MoodResponseDto;
import com.calmify.backend.dto.ReportResponseDto;

import java.util.List;

public interface MoodService {

    MoodResponseDto saveMood(
            String email,
            MoodDto moodDto
    );

    List<MoodResponseDto> getMyMoods(
            String email
    );

    ReportResponseDto getReport(String email);
    void deleteMood(Long id);
}