package com.ohgiraffers.section01.entitymanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryGenerator {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ohgiraffers");
    // 팩토리 생성 및 이름 지정

    private EntityManagerFactoryGenerator() {}
    static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }
}
