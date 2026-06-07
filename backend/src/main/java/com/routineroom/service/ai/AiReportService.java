package com.routineroom.service.ai;

import com.routineroom.mapper.ai.AiReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AiReportService {

    private final AiReportMapper aiReportMapper;
}
