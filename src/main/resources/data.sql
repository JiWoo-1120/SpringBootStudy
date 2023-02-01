-- article 더미 데이터
INSERT INTO article(title, content) VALUES ('가가가가', '1111');
INSERT INTO article(title, content) VALUES ('나나나나', '2222');
INSERT INTO article(title, content) VALUES ('다다다다', '3333');


INSERT INTO article(title, content) VALUES ('당신의 인생 영화는?', '댓글 ㄱ');
INSERT INTO article(title, content) VALUES ('당신의 소울 푸드는?', '댓글 ㄱㄱ');
INSERT INTO article(title, content) VALUES ('당신의 취미는 ', '댓글 ㄱㄱㄱ');


-- comment 더미 데이터
---- 4번 게시글의 댓글들
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'JIWOO', '인생은 아름다워');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'KIM', '쇼생크의 탈출');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'CHOI', '으아어아');

---- 5번 게시글의 댓글들
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'JIWOO', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'KIM', '초밥');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'CHOI', '짜장밥');

---- 6번 게시글의 댓글들
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'JIWOO', '요가');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'KIM', '유튜브');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'CHOI', '독서');