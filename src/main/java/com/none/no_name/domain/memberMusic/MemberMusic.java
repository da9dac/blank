package com.none.no_name.domain.memberMusic;


import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.music.entity.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Entity
public class MemberMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberMusicId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "music_id")
    private Music music;
}
