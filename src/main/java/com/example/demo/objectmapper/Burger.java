package com.example.demo.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor // 생성자 추가
@ToString
@Getter
public class Burger {
    private String name;
    private  int price;
    private List<String> ingredients;
}
