package com.none.no_name.domain.plyaListMusic;


import com.none.no_name.domain.music.entity.Music;
import com.none.no_name.domain.playList.PlayList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class PlayListMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playListMusicId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "playList_id")
    private PlayList playList;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "music_id")
    private Music music;
}
