package com.agritech.model;

public class ArticlesModel {
    private String articleTitle, articleDesc, articleCategory, articleInputDate;
    public ArticlesModel() {}

    public ArticlesModel(String articleTitle, String articleDesc, String articleCategory, String articleInputDate) {
        this.articleTitle = articleTitle;
        this.articleDesc = articleDesc;
        this.articleCategory = articleCategory;
        this.articleInputDate = articleInputDate;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleInputDate() {
        return articleInputDate;
    }

    public void setArticleInputDate(String articleInputDate) {
        this.articleInputDate = articleInputDate;
    }
}
