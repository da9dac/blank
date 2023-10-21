package com.none.no_name.domain.playList.entity;

import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.playListComment.entity.PlayListComment;
import com.none.no_name.domain.playListLike.entity.PlayListLike;
import com.none.no_name.domain.playListMusic.entity.PlayListMusic;
import com.none.no_name.domain.playListTag.entity.PlayListTag;
import com.none.no_name.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playListId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String createMember;

    @Column(nullable = false)
    private String title;

    private String coverImg;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "playList", cascade = ALL)
    private List<PlayListTag> playListTags = new ArrayList<>();

    @OneToMany(mappedBy = "playList", cascade = ALL)
    private List<PlayListLike> playListLikes = new ArrayList<>();

    @OneToMany(mappedBy = "playList", cascade = ALL)
    private List<PlayListComment> playListComments = new ArrayList<>();

    @Column(nullable = false)
    private int likeCount;

    @OneToMany(mappedBy = "playList", cascade = ALL)
    private List<PlayListMusic> playListMusics = new ArrayList<>();
}
