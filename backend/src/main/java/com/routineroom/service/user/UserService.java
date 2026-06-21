package com.routineroom.service.user;

import com.routineroom.common.common.CommonApiException;
import com.routineroom.common.common.ErrorCode;
import com.routineroom.common.security.UserAuthenticationDTO;
import com.routineroom.common.util.JwtUtil;
import com.routineroom.common.util.SecurityUtil;
import com.routineroom.dto.users.UserRequestDTO;
import com.routineroom.dto.users.UserResponseDTO;
import com.routineroom.entity.user.UserEntity;
import com.routineroom.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void registerUser(UserRequestDTO.SignUp request) {
        if (userMapper.selectCountById(request.getId()) > 0) {
            throw new CommonApiException(ErrorCode.USER_ALREADY_EXISTS);
        }
        if (userMapper.selectCountByNickname(request.getNickname()) > 0) {
            throw new CommonApiException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        UserEntity user = UserEntity.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .authority("ROLE_USER")
                .bgmUseYn("N")
                .todayCnt(0)
                .totalCnt(0)
                .build();
        user.setFirst(request.getId());
        userMapper.insert(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO.LoginInfo loginUser(UserRequestDTO.Login request) {
        UserEntity user = userMapper.selectById(request.getId());
        if (user == null) {
            throw new CommonApiException(ErrorCode.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CommonApiException(ErrorCode.INVALID_PASSWORD);
        }

        UserAuthenticationDTO authUser = UserAuthenticationDTO.builder()
                .userNo(user.getUserNo())
                .id(user.getId())
                .authority(user.getAuthority())
                .build();

        return UserResponseDTO.LoginInfo.builder()
                .userNo(user.getUserNo())
                .id(user.getId())
                .nickname(user.getNickname())
                .authority(user.getAuthority())
                .accessToken(jwtUtil.generateToken(authUser))
                .build();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO.Profile getMyInfo() {
        Integer userNo = SecurityUtil.getCurrentUserNo();
        UserEntity user = userMapper.selectByUserNo(userNo);
        if (user == null) {
            throw new CommonApiException(ErrorCode.USER_NOT_FOUND);
        }
        return UserResponseDTO.Profile.builder()
                .userNo(user.getUserNo())
                .id(user.getId())
                .nickname(user.getNickname())
                .profileFileId(user.getProfileFileId())
                .bgmUseYn(user.getBgmUseYn())
                .todayCnt(user.getTodayCnt())
                .totalCnt(user.getTotalCnt())
                .build();
    }

    @Transactional(readOnly = true)
    public void validateUserExists(Integer userNo) {
        if (userMapper.selectByUserNo(userNo) == null) {
            throw new CommonApiException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
