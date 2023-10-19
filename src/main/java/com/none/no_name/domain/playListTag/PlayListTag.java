package com.none.no_name.domain.playListTag;


import com.none.no_name.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PlayListTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playListTagId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column(nullable = false)
    private String name;
}
