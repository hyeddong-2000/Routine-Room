package com.routineroom.service.friend;

import com.routineroom.mapper.friend.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendMapper friendMapper;
}
