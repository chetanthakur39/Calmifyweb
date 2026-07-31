package com.calmify.backend.dto;

public class MoodDto {

    private String mood;
    private String note;

    public MoodDto() {
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
