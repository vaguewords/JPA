package com.ohgiraffers.section02.crud;

import jdk.jfr.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.persistence.EntityTransaction;
import java.util.stream.Stream;

public class EntityManagerCRUDTests {
    private EntityManagerCRUD manager;
    @BeforeEach
    void initManager() {
        this.manager = new EntityManagerCRUD();
    }

    @DisplayName("select 테스트")
    @ParameterizedTest
    @CsvSource(value={"1,1", "2,2", "3,3"})
    void testFindMethodByMenuCode(int menuCode, int expected) {
        //given
        Menu menu = manager.findMenuByMenuCode(menuCode);

        //when
        //then
        System.out.println(menu);
        Assertions.assertEquals(expected, menu.getMenuCode());
    }

    private static Stream<Arguments> newMenu() {
        return Stream.of(Arguments.of("새메뉴", 3000, 3, "Y"));
    }

    @DisplayName("regist 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        //given
        Long count = manager.saveAndReturnAllCount(menuName, menuPrice, categoryCode, orderableStatus);
        //then
        Assertions.assertEquals(22, count);
    }

    @DisplayName("update 테스트")
    @ParameterizedTest
    @CsvSource("1,민트미역국")
    void testModifyMenuName(int menuCode, String menuName) {
        //given
        Menu menu = manager.modifyMenuName(menuCode, menuName);

        //when
        //then
        Assertions.assertEquals(menuName, menu.getMenuName());
    }

    // ValueSource 어노테이션은 boolean을 제외한 기본 자료형과 String, Class만 지원함
    // byte, short, int, long, char, java.lang.String, java.lang.Class만 가능한 셈

    @DisplayName("delete 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testRemoveMenu(int menuCode) {
        // 일단 persistence context에서만 삭제, commit을 하면 비로소 db에서도 삭제
        Long count = manager.removeAndReturnAllCount(menuCode);
        Assertions.assertEquals(20, count);
    }

    @AfterEach
    void rollback() {
        EntityTransaction transaction = manager.getManagerInstance().getTransaction();
        transaction.rollback();
    }

}
