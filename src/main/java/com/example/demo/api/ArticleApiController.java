package com.example.demo.api;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PipedOutputStream;
import java.util.List;

@Slf4j // 로그를 찍어볼 수 있게 해줌
@RestController // RESTAPI 용 컨트롤러! 데이터(JSON)를 반환
public class ArticleApiController {

    @Autowired // DI(스프링부트에서 떙겨오기 위해 사용)
    private ArticleRepository articleRepository;

    // GET 리스트
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // GET 단일
    @GetMapping("/api/articles/{id}")
    public Article index1(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // 새로 글을 작성할때는 처음에 dto로 formdata를 받아온다.
    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article articleEntity = dto.toEntity();
        return articleRepository.save(articleEntity);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        // 1. 수정 용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article:{}", id, article.toString());

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 달느 경우)
        if (target == null || id != article.getId()){
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상응답(200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if(target == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 대상 삭제
        articleRepository.delete(target);

        // 데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
