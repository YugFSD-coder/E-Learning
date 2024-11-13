package com.elearn.app.start_learn_back.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageResponse<T>{

    private int pageSize;

    private int pageNumber;

    private long totalElement;

    private int totalPages;

    private boolean isLast;

    private List<T> content;

}

