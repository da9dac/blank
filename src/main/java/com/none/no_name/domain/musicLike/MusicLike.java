package com.none.no_name.domain.musicLike;


import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.music.entity.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MusicLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "music_id")
    private Music music;
}
