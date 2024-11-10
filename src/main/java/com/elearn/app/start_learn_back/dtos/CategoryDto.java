package com.elearn.app.start_learn_back.dtos;

import com.elearn.app.start_learn_back.entity.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CategoryDto {
    private String id;

    private String title;

    private String desc;

    private Date addDate;

    private List<CourseDto> coursesDto = new ArrayList<>();
}
