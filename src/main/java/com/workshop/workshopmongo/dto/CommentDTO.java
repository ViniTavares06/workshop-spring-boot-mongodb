package com.workshop.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private Date date;
    private AuthorDTO authot;

    public CommentDTO() {
    }

    public CommentDTO(String text, Date date, AuthorDTO authot) {
        this.text = text;
        this.date = date;
        this.authot = authot;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AuthorDTO getAuthot() {
        return authot;
    }

    public void setAuthot(AuthorDTO authot) {
        this.authot = authot;
    }
}
