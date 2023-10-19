package com.none.no_name.domain.musicTag;


import com.none.no_name.domain.music.entity.Music;
import com.none.no_name.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Lazy;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MusicTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicTagId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column(nullable = false)
    private String name;
}
