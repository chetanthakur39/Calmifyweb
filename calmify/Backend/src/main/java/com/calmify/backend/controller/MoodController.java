package com.calmify.backend.controller;

import com.calmify.backend.dto.MoodDto;
import com.calmify.backend.dto.MoodResponseDto;
import com.calmify.backend.service.MoodService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.calmify.backend.dto.ReportResponseDto;
import java.util.List;

@RestController
public class MoodController {

    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping("/api/moods")
    public MoodResponseDto saveMood(
            Authentication authentication,
            @RequestBody MoodDto moodDto) {

        String email = authentication.getName();

        return moodService.saveMood(
                email,
                moodDto
        );
    }

    @GetMapping("/api/moods")
    public List<MoodResponseDto> getMyMoods(
            Authentication authentication) {

        String email = authentication.getName();

        return moodService.getMyMoods(email);
    }

    @GetMapping("/api/moods/report")
public ReportResponseDto getReport(
        Authentication authentication) {

    String email = authentication.getName();

    return moodService.getReport(email);
}
@DeleteMapping("/api/moods/{id}")
public ResponseEntity<String> deleteMood(
        @PathVariable Long id) {

    moodService.deleteMood(id);

    return ResponseEntity.ok(
            "Mood Deleted Successfully");
}
}