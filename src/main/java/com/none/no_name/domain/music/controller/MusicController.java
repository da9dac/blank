package com.none.no_name.domain.music.controller;


import com.none.no_name.domain.music.dto.MusicInfo;
import com.none.no_name.global.response.ApiPageResponse;
import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class MusicController {


    //음원 개별 조회
    @GetMapping("{music-id}")
    public ResponseEntity<ApiSingleResponse<MusicInfo>> getMusic() {
        return null;
    }

    //음원 등록
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createMusic() {
        return null;
    }

    //음원 전체조회(페이징) - 내림차순, 좋아요순, 생성일 순
    @GetMapping
    public ResponseEntity<ApiPageResponse<MusicInfo>> getMusics() {
        return null;
    }

    //유저 음원 조회
    @GetMapping
    public ResponseEntity<ApiPageResponse<MusicInfo>> getUserMusics() {
        return null;
    }

    //플리 안에 있는 음원 조회
    @GetMapping
    public ResponseEntity<ApiPageResponse<MusicInfo>> getPlayListMusics() {
        return null;
    }

    //음원 수정
    @PatchMapping
    public ResponseEntity <ApiSingleResponse<Void>> patchMusic() {
        return null;
    }

    //음원 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteMusic() {
        return null;
    }
}
