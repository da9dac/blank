package com.none.no_name.domain.playList;


import com.none.no_name.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PlayListInfo {
    private Long playListId;
    private Long memberId;
    private Member member;
    private String title;
    private String coverImg;
    private List<String> tags;
    private int likeCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
