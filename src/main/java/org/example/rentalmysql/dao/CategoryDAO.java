package org.example.rentalmysql.dao;

import org.example.rentalmysql.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends CrudRepository<Category, Long > {
}
