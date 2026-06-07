package com.routineroom.service.user;

import com.routineroom.common.common.CommonApiException;
import com.routineroom.common.common.ErrorCode;
import com.routineroom.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public void validateUserExists(Integer userNo) {
        if (userMapper.selectByUserNo(userNo) == null) {
            throw new CommonApiException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
