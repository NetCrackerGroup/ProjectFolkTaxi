package com.netcracker.repositories;

import javax.persistence.EntityManager;

import com.netcracker.hibernate.EntityManagerConfiguration;

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
