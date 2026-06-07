package com.routineroom.mapper.file;

import com.routineroom.entity.file.FileMngEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    FileMngEntity selectById(Integer fileId);

    void insert(FileMngEntity file);

    void deleteById(Integer fileId);
}
