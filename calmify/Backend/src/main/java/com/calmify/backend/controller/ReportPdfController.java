package com.calmify.backend.controller;

import com.calmify.backend.service.ReportPdfService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportPdfController {

    private final ReportPdfService reportPdfService;

    public ReportPdfController(ReportPdfService reportPdfService) {
        this.reportPdfService = reportPdfService;
    }

    @GetMapping("/pdf/{userId}")
    public ResponseEntity<byte[]> downloadReport(
            @PathVariable Long userId) {

        byte[] pdf = reportPdfService.generateReport(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename("Calmify_Report.pdf")
                        .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }
}
