package com.calmify.backend.service.impl;

import com.calmify.backend.dto.ReportPdfDto;
import com.calmify.backend.entity.MoodEntry;
import com.calmify.backend.entity.User;
import com.calmify.backend.pdf.PdfGenerator;
import com.calmify.backend.repository.MoodRepository;
import com.calmify.backend.repository.UserRepository;
import com.calmify.backend.service.ReportPdfService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportPdfServiceImpl implements ReportPdfService {

    private final UserRepository userRepository;
    private final MoodRepository moodRepository;
    private final PdfGenerator pdfGenerator;

    public ReportPdfServiceImpl(
            UserRepository userRepository,
            MoodRepository moodRepository,
            PdfGenerator pdfGenerator) {

        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public byte[] generateReport(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<MoodEntry> moodEntries = moodRepository.findByUser(user);

        MoodEntry latestMood =
                moodRepository.findTopByUserOrderByCreatedAtDesc(user);

        String currentMood = latestMood != null
                ? latestMood.getMood()
                : "N/A";

        Map<String, Integer> moodCount = new HashMap<>();

        for (MoodEntry mood : moodEntries) {

            moodCount.put(
                    mood.getMood(),
                    moodCount.getOrDefault(mood.getMood(), 0) + 1
            );

        }

        String mostFrequentMood = "N/A";
        int max = 0;

        for (Map.Entry<String, Integer> entry : moodCount.entrySet()) {

            if (entry.getValue() > max) {

                max = entry.getValue();
                mostFrequentMood = entry.getKey();

            }

        }

        ReportPdfDto report = new ReportPdfDto();

        report.setName(user.getName());
        report.setEmail(user.getEmail());
        report.setTotalEntries(moodEntries.size());
        report.setCurrentMood(currentMood);
        report.setMostFrequentMood(mostFrequentMood);
        report.setMoodEntries(moodEntries);

        return pdfGenerator.generateReport(report);

    }
}