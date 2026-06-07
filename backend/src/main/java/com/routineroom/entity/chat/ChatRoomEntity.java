package com.routineroom.entity.chat;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomEntity extends BaseEntity {

    private Integer roomId;
    private String roomTitle;
    private String roomTypeCd;
}
