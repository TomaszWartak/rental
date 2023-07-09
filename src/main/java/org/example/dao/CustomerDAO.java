package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Optional<Customer> save( Customer customer ) {
        entityManager.persist( customer );
        return Optional.of( customer );
    }

    public Optional<Customer> read(Long id ) {
        return Optional.of(entityManager.find( Customer.class, id ));
    }

    @Transactional
    public Optional<Customer> update( Customer customer ) {
        return Optional.of( entityManager.merge( customer ) );
    }

    @Transactional
    public void delete( Customer customer ) {
        read( customer.getId() ).ifPresent( entityManager::remove );
    }
}
