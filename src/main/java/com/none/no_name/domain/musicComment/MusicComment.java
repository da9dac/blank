package com.none.no_name.domain.musicComment;


import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.music.entity.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MusicComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicCommentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "music_id")
    private Music music;
}
