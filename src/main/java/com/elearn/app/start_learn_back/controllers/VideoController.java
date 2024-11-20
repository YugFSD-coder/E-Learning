package com.elearn.app.start_learn_back.controllers;

import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.dtos.VideoDto;
import com.elearn.app.start_learn_back.services.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.elearn.app.start_learn_back.config.AppConstants.*;

@RestController
@RequestMapping()
public class VideoController {

    private VideoService videoService;

    public VideoController(VideoService videoService){
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDto> createVideo( @RequestBody VideoDto videoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.createVideo(videoDto));
    }

    @GetMapping
    public CustomPageResponse<VideoDto> getAllVideo(
            @RequestParam (value = "pageNumber", required = false,defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam (value = "pageSize", required = false,defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam (value = "sortBy", required = false,defaultValue = DEFAULT_SORT_BY) String sortBy
    ){
        return  videoService.getAllVideo(pageNumber,pageSize,sortBy);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable String videoId){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideoById(videoId));
    }

    @DeleteMapping("/{videoId}")
    public CustomMessage deleteVideoByid(@PathVariable String videoId){
        videoService.deleteById(videoId);
        CustomMessage message = new CustomMessage();
        message.setMessage("Video deleted !!");
        message.setSuccess(true);
        return message;
    }

    @PutMapping("/{viedoId}")
    public ResponseEntity<VideoDto> updateVideoByid(@PathVariable String videoId,@RequestBody VideoDto videoDto){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.updateVideo(videoId,videoDto));
    }

}
