package com.calmify.backend.dto;

public class AiAnalysisResponseDto {

    private String analysis;

    public AiAnalysisResponseDto() {
    }

    public AiAnalysisResponseDto(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
