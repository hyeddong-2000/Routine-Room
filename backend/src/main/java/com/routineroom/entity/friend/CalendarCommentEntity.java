package com.routineroom.entity.friend;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarCommentEntity extends BaseEntity {

    private Integer commentId;
    private Integer targetUserNo;
    private Integer writerUserNo;
    private LocalDate targetDate;
    private String content;
    private String stickerCd;
    private boolean isSecret;
    private Integer parentCommentId;
    private boolean isDeleted;
}
