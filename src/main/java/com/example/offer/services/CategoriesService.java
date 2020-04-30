package com.example.offer.services;

import com.example.offer.entities.Categories;
import com.example.offer.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories(){
        return categoriesRepository.getAll();
    }

    public Categories getCategoryByID(int id){
        return categoriesRepository.getByID(id);
    }

    public Categories addNewCategory(Categories categories){
        if (categoriesRepository.isEmptyByName(categories.getName())) {
            return categoriesRepository.add(categories);
        } else {
            return null;
        }
    }

    //Уникальный по Name
    public Categories updateCategory(int id, Categories categories){
        if (categoriesRepository.isEmptyByName(categories.getName())) {
            return categoriesRepository.update(id, categories);
        } else {
            return null;
        }
    }

    public void remove(int id){
        categoriesRepository.remove(id);
    }

}
