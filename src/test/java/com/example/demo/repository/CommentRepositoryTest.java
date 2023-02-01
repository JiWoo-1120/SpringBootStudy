package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired //만들어진 객체 사용하려고 끌어오는 어노테이션
    CommentRepository commentRepository;

    @Test
    @Transactional
    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트 결과에 보여줄 이름
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
             List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행 결과 예상하기
            Article article =  new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "JIWOO", "인생은 아름다워");
            Comment b = new Comment(2L, article, "KIM", "쇼생크의 탈출");
            Comment c = new Comment(3L, article, "CHOI", "으아어아");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }

        /* Case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행 결과 예상하기
            Article article =  new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글의 모든 댓글을 출력!");
        }

        /* Case 3 : 9번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 9L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행 결과 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글은 존재하지 않는다!");

        }

        /* Case 4 : 9999번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 9999L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행 결과 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "9999번 글은 존재하지 않는다!");
        }

//        /* Case 5 : -1번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터 준비
            Long articleId = -1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행 결과 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "9999번 글은 존재하지 않는다!");

        }

    }

    @Test
    @Transactional
    @DisplayName("특정 닉네임의 모든 작성자 조회")
    void findByNickname() {
        /* Case 1 : JIWOO의 모든 댓글 조회*/
        {
            // 입력 데이터 준비
            String nickname = "JIWOO";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행 결과 예상하기
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "인생은 아름다워");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는 ", "댓글 ㄱㄱㄱ"), nickname, "요가");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "JIWOO의 모든 댓글을 출력");

        }
        /* Case 2 : "KIM"의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "KIM";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행 결과 예상하기
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "쇼생크의 탈출");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "초밥");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는 ", "댓글 ㄱㄱㄱ"), nickname, "유튜브");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "KIM의 모든 댓글을 출력");

        }

        /* Case 3 : null의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = null;

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행 결과 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글을 출력");

        }

        /* Case 4 : ""의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "%*%";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행 결과 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글을 출력");

        }

        /* Case 5 : I가 들어가있는 모든 댓글 조회 */
    }
}