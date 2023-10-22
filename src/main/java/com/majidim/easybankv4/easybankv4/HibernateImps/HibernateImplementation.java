package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.InterfaceData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.List;
import java.util.Optional;

public class HibernateImplementation<Entity, Identifier> implements InterfaceData<Entity, Identifier> {
    private final EntityManager em;

    public HibernateImplementation() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
        em = emf.createEntityManager(); //represent the context
    }

    public HibernateImplementation(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Entity> create(Entity entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return Optional.of(entity);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return Optional.empty(); // Return an empty Optional on error
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<Entity> update(Entity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Entity> findByID(Identifier id) {
        return Optional.empty();
    }

    @Override
    public List<Entity> getAll() {
        return null;
    }

    @Override
    public boolean delete(Identifier id) {
        return false;
    }
}
