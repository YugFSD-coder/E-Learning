package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CourseDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.entity.Course;
import com.elearn.app.start_learn_back.exception.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositries.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
//        Course savedData = courseRepo.save(this.dtoToEntity(courseDto));
//       return entityToDto(savedData);
        //set ID & date
        String id = UUID.randomUUID().toString();
        courseDto.setCourseId(id);
        courseDto.setCreatedDate(new Date());

        Course course = modelMapper.map(courseDto,Course.class);
        Course saveCourse = courseRepo.save(course);
        return modelMapper.map(saveCourse,CourseDto.class);
    }

    @Override
    public CustomPageResponse<CourseDto> getAll(int pageSize, int pageNumber, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();

        PageRequest pageRequest = PageRequest.of(pageSize, pageNumber,sort);
        Page<Course> courseList = courseRepo.findAll(pageRequest);
        List<Course> courses = courseList.getContent();
        List<CourseDto> courseDtoList = courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class)).toList();

        CustomPageResponse<CourseDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setTotalElement(courseList.getTotalElements());
        customPageResponse.setContent(courseDtoList);
        customPageResponse.setTotalPages(courseList.getTotalPages());
        customPageResponse.setPageSize(pageSize);
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setLast(courseList.isLast());

        return customPageResponse;
    }

    @Override
    public CourseDto getCourseByID(String courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found !!"));
        return modelMapper.map(course,CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto, String courseId) {
        Course course = courseRepo.findById(courseId).
                orElseThrow(()->new ResourceNotFoundException("course not updated, not avaliable"));
        course.setCreatedDate(new Date());
        Optional.ofNullable(course.getTitle()).ifPresent(course::setTitle);
        if(course.getDiscount() != 0){
            course.setDiscount(courseDto.getDiscount());
        }
        Optional.ofNullable(course.getShortDesc()).ifPresent(course::setShortDesc);
        Course savedCourse = courseRepo.save(course);
        return modelMapper.map(savedCourse,CourseDto.class);

    }

    @Override
    public void deleteCourse(String courseId) {
        Course course =courseRepo.findById(courseId)
                .orElseThrow(()->new ResourceNotFoundException("Course not found !"));
        courseRepo.delete(course);
    }



    @Override
    public List<CourseDto> searchByTitle(String titleKeyword) {
       List<Course> course = courseRepo.findBytitle(titleKeyword);
       if(course.isEmpty()){
           throw new ResourceNotFoundException("No course avaliable of title "+ titleKeyword);
       }

        List<CourseDto> courseDtoList = course.stream()
                .map(course1 -> modelMapper.map(course1,CourseDto.class))
                .toList();
        return courseDtoList;
    }



//    public CourseDto entityToDto(Course course){
////        CourseDto courseDto = new CourseDto();
////        courseDto.setCourseId(course.getCourseId());
////        courseDto.setTitle(course.getTitle());
//        CourseDto courseDto = modelMapper.map(course,CourseDto.class);
//        return courseDto;
//    }

//    public Course dtoToEntity(CourseDto courseDto){
//        Course course = modelMapper.map(courseDto,Course.class);
//        return course;
//    }



}
