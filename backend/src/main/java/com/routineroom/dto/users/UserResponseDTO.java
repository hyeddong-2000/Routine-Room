package com.routineroom.dto.users;

import lombok.Builder;
import lombok.Getter;

public class UserResponseDTO {

    @Getter
    @Builder
    public static class LoginInfo {
        private Integer userNo;
        private String id;
        private String nickname;
        private String authority;
        private String accessToken;
    }

    @Getter
    @Builder
    public static class Profile {
        private Integer userNo;
        private String id;
        private String nickname;
        private Integer profileFileId;
        private String bgmUseYn;
        private int todayCnt;
        private int totalCnt;
    }
}
