package com.elearn.app.start_learn_back.dtos;

import com.elearn.app.start_learn_back.entity.Course;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class VideoDto {

    private String vedioId;

    private String title;

    private String desc;

    private String filePath;

    private String contentType;

    private CourseDto course;
}
