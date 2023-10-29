package com.none.no_name.domain.playList.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListInfo {
    private Long playListId;
    private Long memberId;
    private String title;
    private String coverImg;
    private List<String> tags;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
