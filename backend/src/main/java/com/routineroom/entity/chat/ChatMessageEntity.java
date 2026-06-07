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
public class ChatMessageEntity extends BaseEntity {

    private Integer messageId;
    private Integer roomId;
    private Integer userNo;
    private String messageContent;
    private boolean isRead;
}
