package com.none.no_name.domain.music.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicUpdateControllerApi {
    private String musicName;
    private String artistName;
    private String albumName;
    private int musicTime;
    private String albumCoverImg;
    private String musicUrl;
    private List<String> tags;


    public MusicUpdateServiceApi toService() {
        return MusicUpdateServiceApi.builder()
                .musicName(musicName)
                .artistName(artistName)
                .albumName(albumName)
                .musicTime(musicTime)
                .albumCoverImg(albumCoverImg)
                .musicUrl(musicUrl)
                .tags(tags)
                .build();
    }
}
