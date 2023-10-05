package com.ohgiraffers.section02.crud;

import javax.persistence.*;

//@Entity : 엔티티 선언
//@Table : 엔티티를 가지고 테이블을 JPA가 알아서 만듦(name없으면 클래스명으로)
//@Id : 식별자
//@Column : 컬럼(name 없으면 카멜케이스 앞부분마다 언더바 생성해서 자동 명명)
//@GeneratedValue : AUTO(기본), IDENTITY(MYSQL 오토 인크리먼트), SEQUENCE(오라클 시퀀스), TABLE(별도 테이블로 관리)
@Entity(name = "Section02Menu")
@Table(name = "TBL_MENU")
public class Menu {
    @Id
    @Column(name = "MENU_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;
    @Column(name = "MENU_NAME")
    private String menuName;
    @Column(name = "MENU_PRICE")
    private int menuPrice;
    @Column(name = "CATEGORY_CODE")
    private int categoryCode;
    @Column(name = "ORDERABLE_STATUS")
    private String orderableStatus;

    protected Menu() {
    }

    public Menu(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
