package com.auth.template.demo.scopes.articles.forms;

import com.auth.template.demo.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticleDto {

    @NotBlank
    public Long id;

    @NotBlank
    public String title;

    @NotBlank
    public String text;

    @NotBlank
    public String date;

    public Long ownerID;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", ownerID=" + ownerID +
                '}';
    }
}
