package com.calmify.backend.service.impl;

import com.calmify.backend.dto.MoodDto;
import com.calmify.backend.dto.MoodResponseDto;
import com.calmify.backend.entity.MoodEntry;
import com.calmify.backend.entity.User;
import com.calmify.backend.repository.MoodRepository;
import com.calmify.backend.repository.UserRepository;
import com.calmify.backend.service.MoodService;
import org.springframework.stereotype.Service;
import com.calmify.backend.dto.ReportResponseDto;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;
    private final UserRepository userRepository;

    public MoodServiceImpl(
            MoodRepository moodRepository,
            UserRepository userRepository) {

        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
    }

    @Override
public MoodResponseDto saveMood(
        String email,
        MoodDto moodDto) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    MoodEntry moodEntry = new MoodEntry();

    moodEntry.setMood(moodDto.getMood());
    moodEntry.setNote(moodDto.getNote());
    moodEntry.setCreatedAt(java.time.LocalDateTime.now());
    moodEntry.setUser(user);

    MoodEntry savedMood = moodRepository.save(moodEntry);

    MoodResponseDto response = new MoodResponseDto();

    response.setId(savedMood.getId());
    response.setMood(savedMood.getMood());
    response.setNote(savedMood.getNote());
    response.setCreatedAt(savedMood.getCreatedAt());

    return response;
}

   @Override
public List<MoodResponseDto> getMyMoods(
        String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    List<MoodEntry> moods =
            moodRepository.findByUser(user);

    return moods.stream().map(mood -> {

        MoodResponseDto response =
                new MoodResponseDto();

        response.setId(mood.getId());
        response.setMood(mood.getMood());
        response.setNote(mood.getNote());
        response.setCreatedAt(mood.getCreatedAt());

        return response;

    }).toList();
}
@Override
public ReportResponseDto getReport(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    long totalEntries = moodRepository
            .findByUser(user)
            .size();

    MoodEntry latestMood = moodRepository
            .findTopByUserOrderByCreatedAtDesc(user);

    ReportResponseDto response =
            new ReportResponseDto();

    response.setTotalEntries(totalEntries);

    if (latestMood != null) {
        response.setLatestMood(
                latestMood.getMood()
        );
    } else {
        response.setLatestMood("No Mood Found");
    }

    return response;

}

@Override
public void deleteMood(Long id) {

    moodRepository.deleteById(id);

}
}
