package com.epicodus.discussionforum.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 7/11/16.
 */
@Parcel
public class Pigeonhole {
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    private String name;

    public Pigeonhole() {}

    public Pigeonhole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
