package com.none.no_name.domain.music.entity;


import com.none.no_name.domain.memberMusic.MemberMusic;
import com.none.no_name.domain.musicLike.MusicLike;
import com.none.no_name.domain.musicTag.MusicTag;
import com.none.no_name.domain.plyaListMusic.PlayListMusic;
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
    private String musicTime;

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


}
