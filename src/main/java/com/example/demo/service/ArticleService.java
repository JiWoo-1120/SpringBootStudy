package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성) 여기서 객체가 만들어지니까 Controller에서 @Autowired 할 수 있다.
public class ArticleService {

    //해당 서비스가 ArticleRepository와 협업할 수 있도록 필드 선언
    @Autowired //DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article delete(Long id) {

        Article article = articleRepository.findById(id).orElse(null);

        if(article == null) {
            return null;
        }

        articleRepository.delete(article);
        return article;
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) {   // 이미 있는 게시물을 수정하지 못하도록
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1. 수정 용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article:{}",id,article.toString());

        // 2. 대상 엔티티를 조회
        Article target= articleRepository.findById(id).orElse(null);


        // 3. 잘못된 요청 처리(대상이 없거나, id가 달느 경우)
        if (target == null || id != article.getId()){
            // 400, 잘못된 요청 응답!
            return null;
        }

        // 4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다! 해당 메소드가 수행되다가 실패가 되면 해당 메소드가 실행되기 이전 상태로 롤백한다.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 entity 묶음으로 변환
        List<Article> articleList =  dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. entity 묶음을 db로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결재 실패!")

        );

        // 결과값 반환
        return articleList;
    }
}




