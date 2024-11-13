package com.elearn.app.start_learn_back.controllers;


import com.elearn.app.start_learn_back.dtos.CategoryDto;
import com.elearn.app.start_learn_back.dtos.CustomMessage;
import com.elearn.app.start_learn_back.dtos.CustomPageResponse;
import com.elearn.app.start_learn_back.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elearn.app.start_learn_back.config.AppConstants.*;

@RestController
@RequestMapping("/api/v1/categories")
public class categoryController {

    private CategoryService categoryService;

    public categoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //create category

    @PostMapping
    public ResponseEntity<CategoryDto> createCetagory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto dto = categoryService.createService(categoryDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    //getAll
    @GetMapping
    public CustomPageResponse<CategoryDto> getALl(
            @RequestParam(value = "pageNumber",required = false,defaultValue =DEFAULT_PAGE_NUMBER )int pageNumber,
            @RequestParam(value = "pageSize",required = false,defaultValue = DEFAULT_PAGE_SIZE)int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = DEFAULT_SORT_BY) String sortBy
    ){
        return categoryService.getAll(pageNumber,pageSize,sortBy);
    }
    //single category
    @GetMapping("/{categoryId}")
    public CategoryDto getById(@PathVariable String categoryId){
        return categoryService.getById(categoryId);

    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> deleteById(@PathVariable String categoryId){
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setSuccess(true);
        customMessage.setMessage("Category deleted successfully !!");
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    //update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryId,categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }
}
