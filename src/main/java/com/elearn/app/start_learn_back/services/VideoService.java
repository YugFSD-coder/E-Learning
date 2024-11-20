package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.dtos.VideoDto;
import com.elearn.app.start_learn_back.entity.Video;

import java.util.List;

public interface VideoService {

    VideoDto createVideo(VideoDto videoDto);

    CustomPageResponse<VideoDto> getAllVideo(int pageNumber, int pageSize,String sortBy);

    VideoDto getVideoById(String id);

    VideoDto updateVideo(String videoId, VideoDto videoDto);

    void deleteById(String id);
}
