package com.study.bootvue.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostSearch {

    private static final int MAX_SIZE = 2000;
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

   /* @Builder
    public PostSearch(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }*/
    public long getOffset() {
        return (long) (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }
}
