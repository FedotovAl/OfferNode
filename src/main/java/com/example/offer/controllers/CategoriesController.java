package com.example.offer.controllers;

import com.example.offer.entities.Categories;
import com.example.offer.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getAllCategories() {
        return new ResponseEntity<>(categoriesService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoriesId}")
    public ResponseEntity<Categories> getCategoryByID(@PathVariable("categoriesId") int id) {
        if (categoriesService.getCategoryByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoriesService.getCategoryByID(id), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories categories) {

        return new ResponseEntity<>(categoriesService.addNewCategory(categories), HttpStatus.CREATED);
    }

    @PutMapping("/categories/{categoriesId}")
    public ResponseEntity<Categories> updateCategory(@PathVariable("categoriesId") int id,
                               @RequestBody Categories categories) {
        if (categoriesService.getCategoryByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoriesService.updateCategory(id, categories), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoriesId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoriesId") int id) {
        if (categoriesService.getCategoryByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriesService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
