package com.none.no_name.domain.musicComment;


import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music-comments")
@RequiredArgsConstructor
public class MusicController {

    //댓글 생성
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createComment() {
        return null;
    }

    //특정 음원의 댓글 조회
    @GetMapping
    public ResponseEntity<ApiSingleResponse<CommentInfo>> getComment() {
        return null;
    }

    //댓글 수정
    @PatchMapping
    public ResponseEntity<ApiSingleResponse<Void>> updateComment() {
        return null;
    }

    //댓글 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteComment() {
        return null;
    }

}
