package com.none.no_name.domain.tag;


import com.none.no_name.domain.playListTag.PlayListTagInfo;
import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class TagController {

    //태그 생성
    @PostMapping
    public ResponseEntity<ApiSingleResponse<TagInfo>> createTag() {
        return null;
    }

    //태그 전체 조회 - 리스트 형식으로 ..?
    @GetMapping
    public ResponseEntity<ApiSingleResponse<TagInfo>> getTags() {
        return null;
    }

    //태그 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteTag() {
        return null;
    }

    //태그 수정
    @PatchMapping
    public ResponseEntity<ApiSingleResponse<Void>> updateTag() {
        return null;
    }
}
