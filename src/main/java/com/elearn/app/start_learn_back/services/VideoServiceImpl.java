package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.dtos.VideoDto;
import com.elearn.app.start_learn_back.entity.Video;
import com.elearn.app.start_learn_back.exception.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositries.VideoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService{

    private VideoRepo videoRepo;

    private ModelMapper modelMapper;

    public VideoServiceImpl (VideoRepo videoRepo,ModelMapper modelMapper ){
        this.videoRepo=videoRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public VideoDto createVideo(VideoDto videoDto) {

        String id = UUID.randomUUID().toString();
        videoDto.setVedioId(id);
        Video saveVideo = modelMapper.map(videoDto,Video.class);
        Video vd = videoRepo.save(saveVideo);
        return modelMapper.map(vd,VideoDto.class);
    }

    @Override
    public CustomPageResponse<VideoDto> getAllVideo(int pageNumber, int pageSize,String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Video> videoList = videoRepo.findAll(pageRequest);
        List<Video> videos = videoList.getContent();

        List<VideoDto> videoDtos = videos.stream()
                                    .map(vd -> modelMapper.map(videos,VideoDto.class)).toList();

        CustomPageResponse<VideoDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setTotalElement(videoList.getTotalElements());
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(pageSize);
        customPageResponse.setTotalPages(videoList.getTotalPages());
        customPageResponse.setContent(videoDtos);
        customPageResponse.setLast(videoList.isLast());

        return customPageResponse;
    }

    @Override
    public VideoDto getVideoById(String id) {
        Video video = videoRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Video not found !!"));
        return modelMapper.map(video,VideoDto.class);
    }


    @Override
    public VideoDto updateVideo(String videoId, VideoDto videoDto) {
        Video video = videoRepo.findById(videoId)
                .orElseThrow(()-> new ResourceNotFoundException("Video Can not be update !!, Not found "));
        if(videoDto.getTitle() != null){
            video.setTitle(videoDto.getTitle());
        }
        Optional.ofNullable(videoDto.getDesc())
                .ifPresent(video::setDesc);
        Optional.ofNullable(videoDto.getContentType()).ifPresent(video::setContentType);
        Video saveVideo = modelMapper.map(video, Video.class);
        return modelMapper.map(saveVideo,VideoDto.class);
    }

    @Override
    public void deleteById(String id) {
        Video video = videoRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(" not deleted , Not found !"));
        videoRepo.delete(video);
    }
}
