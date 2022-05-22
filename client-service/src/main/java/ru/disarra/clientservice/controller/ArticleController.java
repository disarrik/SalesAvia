package ru.disarra.clientservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.disarra.clientservice.dto.ArticleDTO;
import ru.disarra.clientservice.dto.ArticleWithoutContentDTO;
import ru.disarra.clientservice.service.ArticleService;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final int articlesOnOnePage;
    private final ArticleService articleService;

    public ArticleController(@Value("${articles.onOnePage}") int articlesOnOnePage,
                             ArticleService articleService) {
        this.articlesOnOnePage = articlesOnOnePage;
        this.articleService = articleService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(defaultValue = "1") int page) {
        Page<ArticleWithoutContentDTO> articles = articleService.getPage(page-1, articlesOnOnePage);
        model.addAttribute(
                "articles",
                articles
        );
        model.addAttribute(
                "pages",
                IntStream.rangeClosed(1, articles.getTotalPages())
                        .boxed()
                        .collect(Collectors.toList())
        );
        model.addAttribute("currentPage", page);
        return "articles";
    }

    @GetMapping(params = "title")
    public String article(Model model, @RequestParam String title) {
        model.addAttribute(
                "article",
                articleService.getByTitle(title)
        );
        return "article";
    }
}
