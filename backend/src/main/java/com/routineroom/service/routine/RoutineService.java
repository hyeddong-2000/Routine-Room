package com.routineroom.service.routine;

import com.routineroom.common.common.CommonApiException;
import com.routineroom.common.common.ErrorCode;
import com.routineroom.mapper.routine.RoutineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineMapper routineMapper;

    @Transactional(readOnly = true)
    public void validateRoutineExists(Integer routineId) {
        if (routineMapper.selectById(routineId) == null) {
            throw new CommonApiException(ErrorCode.ROUTINE_NOT_FOUND);
        }
    }
}
