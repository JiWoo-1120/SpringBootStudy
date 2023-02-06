package com.example.demo.ioc;

public class Steak {
    public Steak(IngredientFactory ingredientFactory) {

    }

    public String cook(String menu) {
        // 요리 재료 준비
        Pork pork = new Pork("한우 꽃등심");


        // 요리 반환
        return pork.getName() + "으로 만든 " + menu;

    }
}
