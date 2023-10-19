package com.none.no_name.domain.musicComment;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentInfo {
    private Long memberId;
    private String content;
}
