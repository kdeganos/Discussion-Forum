package com.epicodus.discussionforum.models;

/**
 * Created by Guest on 7/11/16.
 */
public class Comment {
    String author;
    String content;

    public Comment() {

    }

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
