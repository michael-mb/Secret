package com.auth.template.demo.scopes.articles.controller;

import com.auth.template.demo.scopes.articles.entities.Article;
import com.auth.template.demo.scopes.articles.forms.ArticleDto;
import com.auth.template.demo.scopes.articles.services.ArticleService;
import com.auth.template.demo.scopes.token.TokenServiceImpl;
import com.auth.template.demo.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    TokenServiceImpl tokenService;

    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto articleDto){
        if(articleDto == null)
            return ResponseEntity.badRequest()
                    .body("ArticleDto is null");
        if(articleDto.title == null || articleDto.text == null || articleDto.date == null || articleDto.ownerID == null)
            return  ResponseEntity.badRequest()
                    .body("One of the parameters is null");

        return ResponseEntity.ok(articleService.createArticle(new Article(articleDto.text, articleDto.title, articleDto.ownerID, articleDto.date)));
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllArticles(@RequestHeader (name="Authorization") Optional<String> token){


        if(token.isEmpty())
            return ResponseEntity.badRequest()
                    .body("The Token is empty");

        Long userId = null;
        try {
            userId = userService.getIdFromEmail(tokenService.getEmailFromToken(token.get()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("The Token is broken");
        }


        return ResponseEntity.ok(articleService.findArticlesWithUserId(userId));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findArticleById(@RequestParam("id")Optional<Long> articleId , @RequestHeader (name="Authorization") Optional<String> token){
        if(articleId.isEmpty())
            return ResponseEntity.badRequest()
                    .body("ArticleId is null");

        Article article = articleService.findArticleByID(articleId.get());

        if(token.isEmpty())
            return ResponseEntity.badRequest()
                    .body("The Token is empty");

        if(article == null)
            return  ResponseEntity.badRequest()
                    .body("The article with this id is null");

        if(!tokenService.getEmailFromToken(token.get()).equals(userService.getEmailFromId(article.getOwnerID())))
            return  ResponseEntity.badRequest()
                    .body("The article is not yours");

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteArticleById(@RequestParam("id")Optional<Long> articleId , @RequestHeader (name="Authorization") Optional<String> token){
        if(articleId.isEmpty())
            return ResponseEntity.badRequest()
                    .body("ArticleId is null");

        if(token.isEmpty())
            return ResponseEntity.badRequest()
                    .body("The Token is empty");

        articleService.deleteArticleByID(articleId.get());
        return ResponseEntity.ok("Article is deleted");
    }

    @PutMapping("/")
    public ResponseEntity<?> updateArticleById(@RequestBody ArticleDto articleDto , @RequestHeader (name="Authorization") Optional<String> token){
        if(articleDto == null)
            return ResponseEntity.badRequest()
                    .body("ArticleDto is null");
        if(articleDto.title == null || articleDto.text == null || articleDto.date == null || articleDto.ownerID == null)
            return  ResponseEntity.badRequest()
                    .body("One of the parameters is null");

        if(token.isEmpty())
            return ResponseEntity.badRequest()
                    .body("The Token is empty");

        Article article = new Article(articleDto.text, articleDto.title, articleDto.ownerID, articleDto.date);
        System.out.println(articleDto);
        article.setId(articleDto.id);

        return ResponseEntity.ok(articleService.updateArticle(article));
    }
}
