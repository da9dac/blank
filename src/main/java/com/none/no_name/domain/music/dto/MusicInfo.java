package com.none.no_name.domain.music.dto;

import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.music.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MusicInfo {

    private String artistName;
    private String albumName;
    private Long musicTime;
    private String albumCoverImg;
    private String musicUri;
    private int musicLikeCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedAt;
    private List<String> musicTagName;

    public static MusicInfo of(Music music) {

        return MusicInfo.builder()
                .artistName(music.getArtistName())
                .albumName(music.getAlbumName())
                .musicTime(Long.valueOf(music.getMusicTime()))
                .albumCoverImg(music.getAlbumCoverImag())
                .musicUri(music.getMusicUrl())
                .musicLikeCount(music.getMusicLikeCount())
                .createdDate(music.getCreatedDate())
                .modifiedAt(music.getModifiedDate())
                .build();
    }
}
