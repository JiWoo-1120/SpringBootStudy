package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {

//        /* for문을 이용한 엔티티를 DTO로 변환하기 */
//        // 조희: 댓글 목록 (엔티티를 찾는다)
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 변환: 엔티티 -> DTO (엔티티에 담긴걸 for문을 이용해 하나씩 빼서 dto에 넣어준다.
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
////        }
//        // 반환
//        return dtos;

        // 반환 (스트림 문법)
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional //DB를 건드리는 일이기 때문에 문제가 생기면 롤백이 필요하다.
    public CommentDto create(Long articleId, CommentDto dto) {
        // 댓글을 작성할 게시물 찾기 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 (대상 게시글이 없습니다.)"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComent(dto, article);

        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글 조회(댓글이 없으면 예외발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 댓글 대상이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
   }

    @Transactional
    public CommentDto delete(Long id) {

        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 댓글이 없습니다."));

        // 댓글 DB에서 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);

    }
}
