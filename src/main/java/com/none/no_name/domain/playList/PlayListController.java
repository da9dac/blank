package com.none.no_name.domain.playList;


import com.none.no_name.global.response.ApiPageResponse;
import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class PlayListController {

    //플리 생성
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createPlayList() {
        return null;
    }

    //플리 단건 조회
    @GetMapping
    public ResponseEntity<ApiSingleResponse<PlayListInfo>> getPlayList() {
        return null;
    }

    //플리 전체 조회 - 페이징
    @GetMapping
    public ResponseEntity<ApiPageResponse<PlayListInfo>> getAllPlayList() {
        return null;
    }

    //좋아요한 플리 조회 - 페이징
    @GetMapping
    public ResponseEntity<ApiPageResponse<PlayListInfo>> getLikePlayList() {
        return null;
    }

    //플리 수정
    @PatchMapping
    public ResponseEntity<Void> updatePlayList() {
        return null;
    }

    //플리 삭제
    @DeleteMapping
    public ResponseEntity<Void> deletePlayList() {
        return null;
    }

    //플리에서 음원 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteMusicInPlayList() {
        return null;
    }

    //플리에 음원 추가
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> addMusicInPlayList() {
        return null;
    }




}
