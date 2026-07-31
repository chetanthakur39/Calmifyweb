package com.calmify.backend.service;

import com.calmify.backend.dto.AiAnalysisRequestDto;
import com.calmify.backend.dto.AiAnalysisResponseDto;

public interface AiAnalysisService {

    AiAnalysisResponseDto analyzeMood(
            AiAnalysisRequestDto requestDto);

}

