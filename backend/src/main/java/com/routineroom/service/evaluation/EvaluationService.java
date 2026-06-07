package com.routineroom.service.evaluation;

import com.routineroom.mapper.evaluation.EvaluationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationMapper evaluationMapper;
}
