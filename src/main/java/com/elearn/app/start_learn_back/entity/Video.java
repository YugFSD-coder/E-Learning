package com.elearn.app.start_learn_back.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "videos")
@Data
public class Video {

    @Id
    private String vedioId;

    private String title;

    @Column(name = "description",length = 1000)
    private String desc;

    @Column(length = 2000)
    private String filePath;

    private String contentType;

    @ManyToOne
    private Course course;



}
