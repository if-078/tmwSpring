/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softserve.if078.tmwSpring.entities;

/**
 *
 * @author Oleg
 */
public class Tag {

    public int tagId;
    public String name;
    public int userId;


    public Tag(int tagId, String name, int userId) {
        this.tagId = tagId;
        this.name = name;
        this.userId = userId;
    }

    public Tag(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public Tag() {
    }

    public void setId(int id) {
        this.tagId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return tagId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tag{" + "tadId=" + tagId + ", name=" + name + ", userId=" + userId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.tagId;
        hash = 89 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (this.tagId != other.tagId) {
            return false;
        }

        return true;
    }
}


