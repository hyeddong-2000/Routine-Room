package com.routineroom.entity.file;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FileMngEntity extends BaseEntity {

    private Integer fileId;
    private String filePath;
    private String orgnFileNm;
    private String chgFileNm;
    private String fileExt;
    private long fileSize;
}
