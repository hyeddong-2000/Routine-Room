package com.routineroom.mapper.friend;

import com.routineroom.entity.friend.CalendarCommentEntity;
import com.routineroom.entity.friend.FriendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface FriendMapper {

    FriendEntity selectByPair(
            @Param("requesterNo") Integer requesterNo,
            @Param("receiverNo") Integer receiverNo
    );

    List<FriendEntity> selectAcceptedFriendsByUserNo(Integer userNo);

    int selectCountByPair(
            @Param("requesterNo") Integer requesterNo,
            @Param("receiverNo") Integer receiverNo
    );

    void insertFriend(FriendEntity friend);

    void updateFriendStatus(
            @Param("friendShipId") Integer friendShipId,
            @Param("statusCd") String statusCd,
            @Param("lastMdfr") String lastMdfr
    );

    void deleteById(Integer friendShipId);

    List<CalendarCommentEntity> selectCommentListByTargetAndDate(
            @Param("targetUserNo") Integer targetUserNo,
            @Param("targetDate") LocalDate targetDate
    );

    void insertComment(CalendarCommentEntity comment);

    void softDeleteComment(
            @Param("commentId") Integer commentId,
            @Param("lastMdfr") String lastMdfr
    );
}
