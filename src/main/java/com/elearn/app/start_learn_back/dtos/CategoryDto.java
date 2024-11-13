package com.elearn.app.start_learn_back.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CategoryDto {
    private String id;

    @NotEmpty(message = "Title is required !")
    @Size(min = 3,max = 50, message = "title must be between 3 and 50 charactor")
    private String title;

    @NotEmpty(message = "desc is required")
    private String desc;

    private Date addDate;

}
