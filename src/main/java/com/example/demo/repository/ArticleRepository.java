package com.example.demo.repository;


import com.example.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/*
* CrudRepository<A, B>
* A(첫번째 인자) - 관리 대상 엔티티를 넣어주어야함
* B(두번째 인자) - 관리 대상 엔티티에서 대표값을 타입을 넣어준다.
* */
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
