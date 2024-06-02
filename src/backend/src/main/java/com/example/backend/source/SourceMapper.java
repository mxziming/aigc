package com.example.backend.source;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;
@Mapper
public interface SourceMapper {
    void addPicture(Picture picture);
    void addVideo(Video video);
}
