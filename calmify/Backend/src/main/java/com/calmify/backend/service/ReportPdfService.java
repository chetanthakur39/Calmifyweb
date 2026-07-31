package com.calmify.backend.service;

public interface ReportPdfService {

    byte[] generateReport(Long userId);

}
