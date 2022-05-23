package ru.disarra.clientservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.disarra.clientservice.dto.ArticleDTO;
import ru.disarra.clientservice.dto.ArticleWithoutContentDTO;
import ru.disarra.clientservice.entity.Article;

import java.util.Optional;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    Page<Article> findAllProjectedBy(Pageable pageable);
    Optional<Article> findByTitle(String title);
}
