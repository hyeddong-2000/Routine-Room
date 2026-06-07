package com.routineroom.entity.friend;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FriendEntity extends BaseEntity {

    private Integer friendShipId;
    private Integer requesterNo;
    private Integer receiverNo;
    private String statusCd;
}
