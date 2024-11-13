package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;

public interface CategoryService {

    CategoryDto createService(CategoryDto categoryDto);

    CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy);

    CategoryDto getById(String categoryId);

    void delete(String categoryId);

    CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);


    //search

}
