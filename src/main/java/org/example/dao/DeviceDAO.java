package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.entities.Device;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DeviceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Optional<Device> save( Device device ) {
        entityManager.persist( device );
        return Optional.of( device );
    }

    public Optional<Device> read(Long id ) {
        return Optional.of(entityManager.find( Device.class, id ));
    }

    @Transactional
    public Optional<Device> update( Device device ) {
        return Optional.of( entityManager.merge( device ) );
    }

    @Transactional
    public void delete( Device device ) {
        read( device.getId() ).ifPresent( entityManager::remove );
    }
}
