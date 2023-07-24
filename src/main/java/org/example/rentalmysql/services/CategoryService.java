package org.example.rentalmysql.services;

import org.example.rentalmysql.dao.CategoryDAO;
import org.example.rentalmysql.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {

    CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public void addCategory(Category category) {
        // TODO walidacja?
        categoryDAO.save( category );
    }

    public void updateCategory(Category category) {
        categoryDAO.save( category );
    }

    public void deleteCategory( Category category ) {
        categoryDAO.delete( category );
    }

    public void deleteCategoryWithId( Long categoryId ) {
        categoryDAO.deleteById( categoryId );
    }

    public Optional<Category> getCategoryWithId(Long categoryId ) {
        return categoryDAO.findById( categoryId );
    }

    public List<Category> getAllCategories() {
        Iterable<Category> categories = categoryDAO.findAll();
        return StreamSupport.stream(categories.spliterator(), false)
                .toList();
    }

}
