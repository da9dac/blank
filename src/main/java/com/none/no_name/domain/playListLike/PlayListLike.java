package com.none.no_name.domain.playListLike;


import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.playList.PlayList;
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
public class PlayListLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playListLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "playList_id")
    private PlayList playList;
}
