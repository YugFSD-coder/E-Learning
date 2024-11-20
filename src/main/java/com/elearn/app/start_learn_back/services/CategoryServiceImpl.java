package com.elearn.app.start_learn_back.services;

import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.entity.Category;
import com.elearn.app.start_learn_back.exception.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositries.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createService(CategoryDto categoryDto) {
        //create id
        String id = UUID.randomUUID().toString();
        categoryDto.setId(id);
        //create date
        categoryDto.setAddDate(new Date());

        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCat = categoryRepo.save(category);
        return modelMapper.map(savedCat,CategoryDto.class);
    }

    @Override
    public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy) {
        Sort sort = Sort.by(sortBy).descending();
                //page size limit
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> category = categoryRepo.findAll(pageRequest);
        List<Category> all = category.getContent();
        List <CategoryDto>categoryDtos = all
                .stream().map(cate -> modelMapper.map(cate,CategoryDto.class)).toList();

        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setTotalElement(category.getTotalElements());
        customPageResponse.setContent(categoryDtos);
        customPageResponse.setTotalPages(category.getTotalPages());
        customPageResponse.setPageSize(pageSize);
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setLast(category.isLast());

        return customPageResponse;
    }

    @Override
    public CategoryDto getById(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found !!!"));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found, not deleted !!"));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found,can not be updated !!")
        );
        if(category.getTitle() != null){
            category.setTitle(categoryDto.getTitle());
        }
        Optional.ofNullable(categoryDto.getDesc())
                .ifPresent(category::setDesc);
        category.setAddDate(new Date());
        Category  saveUpdate= categoryRepo.save(category);

        return modelMapper.map(saveUpdate,CategoryDto.class);
    }
}
