package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;

import java.util.List;

public interface CourseService {

    CourseDto create(CourseDto courseDto);

    CustomPageResponse<CourseDto> getAll(int pageSize, int pageNumber, String sortBy);

    CourseDto updateCourse(CourseDto courseDto, String courseId);

    void deleteCourse(String courseId);

    List<CourseDto> searchByTitle(String titleKeyword);

    CourseDto getCourseByID(String courseId);
}
