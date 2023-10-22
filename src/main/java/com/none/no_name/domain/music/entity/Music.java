package com.none.no_name.domain.music.entity;

import com.none.no_name.domain.memberMusic.entity.MemberMusic;
import com.none.no_name.domain.music.dto.CreateMusic;
import com.none.no_name.domain.music.dto.MusicInfo;
import com.none.no_name.domain.musicLike.entity.MusicLike;
import com.none.no_name.domain.musicTag.entity.MusicTag;
import com.none.no_name.domain.playListMusic.entity.PlayListMusic;
import com.none.no_name.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
@Builder
public class Music extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    @Column(nullable = false)
    private String musicName;

    @Column(nullable = false)
    private String artistName;

    @Column(nullable = false)
    private Long musicTime;

    private String albumCoverImag;

    private String albumName;

    @Column(nullable = false, unique = true)
    private String musicUrl = "";

    @OneToMany(mappedBy = "music", cascade = ALL)
    private List<MusicLike> musicLikes = new ArrayList<>();

    private int musicLikeCount;

    @ElementCollection(fetch = LAZY)
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = ALL)
    private List<MemberMusic> memberMusics = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = ALL)
    private List<PlayListMusic> playListMusics = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = ALL)
    private List<MusicTag> musicTags = new ArrayList<>();

    private int likes;


    public static Music createMusic(Long loginMemberId, CreateMusic createMusic) {
        return Music.builder()
                .musicName(builder().musicName)
                .artistName(builder().artistName)
                .albumName(builder().albumName)
                .musicTime(builder().musicTime)
                .albumCoverImag(builder().albumCoverImag)
                .musicUrl(builder().musicUrl)
                .build();
    }

    public void updateMusic(String musicName,
                            String artistName,
                            String albumName,
                            int musicTime,
                            String albumCoverImag) {

        this.musicName = musicName == null ? this.musicName : musicName;
        this.artistName = artistName == null ? this.artistName : artistName;
        this.albumName = albumName == null ? this.albumName : albumName;
        this.musicTime = (musicTime == 0) ? this.musicTime : musicTime;
        this.albumCoverImag = albumCoverImag == null ? this.albumCoverImag : albumCoverImag;
    }

    public void addLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        this.likes--;
    }

    public static Music addMusic(Long musicId, MusicInfo musicInfo) {
        return Music.builder()
                .musicId(musicId)
                .artistName(musicInfo.getArtistName())
                .artistName(musicInfo.getAlbumName())
                .musicTime(musicInfo.getMusicTime())
                .albumCoverImag(musicInfo.getAlbumCoverImg())
                .musicUrl(musicInfo.getMusicUri())
                .build();

    }
}

