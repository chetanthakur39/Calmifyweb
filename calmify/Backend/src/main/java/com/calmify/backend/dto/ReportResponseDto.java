package com.calmify.backend.dto;

public class ReportResponseDto {

    private long totalEntries;
    private String latestMood;

    public ReportResponseDto() {
    }

    public long getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(long totalEntries) {
        this.totalEntries = totalEntries;
    }

    public String getLatestMood() {
        return latestMood;
    }

    public void setLatestMood(String latestMood) {
        this.latestMood = latestMood;
    }
}