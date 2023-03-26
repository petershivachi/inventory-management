package com.emtech.inventorymanagement.inventory.handler;


import com.emtech.inventorymanagement.inventory.data.EntityResponse;
import com.emtech.inventorymanagement.inventory.data.http.request.CategoryCreateRequest;
import com.emtech.inventorymanagement.inventory.data.http.request.CategoryUpdateRequest;
import com.emtech.inventorymanagement.inventory.data.http.response.CategoriesResponse;
import com.emtech.inventorymanagement.inventory.data.http.response.CategoryResponse;
import com.emtech.inventorymanagement.inventory.integ.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(
        path = "/api/v1/categories"
)
public class CategoryHandler {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(
            path ="/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<EntityResponse>> createCategory(@RequestBody CategoryCreateRequest body){
        EntityResponse response = this.categoryService.createCategory(body.getName(), body.getDescription());

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<EntityResponse>> updateCategory(@RequestBody CategoryUpdateRequest body){
        EntityResponse response = this.categoryService.updateCategory(body.getCategoryId(), body.getDescription());

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/activate-category/{categoryId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<EntityResponse>> activateCategory(@PathVariable Long categoryId){
        EntityResponse response = this.categoryService.updateStatus(categoryId, 1);

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/deactivate-category/{categoryId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<EntityResponse>> deactivateCategory(@PathVariable Long categoryId){
        EntityResponse response = this.categoryService.updateStatus(categoryId, 0);

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/{categoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<CategoryResponse>> findCategoryById(@PathVariable Long categoryId){
        CategoryResponse response = this.categoryService.findCategoryById(categoryId);

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<CategoryResponse>> findCategoryByName(@PathVariable String name){
        CategoryResponse response = this.categoryService.findCategoryByName(name);

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<CategoriesResponse>> findAllCategories(@PathVariable String name){
        CategoriesResponse response = this.categoryService.findAllCategories();

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/active-categories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<CategoriesResponse>> findAllActiveCategories(){
        CategoriesResponse response = this.categoryService.findAllCategoriesByStatus(1);

        return Mono.just(ResponseEntity.ok().body(response));
    }

    @RequestMapping(
            path ="/in-active-categories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<CategoriesResponse>> findAllInactiveCategories(){
        CategoriesResponse response = this.categoryService.findAllCategoriesByStatus(0);

        return Mono.just(ResponseEntity.ok().body(response));
    }
}
