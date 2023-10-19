package com.none.no_name.domain.musicLike;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeInfo {
    private Long musicLikeId;
    private Long memberId;
    private Long musicId;
    private String message;

    public static LikeInfo of(Long musicLikeId, Long memberId, Long musicId, String message) {
        return LikeInfo.builder()
                .musicLikeId(musicLikeId)
                .memberId(memberId)
                .musicId(musicId)
                .message(message)
                .build();
    }
}
