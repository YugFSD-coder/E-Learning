package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.entity.Course;
import com.elearn.app.start_learn_back.exception.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositries.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepo courseRepo;
    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper){
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        Course savedData = courseRepo.save(this.dtoToEntity(courseDto));
       return entityToDto(savedData);
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courseList = courseRepo.findAll();
        List<CourseDto> courseDtoList =courseList.stream()
                .map(course -> entityToDto(course))
                .collect(Collectors.toList());
        return courseDtoList;
    }

    @Override
    public CourseDto update(CourseDto courseDto, String courseId) {
        return null;
    }

    @Override
    public void delete(String courseId) {
        Course course =courseRepo.findById(courseId)
                .orElseThrow(()->new ResourceNotFoundException("Course not found !"));
        courseRepo.delete(course);
    }



    @Override
    public List<CourseDto> searchByTitle(String titleKeyword) {
        return List.of();
    }

    public CourseDto entityToDto(Course course){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setCourseId(course.getCourseId());
//        courseDto.setTitle(course.getTitle());
        CourseDto courseDto = modelMapper.map(course,CourseDto.class);
        return courseDto;
    }

    public Course dtoToEntity(CourseDto courseDto){
        Course course = modelMapper.map(courseDto,Course.class);
        return course;
    }



}
