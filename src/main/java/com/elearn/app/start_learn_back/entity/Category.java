package com.elearn.app.start_learn_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="catagories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String title;

    @Column(name = "description")
    private String desc;

    private Date addDate;

    @ManyToMany(mappedBy = "categoryList")
    private List<Course> courses = new ArrayList<>();
}
