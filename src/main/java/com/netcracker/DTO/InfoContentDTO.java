package com.netcracker.DTO;

public class InfoContentDTO {

    private String Subject;
    private String text;

    public InfoContentDTO() {
    }


    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
