package com.routineroom.mapper.chat;

import com.routineroom.entity.chat.ChatMessageEntity;
import com.routineroom.entity.chat.ChatRoomEntity;
import com.routineroom.entity.chat.ChatRoomMemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    ChatRoomEntity selectRoomById(Integer roomId);

    List<ChatRoomEntity> selectRoomListByUserNo(Integer userNo);

    void insertRoom(ChatRoomEntity room);

    void insertRoomMember(ChatRoomMemberEntity member);

    int selectMemberCount(@Param("roomId") Integer roomId, @Param("userNo") Integer userNo);

    List<ChatMessageEntity> selectMessageListByRoomId(
            @Param("roomId") Integer roomId,
            @Param("limit") int limit
    );

    void insertMessage(ChatMessageEntity message);

    void updateReadStatus(@Param("roomId") Integer roomId, @Param("userNo") Integer userNo);
}
