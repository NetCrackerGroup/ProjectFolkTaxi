package com.netcracker.repositories;

import javax.persistence.EntityManager;

import com.netcracker.hibernate.EntityManagerConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public abstract class AbstractRepository<T> {
    final Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void save(T entity) {
        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void remove(T entity) {
        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T find(Object id) {
        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        return entityManager.find(entityClass, id);
    }
}
