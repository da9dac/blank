package com.none.no_name.domain.musicTag;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagOfMusic {
    private Long tagOfMusicId;
    private Long musicId;
    private Long tagId;
    private String tagName;
}
