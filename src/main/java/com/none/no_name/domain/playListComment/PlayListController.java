package com.none.no_name.domain.playListComment;


import com.none.no_name.domain.musicComment.CommentInfo;
import com.none.no_name.global.response.ApiPageResponse;
import com.none.no_name.global.response.ApiSingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class PlayListController {

    //댓글 생성
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createComment() {
        return null;
    }

    //댓글 전체 조회
    @GetMapping
    public ResponseEntity<ApiPageResponse<CommentInfo>> getComments() {
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
