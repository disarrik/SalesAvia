package ru.disarra.clientservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.disarra.clientservice.dto.ArticleDTO;
import ru.disarra.clientservice.dto.ArticleWithoutContentDTO;
import ru.disarra.clientservice.entity.Article;
import ru.disarra.clientservice.entity.Author;
import ru.disarra.clientservice.exception.ArticleNotFoundException;
import ru.disarra.clientservice.repository.ArticleRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    private final List<Article> articles = List.of(
            new Article(
                    "title1",
                    "shortDescription1",
                    "content1",
                    new Author(
                            "name1",
                            "surname1"
                    ),
                    "url1",
                    ZonedDateTime.now()
            ),
            new Article(
                    "title2",
                    "shortDescription2",
                    "content2",
                    new Author(
                            "name2",
                            "surname2"
                    ),
                    "url2",
                    ZonedDateTime.now()
            )
    );


    @Test
    void getPageTest() {
        //given
        Mockito
                .when(articleRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(articles));
        ArticleService articleService= new ArticleService(articleRepository);

        //when
        Page<ArticleWithoutContentDTO> result = articleService.getPage(1, 3);

        //then
        Assertions.assertEquals(
                articles.stream().map(ArticleWithoutContentDTO::of).collect(Collectors.toList()),
                result.toList()
        );

    }

    @Test
    void getByTitleSuccessTest() {
        //given
        Optional<Article> article = Optional.of(articles.get(0));
        Mockito
                .when(articleRepository.findByTitle(any(String.class)))
                .thenReturn(article);
        ArticleService articleService= new ArticleService(articleRepository);

        //when
        ArticleDTO result = articleService.getByTitle("any");

        //then
        Assertions.assertEquals(
                ArticleDTO.of(article.get()),
                result
        );
    }

    @Test
    void getByTitleFailureTest() {
        //given
        Optional<Article> article = Optional.of(articles.get(0));
        Mockito
                .when(articleRepository.findByTitle(any(String.class)))
                .thenThrow(ArticleNotFoundException.class);
        ArticleService articleService= new ArticleService(articleRepository);

        //when then
        Assertions.assertThrows(
                ArticleNotFoundException.class,
                () -> articleService.getByTitle("any")
        );
    }
}