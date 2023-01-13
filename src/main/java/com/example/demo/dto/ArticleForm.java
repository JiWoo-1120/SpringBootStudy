package com.example.demo.dto;


import com.example.demo.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO는 form 데이터를 받아올 그릇
@AllArgsConstructor
@ToString
@NoArgsConstructor // 19강 생성자추가에러 해결 (InvalidDefinitionException)
@Setter
public class ArticleForm {

    private Long id; // id 필드 추가!!
    private String title;
    private String content;

//    public ArticleForm(){} // 19강 생성자추가에러 해결 (InvalidDefinitionException)

    public Article toEntity() {
        return new Article(id, title, content);
    }

    //롬복의 @AllArgsConstructor 어노테이션이 대신 처리해줌
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }


    //롬복의 @ToString 어노테이션이 대신 처리해줌
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    // Article 타입을 반환하길 원함
    // 새롭게 Article을 만들어서 return 해준다.
    // Article은 엔티티였다. 엔티티 클래스의 객체를 생성해야 하니까 생성자를 호출해줘야한다.

}
