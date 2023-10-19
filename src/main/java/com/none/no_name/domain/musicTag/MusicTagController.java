package com.none.no_name.domain.musicTag;


import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MusicTagController {

    //음원 태그 등록
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createTag() {
        return null;
    }

    //음원 태그 조회
    @GetMapping
    public ResponseEntity<ApiSingleResponse<TagOfMusic>> getTag() {
        return null;
    }

    //음원 태그 단일 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteTag() {
        return null;
    }

    //음원 태그 전체 삭제

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTag() {
        return null;
    }
}
