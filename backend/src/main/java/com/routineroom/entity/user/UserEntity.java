package com.routineroom.entity.user;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    private Integer userNo;
    private String id;
    private String password;
    private String nickname;
    private String authority;
    private Integer bgmFileId;
    private String bgmUseYn;
    private Integer profileFileId;
    private int todayCnt;
    private int totalCnt;
    private String clientIp;
}
