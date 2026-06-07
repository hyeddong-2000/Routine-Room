package com.routineroom.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomMemberEntity {

    private Integer roomId;
    private Integer userNo;
    private LocalDateTime joinedDt;
}
