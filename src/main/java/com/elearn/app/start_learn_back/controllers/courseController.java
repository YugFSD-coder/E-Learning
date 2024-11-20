package com.elearn.app.start_learn_back.controllers;

import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.elearn.app.start_learn_back.config.AppConstants.*;

@RestController
@RequestMapping("/api/v1/courses")
public class courseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(courseDto));
    }

    @GetMapping
    public CustomPageResponse<CourseDto> getAllCourse(
            @RequestParam(value = "pageSize",required = false,defaultValue = DEFAULT_PAGE_NUMBER)int pageSize,
            @RequestParam(value = "pageNumber",required = false,defaultValue = DEFAULT_PAGE_SIZE)int pageNumber,
            @RequestParam(value = "sortBy",required = false,defaultValue = DEFAULT_SORT_BY)String sortBy

    ){
        return courseService.getAll(pageSize,pageNumber,sortBy);
    }

    @GetMapping("/{courseId}")
    public CourseDto getCourseById(@PathVariable String courseId){
        return courseService.getCourseByID(courseId);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<CustomMessage>deleteByCourseId(@PathVariable String courseId){
        courseService.deleteCourse(courseId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("course is deleted");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    @PutMapping("/{courseId}")
    public CourseDto updateCourse(@PathVariable String courseId,@RequestBody CourseDto courseDto){
        return courseService.updateCourse(courseDto,courseId);
    }

}
