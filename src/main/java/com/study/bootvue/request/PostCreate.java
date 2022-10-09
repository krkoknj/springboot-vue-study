package com.study.bootvue.request;

import lombok.Setter;

@Setter
public class PostCreate {

    public String title;
    public String content;


    @Override
    public String toString() {
        return "PostCreate{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
