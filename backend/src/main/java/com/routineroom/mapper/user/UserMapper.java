package com.routineroom.mapper.user;

import com.routineroom.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserEntity selectById(String id);

    UserEntity selectByUserNo(Integer userNo);

    int selectCountById(String id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void updateTodayCnt(Integer userNo);

    void deleteById(Integer userNo);
}
