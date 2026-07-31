package com.calmify.backend.service.impl;

import com.calmify.backend.client.HuggingFaceClient;
import com.calmify.backend.dto.AiAnalysisRequestDto;
import com.calmify.backend.dto.AiAnalysisResponseDto;
import com.calmify.backend.service.AiAnalysisService;
import org.springframework.stereotype.Service;
/* 
@Service
public class AiAnalysisServiceImpl implements AiAnalysisService {

    private final HuggingFaceClient huggingFaceClient;

    public AiAnalysisServiceImpl(HuggingFaceClient huggingFaceClient) {
        this.huggingFaceClient = huggingFaceClient;
    }

    @Override
    public AiAnalysisResponseDto analyzeMood(
            AiAnalysisRequestDto requestDto) {

        String result = huggingFaceClient.analyzeMood(
                requestDto.getMood(),
                requestDto.getNote()
        );

        return new AiAnalysisResponseDto(result);
    }
}
    */

@Service
public class AiAnalysisServiceImpl implements AiAnalysisService {

    private final HuggingFaceClient huggingFaceClient;

    public AiAnalysisServiceImpl(HuggingFaceClient huggingFaceClient) {
        this.huggingFaceClient = huggingFaceClient;
    }

    @Override
    public AiAnalysisResponseDto analyzeMood(AiAnalysisRequestDto requestDto) {

        System.out.println("INSIDE SERVICE");

        String result = huggingFaceClient.analyzeMood(
                requestDto.getMood(),
                requestDto.getNote()
        );

        System.out.println("AI RESPONSE = " + result);

        return new AiAnalysisResponseDto(result);
    }
}