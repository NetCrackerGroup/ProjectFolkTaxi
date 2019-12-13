package com.netcracker.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerConfiguration {

    private static final String PERSISTENCE_UNIT_NAME = "com.netcracker.hibernate";
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    private static final EntityManagerConfiguration INSTANCE = new EntityManagerConfiguration();

    private EntityManagerConfiguration() {
    }

    public static EntityManagerConfiguration getInstance() { return INSTANCE; }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void releaseResourcesAndClose() {
        entityManagerFactory.close();
    }

}