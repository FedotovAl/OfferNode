package com.example.offer.repositories;

import com.example.offer.entities.Categories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CategoriesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Categories> getAll(){
        return entityManager
                .createQuery("from Categories", Categories.class)
                .getResultList();
    }

    public Categories getByID(int id){
        return entityManager.find(Categories.class, id);
    }

    public Categories add(Categories categories){
        entityManager.persist(categories);
        return categories;
    }

    public Categories update(int id, Categories categories){
        Categories originalCategories = entityManager.find(Categories.class, id);
        if (originalCategories != null){
            originalCategories.setName(categories.getName());

            entityManager.merge(originalCategories);
        }
        return originalCategories;
    }

    public void remove(int id){
        Categories originalCategories = entityManager.find(Categories.class, id);
        if (originalCategories != null && originalCategories.getOffersSetCategor().isEmpty()){
            entityManager.remove(originalCategories);
        }
    }

    public boolean isEmptyByName(String paramName) throws NullPointerException{
        List<Categories> originalCategories = entityManager
                .createQuery("from Categories where name = '"
                                + paramName +
                                "'",
                        Categories.class
                )
                .getResultList();
        if (originalCategories.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    //?
    public Categories getByName(String paramName){
        List<Categories> categoriesList = entityManager
                .createQuery("from Categories where name = '"
                                + paramName +
                                "'",
                        Categories.class
                ).getResultList();
        if (categoriesList.size() != 0){
            return categoriesList.get(0);
        } else {
            return null;
        }
    }
}
