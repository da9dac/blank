package com.none.no_name.domain.playListTag;


import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class PlayListTagController {

    //태그 생성
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createPlayListTag() {
        return null;
    }

    //태그 조회
    @GetMapping
    public ResponseEntity<ApiSingleResponse<PlayListTagInfo>> getPlayListTag() {
        return null;
    }

    //태그 삭제
    @DeleteMapping
    public ResponseEntity<Void> deletePlayListTag() {
        return null;
    }



}
