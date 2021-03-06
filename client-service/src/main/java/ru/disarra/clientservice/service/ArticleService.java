package ru.disarra.clientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.disarra.clientservice.dto.ArticleDTO;
import ru.disarra.clientservice.dto.ArticleWithoutContentDTO;
import ru.disarra.clientservice.entity.Article;
import ru.disarra.clientservice.exception.ArticleNotFoundException;
import ru.disarra.clientservice.repository.ArticleRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<ArticleWithoutContentDTO> getPage(int pageNum, int itemsOnPage) {
        return articleRepository.findAll(
                PageRequest.of(
                        pageNum,
                        itemsOnPage,
                        Sort.by("posted"))
        ).map(ArticleWithoutContentDTO::of);
    }

    public ArticleDTO getByTitle(String title) throws ArticleNotFoundException {
        return ArticleDTO.of(
                articleRepository.findByTitle(title)
                .orElseThrow(ArticleNotFoundException::new)
        );
    }
}
