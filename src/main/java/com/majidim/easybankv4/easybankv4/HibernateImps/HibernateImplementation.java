package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.InterfaceData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import com.majidim.easybankv4.easybankv4.dto.*;

import java.util.Collections;
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
    public Optional<Entity> findByID(Identifier id, Class<Entity> entityClass) {


        try {
            em.getTransaction().begin();
            try {
                Entity entity = em.find(entityClass, id);
                return Optional.ofNullable(entity);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            em.getTransaction().commit();
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    @Override
    public List<Entity> getAll(Class<Entity> entityClass) {
        try {
            System.out.println("from befor getall");
            em.getTransaction().begin();

            // Use JPQL (Java Persistence Query Language) to create a query to select all entities
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<Entity> query = em.createQuery(jpql, entityClass);

            List<Entity> resultList = query.getResultList();

            em.getTransaction().commit();
            System.out.println("from after getall");
            return resultList;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return Collections.emptyList(); // Return an empty list on error
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(Identifier id) {
        return false;
    }
}
