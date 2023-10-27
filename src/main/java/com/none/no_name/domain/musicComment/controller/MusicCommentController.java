package com.none.no_name.domain.musicComment.controller;


import com.none.no_name.domain.musicComment.dto.CommentInfo;
import com.none.no_name.domain.musicComment.dto.CommentSort;
import com.none.no_name.domain.musicComment.dto.MusicCommentResponse;
import com.none.no_name.domain.musicComment.dto.MusicCreateApi;
import com.none.no_name.domain.musicComment.service.MusicCommentService;
import com.none.no_name.global.annotation.LoginId;
import com.none.no_name.global.base.BaseEntity;
import com.none.no_name.global.response.ApiPageResponse;
import com.none.no_name.global.response.ApiSingleResponse;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class MusicCommentController {

    private final MusicCommentService musicCommentService;


    //음원 댓글 전체 조회
    @GetMapping("/{comment-id}")
    public ResponseEntity<ApiPageResponse<CommentInfo>> getComments(@PathVariable("comment-id") @Positive(message = "{validation.positive}") Long commentId,
                                                                    @LoginId Long loginMemberId,
                                                                    @Positive(message = "{validation.positive}") @RequestParam(defaultValue = "1") int page,
                                                                    @Positive(message = "{validation.positive}") @RequestParam(defaultValue = "5") int size,
                                                                    @RequestParam(defaultValue = "created-date") CommentSort sort,
                                                                    @RequestParam(required = false) @Positive(message = "{validation.positive}") int like) {

        Page<CommentInfo> comments = musicCommentService.getComments(commentId, page-1, size, sort, like);

        return ResponseEntity.ok(ApiPageResponse.ok(comments, "댓글 전체 조회 성공"));
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity<ApiSingleResponse<Void>> updateComment(@PathVariable("comment-id") @Positive(message = "{validation.positive}") Long commentId,
                                                                 @LoginId Long loginMemberId,
                                                                 @RequestBody MusicCommentResponse response) {

        musicCommentService.updateComment(commentId, loginMemberId, response.toService());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("comment-id") @Positive(message = "{validation.positive}") Long commentId,
                                               @LoginId Long loginMemberId) {

        musicCommentService.deleteComment(commentId, loginMemberId);

        return ResponseEntity.noContent().build();
    }


}
