package com.none.no_name.domain.musicLike;


import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class MusicLikeController {

    //음원 좋아요 생성,취소
    @PostMapping
    public ResponseEntity<ApiSingleResponse<LikeInfo>> postLikeMusic() {
        return null;
    }

    //음원 좋아요 개별 조회
    @GetMapping
    public ResponseEntity<ApiSingleResponse<UserLikeMusic>> getMusicLike() {
        return null;
    }

    //user의 좋아요 전체목록 조회
    @GetMapping
    public ResponseEntity<ApiSingleResponse<UserLikeMusic>> getMusicsLike() {
        return null;
    }



}
