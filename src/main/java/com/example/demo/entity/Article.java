package com.example.demo.entity;

import com.example.demo.dto.ArticleForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

// DB에서 인식할 수 있도록 @Entity 어노테이션 선언
@Entity
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자를 추가!
@ToString
@Getter
public class Article {

    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성해주는 어노테인션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }


    // 롬복의 @AllArgsConstructor가 대신 일해줌
    // Entity를 만들기 위해서 생성자를 추가
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

    // 롬복의 @ToString가가 대신 해줌
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

     //롬복의 @NoArgsConstructor 대신 해줌
//    Article(){
//
//    }
}

