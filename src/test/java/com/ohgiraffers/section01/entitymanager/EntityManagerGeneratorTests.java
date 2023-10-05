package com.ohgiraffers.section01.entitymanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerGeneratorTests {
    /*
    * EntityManagerFactory
    * - 엔티티 매니저 생성 기능을 제공하는 팩토리
    * - 스레드에 안전함 : 여러 스레드가 동시에 접근해도 안전하므로 서로 다른 스레드들이 공유해서 재사용
    * - 요청 스코프마다 생상하기에는 시간, 메모리의 부담이 크므로 applicaton 스코프와 동일하게 싱글톤으로 생성해서 관리
    * - 따라서 어플리케이션 하나당 한 개의 엔티티 매니저 팩토리를 생성함
    * */
    @DisplayName("엔티티 매니저 팩토리 생성 확인하기")
    @Test
    void testGenerateEntityManagerFactory() {
        // given
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryGenerator.getInstance();
        // when
        // then
        Assertions.assertNotNull(entityManagerFactory);
    }

    @DisplayName("엔티티 매니저 싱글톤으로 생성됐는지 확인")
    @Test
    void testIsEntityManagerSingletonInstance() {
        // given
        EntityManagerFactory factory1 = EntityManagerFactoryGenerator.getInstance();
        // when
        EntityManagerFactory factory2 = EntityManagerFactoryGenerator.getInstance();
        // then
        Assertions.assertEquals(factory1, factory2);
    }

    /*
    * 엔티티 매니저
    * 메모리상의 데이터베이스인 영속성 컨텍스트를 관리하는 인스턴스
    * 엔티티 저장, 수정, 삭제, 조회 등 관련된 모든 작업 수행
    * 팩토리와는 달리 스레드에 안전하지 않아 동시성 문제 발생 가능
    * 스레드간 공유하지 않고, request scope와 일치시킴
    * 
    * 영속성 컨텍스트
    * 엔티티 매니저를 통해 엔티티를 저장/조회하면 엔티티가 보관되는 저장소
    * key-value 방식으로 저장
    * 엔티티 매니저 하나를 생성할 때마다 하나의 영속성 컨텍스트가 만들어짐
    * 엔티티 매니저를 통해서만 접근하고 관리할 수 있음
    * 
    * */

    @DisplayName("엔티티 매니저 생성")
    @Test
    void testGenerateEntityManager() {
        //given
        EntityManager entityManager = EntityManagerGenerator.getManagerInstance();
        //when
        //then
        Assertions.assertNotNull(entityManager);
    }

    @Test
    void testEntityManagerLifeCycle() {
        //given
        EntityManager manager1 = EntityManagerGenerator.getManagerInstance();
        EntityManager manager2 = EntityManagerGenerator.getManagerInstance();
        //when
        //then
        Assertions.assertNotEquals(manager1, manager2);
    }

}
