package me.farmingwhale.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.farmingwhale.springbootdeveloper.domain.Article;
import me.farmingwhale.springbootdeveloper.dto.ArticleListViewResponse;
import me.farmingwhale.springbootdeveloper.dto.ArticleViewResponse;
import me.farmingwhale.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream() // 순환 가능한 스트림 객체로 만듬
                .map(ArticleListViewResponse::new) // new를 통해 생성자 만드는걸 더 간결하게 :: 로 한거
                .toList();
        model.addAttribute("articles", articles); //블로그 글 리스트 저장

        return "articleList"; // articleList.html라는 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) { //@PathVariable : url에 들어오는 변수를 받음
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // id 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
