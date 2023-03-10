package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");

       List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        //예상
        Long id = -1L;
        Article expected = null;

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title_content만_있는_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(2L, title, content);
        Article expected = null;

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title_content가_있는_dto_입력() {
        //예상
        Long id = 1L;
        String title = "1번 제목 수정";
        String content = "1번 내용 수정";
        ArticleForm dto = new ArticleForm(id, title, content); // 처음에 들어오는 데이터는 DTO로 넘어오기 때문에
        Article expected = new Article(id, title, content);    // DTO로 넘겨준 후 그 값을 엔티티로 바꿔준다.

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title만_있는_dto_입력() {
        //예상
        Long id = 1L;
        String title = "안녕하세영";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id ,title, null);
        Article expected = new Article(id, title, content);

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected.toString(), article.toString());

    }


    @Test
    @Transactional
    void update_실패___존재하지_않는_id의_dto_입력() {
        //예상
        Long id = 4L;
        String title = "안녕하세영";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id ,title, content);
        Article expected = null;

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void update_실패___id만_있는_dto_입력() {
        //예상
        Long id = 1L;
        String title = null;
        String content = null;
        ArticleForm dto = new ArticleForm(id ,title, content);
        Article expected =  new Article(id, "가가가가", "1111");

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    @Transactional
    void delete_성공___존재하는_id_입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(1L, "가가가가", "1111");

        //실제
        Article article = articleService.delete(id);

        //비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    @Transactional
    void delete_실패___존재하지_않는_id_입력() {
        //예상
        Long id = 4L;
        Article expected = null;

        //실제
        Article article = articleService.delete(id);

        //비교
        assertEquals(expected, article);
    }

}