package com.none.no_name.domain.musicLike;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLikeMusic {
    private Long singMusicLikeId;
    private Long memberId;
    private Long musicId;
    private String musicName;
    private String artistName;
    private String albumName;
}
