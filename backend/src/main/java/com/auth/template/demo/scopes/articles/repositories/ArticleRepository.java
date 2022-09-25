package com.auth.template.demo.scopes.articles.repositories;

import com.auth.template.demo.scopes.articles.entities.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    Streamable<Article> findAll();

    List<Article> findArticleByOwnerID(Long id);
}
