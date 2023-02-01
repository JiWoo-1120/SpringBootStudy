package com.example.demo.dto;


import com.example.demo.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 생성자 자동 완성
@NoArgsConstructor  // 기본 생성자 자동 추가
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(), // 아티클의 id를 넣어주어야하기때문에
                comment.getNickname(),
                comment.getBody()
        );
    }
}
