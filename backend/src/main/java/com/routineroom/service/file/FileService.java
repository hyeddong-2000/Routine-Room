package com.routineroom.service.file;

import com.routineroom.mapper.file.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
}
