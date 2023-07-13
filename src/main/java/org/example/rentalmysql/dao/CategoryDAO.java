package org.example.rentalmysql.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.rentalmysql.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Optional<Category> save(Category category ) {
        entityManager.persist( category );
        return Optional.of( category );
    }

    public Optional<Category> read(Long id ) {
        return Optional.of(entityManager.find( Category.class, id ));
    }

    @Transactional
    public Optional<Category> update( Category category ) {
        return Optional.of( entityManager.merge( category ) );
    }

    @Transactional
    public void delete( Category category ) {
        read( category.getId() ).ifPresent( entityManager::remove );
    }
}
