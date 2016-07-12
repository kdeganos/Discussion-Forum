package com.epicodus.discussionforum.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 7/11/16.
 */
@Parcel
public class Pigeonhole {

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

}
