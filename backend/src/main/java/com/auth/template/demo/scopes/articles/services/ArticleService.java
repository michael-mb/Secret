package com.auth.template.demo.scopes.articles.services;

import com.auth.template.demo.scopes.articles.entities.Article;
import com.auth.template.demo.scopes.articles.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article createArticle(Article article){
        if(article == null) throw new NullPointerException("Article must not be null");
        return articleRepository.save(article);
    }

    public Article findArticleByID(Long id){
        if(id == null) throw new NullPointerException("Article ID must not be null");
        Optional<Article> article = articleRepository.findById(id);

        if(article.isEmpty())
            return null;
        else
            return article.get();
    }

    public void deleteArticleByID(Long id){
        if(id == null) throw new NullPointerException("Article ID must not be null");
        articleRepository.deleteById(id);
    }

    public Article updateArticle(Article article){
        return articleRepository.save(article);
    }

    public List<Article> findArticlesWithUserId(Long id){
        return articleRepository.findArticleByOwnerID(id);
    }
}
