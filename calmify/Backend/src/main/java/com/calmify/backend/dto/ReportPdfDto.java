package com.calmify.backend.dto;

import com.calmify.backend.entity.MoodEntry;
import java.util.List;

public class ReportPdfDto {

    private String name;
    private String email;
    private int totalEntries;
    private String currentMood;
    private String mostFrequentMood;
    private List<MoodEntry> moodEntries;

    public ReportPdfDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(int totalEntries) {
        this.totalEntries = totalEntries;
    }

    public String getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(String currentMood) {
        this.currentMood = currentMood;
    }

    public String getMostFrequentMood() {
        return mostFrequentMood;
    }

    public void setMostFrequentMood(String mostFrequentMood) {
        this.mostFrequentMood = mostFrequentMood;
    }

    public List<MoodEntry> getMoodEntries() {
        return moodEntries;
    }

    public void setMoodEntries(List<MoodEntry> moodEntries) {
        this.moodEntries = moodEntries;
    }
}
