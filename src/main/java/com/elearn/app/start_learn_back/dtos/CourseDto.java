package com.elearn.app.start_learn_back.dtos;

import com.elearn.app.start_learn_back.entity.Category;
import com.elearn.app.start_learn_back.entity.Video;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CourseDto {
    private String courseId;

    private String title;

    private String shortDesc;

    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    private String banner;

}
