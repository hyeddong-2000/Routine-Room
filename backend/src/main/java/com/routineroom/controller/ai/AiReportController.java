package com.routineroom.controller.ai;

import com.routineroom.service.ai.AiReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/reports")
@RequiredArgsConstructor
public class AiReportController {

    private final AiReportService aiReportService;
}
