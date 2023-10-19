package com.none.no_name.domain.playListLike;


import com.none.no_name.domain.playList.PlayListInfo;
import com.none.no_name.global.response.ApiPageResponse;
import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PlayListLikeController {

    //좋아요, 좋아요 취소
    @PatchMapping
    public ResponseEntity<ApiSingleResponse<Boolean>> updateLike() {
        return null;
    }

    //플리의좋아요 전체조회
    @GetMapping
    public ResponseEntity<ApiPageResponse<PlayListLikeInfo>> getLikes() {
        return null;
    }
}
