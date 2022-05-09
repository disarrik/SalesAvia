package ru.disarra.clientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.disarra.clientservice.service.ArticleService;

@Controller
public class IndexController {
    private final ArticleService articleService;

    @Autowired
    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute(
                "slides",
                articleService.getPage(0, 3)
        );
        return "index";
    }
}
