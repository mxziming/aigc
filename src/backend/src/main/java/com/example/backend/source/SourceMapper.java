package com.example.backend.source;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface SourceMapper {
    void addPicture(Picture picture);
    void addVideo(Video video);
    List<String> getAllPictureUrls(int uid);
    List<String> getAllVideoUrls(int uid);
}
