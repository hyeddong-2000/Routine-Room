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
public class UserDeviceEntity extends BaseEntity {

    private Integer deviceId;
    private Integer userNo;
    private String deviceToken;
    private String osType;
}
