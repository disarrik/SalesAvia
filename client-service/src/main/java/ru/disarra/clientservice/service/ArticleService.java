package ru.disarra.clientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.disarra.clientservice.dto.ArticleDTO;
import ru.disarra.clientservice.dto.ArticleWithoutContentDTO;
import ru.disarra.clientservice.exception.ArticleNotFoundException;
import ru.disarra.clientservice.repository.ArticleRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<ArticleWithoutContentDTO> getPage(int pageNum, int itemsOnPage) {
        return articleRepository.findAllProjectedBy(
                PageRequest.of(
                        pageNum,
                        itemsOnPage,
                        Sort.by("posted"))
        );
    }

    public ArticleDTO getBiTitle(String title) throws ArticleNotFoundException {
        return articleRepository.findByTitle(title)
                .orElseThrow(ArticleNotFoundException::new);
    }
}
