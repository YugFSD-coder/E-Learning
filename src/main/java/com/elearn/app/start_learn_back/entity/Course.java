package com.elearn.app.start_learn_back.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "courses")
@Data
public class Course {

    @Id
    private String courseId;

    private String title;

    private String shortDesc;

    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    private String banner;

    @OneToMany(mappedBy = "course")
    private List<Video> videos = new ArrayList<>();

    @ManyToMany
    private List<Category> categorList = new ArrayList<>();

}
