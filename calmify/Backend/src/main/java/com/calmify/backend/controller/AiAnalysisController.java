package com.calmify.backend.controller;

import com.calmify.backend.dto.AiAnalysisRequestDto;
import com.calmify.backend.dto.AiAnalysisResponseDto;
import com.calmify.backend.service.AiAnalysisService;
import org.springframework.web.bind.annotation.*;
 
@RestController

@RequestMapping("/api/ai")
public class AiAnalysisController {

    private final AiAnalysisService aiAnalysisService;

    public AiAnalysisController(
            AiAnalysisService aiAnalysisService) {

        this.aiAnalysisService = aiAnalysisService;
    }

    @GetMapping("/chetan")
    public String chetan() {
        return "CHETAN123";
    }



    @PostMapping("/analyze")
public AiAnalysisResponseDto analyzeMood(
        @RequestBody AiAnalysisRequestDto requestDto) {

    System.out.println("===== AI ANALYZE API HIT =====");

    return aiAnalysisService.analyzeMood(requestDto);
}
}
    
   /* 
@RestController
@RequestMapping("/api/ai")
public class AiAnalysisController {

    @GetMapping("/test")
    public String test() {
        System.out.println("AI TEST HIT");
        return "AI Controller Working";
    }
}
*/

