package com.ohgiraffers.section01.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerGenerator {
    public static EntityManager getManagerInstance() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryGenerator.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

}
