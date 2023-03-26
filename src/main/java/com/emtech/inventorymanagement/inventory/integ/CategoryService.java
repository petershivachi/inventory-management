package com.emtech.inventorymanagement.inventory.integ;

import com.emtech.inventorymanagement.inventory.data.EntityResponse;
import com.emtech.inventorymanagement.inventory.data.category.CategoryData;
import com.emtech.inventorymanagement.inventory.data.http.response.CategoriesResponse;
import com.emtech.inventorymanagement.inventory.data.http.response.CategoryResponse;
import com.emtech.inventorymanagement.inventory.model.Category;
import com.emtech.inventorymanagement.inventory.repo.CategoryRepository;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

@Service
@Log
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public EntityResponse createCategory(@NonNull String name, @NonNull String description){
        AtomicReference<EntityResponse> response  = new AtomicReference<>();

        this.categoryRepository.findByName(name).ifPresentOrElse(category -> {
            log.log(Level.WARNING, String.format("Category with the name %s already exists", name));

            response.set(EntityResponse.builder().message(String.format("Category with the name %s already exists", name)).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }, () -> {
            AtomicReference<Category> category = new AtomicReference<>(new Category());
            category.get().setName(name);
            category.get().setDescription(description);
            category.get().setStatus(1);

            category.set(this.categoryRepository.save(category.get()));

            response.set(EntityResponse.builder().message("Category created successfully").statusCode(HttpStatus.CREATED.value()).build());
        });

        return response.get();
    }

    public EntityResponse updateCategory(@NonNull Long categoryId, @NonNull String description){
        AtomicReference<EntityResponse> response  = new AtomicReference<>();

        this.categoryRepository.findById(categoryId).ifPresentOrElse(category -> {
            AtomicReference<Category> myCategory  = new AtomicReference<>(category);
            myCategory.get().setDescription(description);

            myCategory.set(this.categoryRepository.save(myCategory.get()));

            response.set(EntityResponse.builder().message("Category updated successfully").statusCode(HttpStatus.OK.value()).build());
        }, () -> {
            log.log(Level.WARNING, String.format("Category with the id %s not found", categoryId));

            response.set(EntityResponse.builder().message(String.format("Category with the id %s not found", categoryId)).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        });

        return response.get();
    }

    public EntityResponse updateStatus(@NonNull Long categoryId, @NonNull Integer status){
        AtomicReference<EntityResponse> response  = new AtomicReference<>();

        this.categoryRepository.findById(categoryId).ifPresentOrElse(category -> {
            AtomicReference<Category> myCategory  = new AtomicReference<>(category);
            myCategory.get().setStatus(status);

            myCategory.set(this.categoryRepository.save(myCategory.get()));

            response.set(EntityResponse.builder().message("Category status updated successfully").statusCode(HttpStatus.OK.value()).build());
        }, () -> {
            log.log(Level.WARNING, String.format("Category with the id %s not found", categoryId));

            response.set(EntityResponse.builder().message(String.format("Category with the id %s not found", categoryId)).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        });

        return response.get();
    }

    public CategoryResponse findCategoryById(@NonNull Long categoryId){
        AtomicReference<CategoryResponse> response  = new AtomicReference<>();

        this.categoryRepository.findById(categoryId).ifPresentOrElse(category -> {
            CategoryData categoryData = CategoryData.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .status(category.getStatus())
                    .creationDate(category.getCreationDate())
                    .updateDate(category.getUpdateDate())
                    .build();

            response.set(CategoryResponse.builder().category(categoryData).statusCode(HttpStatus.OK.value()).build());
        }, () -> {
            log.log(Level.WARNING, String.format("Category with the id %s not found", categoryId));
        });

        return response.get();
    }

    public CategoryResponse findCategoryByName(@NonNull String name){
        AtomicReference<CategoryResponse> response  = new AtomicReference<>();

        this.categoryRepository.findByName(name).ifPresentOrElse(category -> {
            CategoryData categoryData = CategoryData.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .status(category.getStatus())
                    .creationDate(category.getCreationDate())
                    .updateDate(category.getUpdateDate())
                    .build();

            response.set(CategoryResponse.builder().category(categoryData).statusCode(HttpStatus.OK.value()).build());
        }, () -> {
            log.log(Level.WARNING, String.format("Category with the name %s not found", name));
        });

        return response.get();
    }

    public CategoriesResponse findAllCategories(){
        AtomicReference<CategoriesResponse> response  = new AtomicReference<>();

        List<CategoryData> categoriesData = new ArrayList<>();

        List<Category> categories = this.categoryRepository.findAll();

        if(!categories.isEmpty()){
            categories.forEach(category -> {
                CategoryData categoryData = CategoryData.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .status(category.getStatus())
                        .creationDate(category.getCreationDate())
                        .updateDate(category.getUpdateDate())
                        .build();

                categoriesData.add(categoryData);
            });

            response.set(CategoriesResponse.builder().categories(categoriesData).statusCode(HttpStatus.OK.value()).build());
        }

        return response.get();
    }

    public CategoriesResponse findAllCategoriesByStatus(@NonNull Integer status){
        AtomicReference<CategoriesResponse> response  = new AtomicReference<>();

        List<CategoryData> categoriesData = new ArrayList<>();

        List<Category> categories = this.categoryRepository.findByStatus(status);

        if(!categories.isEmpty()){
            categories.forEach(category -> {
                CategoryData categoryData = CategoryData.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .status(category.getStatus())
                        .creationDate(category.getCreationDate())
                        .updateDate(category.getUpdateDate())
                        .build();

                categoriesData.add(categoryData);
            });

            response.set(CategoriesResponse.builder().categories(categoriesData).statusCode(HttpStatus.OK.value()).build());
        }

        return response.get();
    }
}
