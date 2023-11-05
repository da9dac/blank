package com.none.no_name.domain.music.controller;

import com.none.no_name.domain.music.dto.*;
import com.none.no_name.domain.music.service.MusicService;
import com.none.no_name.domain.musicComment.dto.CommentApi;
import com.none.no_name.domain.musicComment.dto.CommentInfo;
import com.none.no_name.domain.musicComment.dto.CommentSort;
import com.none.no_name.domain.musicComment.service.MusicCommentService;
import com.none.no_name.domain.musicLike.service.MusicLikeService;
import com.none.no_name.domain.musicTag.entity.MusicTag;
import com.none.no_name.domain.playListMusic.entity.PlayListMusic;
import com.none.no_name.domain.tag.dto.TagResponseApi;
import com.none.no_name.domain.tag.service.TagService;
import com.none.no_name.global.annotation.LoginId;
import com.none.no_name.global.base.BaseEntity;
import com.none.no_name.global.response.ApiPageResponse;
import com.none.no_name.global.response.ApiSingleResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
@Validated
public class MusicController{

    private final MusicService musicService;
    private final MusicCommentService musicCommentService;
    private final TagService tagService;


    //음원 개별 조회
    @GetMapping("{music-id}")
    public ResponseEntity<ApiSingleResponse<MusicInfo>> getMusic(@PathVariable("music-id") @Positive(message = "{validation.positive}") Long musicId,
                                                                 @LoginId Long loginMemberId) {

        MusicInfo music = musicService.getMusic(musicId, loginMemberId);

        return ResponseEntity.ok(ApiSingleResponse.ok(music, "음원 단건 조회 성공"));
    }

    //음원 등록
    @PostMapping
    public ResponseEntity<ApiSingleResponse<Void>> createMusic(@LoginId Long loginMemberId,
                                                               @RequestBody @Valid MusicCreateApi response) {

        MusicCreateResponse musicCreateUrl = musicService.createMusic(loginMemberId, response.toService());

        URI uri = URI.create("/musics/" + musicCreateUrl);

        return ResponseEntity.created(uri).build();
    }

    //음원 전체조회(페이징) - 내림차순, 좋아요순, 생성일 순
    @GetMapping
    public ResponseEntity<ApiPageResponse<MusicInfo>> getMusics(@Positive(message = "{validation.positive}") @LoginId Long loginMember,
                                                                @RequestParam(defaultValue = "1") @Positive(message = "{validation.positive}") int page,
                                                                @RequestParam(defaultValue = "5") @Positive(message = "{validation.positive}") int size,
                                                                @RequestParam(defaultValue = "created-date") MusicSort sort) {

        Page<MusicInfo> musics = musicService.getMusics(loginMember, page-1, size, sort);

        return ResponseEntity.ok(ApiPageResponse.ok(musics, "음원 전체 조회를 완료하였습니다."));
    }


    //음원 수정
    @PatchMapping("/{music-id}")
    public ResponseEntity <ApiSingleResponse<Void>> updateMusic(@PathVariable("music-id") @Positive(message = "{validation.positive}") Long musicId,
                                                                @LoginId Long loginMemberId,
                                                                @RequestBody @Valid MusicUpdateControllerApi request) {

        musicService.updateMusic(musicId, loginMemberId, request.toService());

        return ResponseEntity.noContent().build();
    }

    //음원 삭제
    @DeleteMapping("/{music-id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable("music-id") @Positive(message = "{validation.positive}") Long musicId,
                                            @LoginId Long loginMember) {

        musicService.deleteMusic(musicId, loginMember);

        return ResponseEntity.noContent().build();
    }

    //음원 댓글 생성
    @PostMapping("/{music-id}/comments")
    public ResponseEntity<ApiSingleResponse<Void>> createComment(@PathVariable("music-id") @Positive(message = "{validation.positive}") Long musicId,
                                                                 @LoginId Long loginMemberId,
                                                                 @RequestBody CommentApi response) {

        Long commentId = musicCommentService.createComment(musicId, loginMemberId, response);

        URI uri = URI.create("/comments/" + commentId);

        return ResponseEntity.created(uri).build();
    }


    //태그 등록
    @PostMapping("/{music-id}/tags")
    public ResponseEntity<Void> createTag(@PathVariable("music-id") @Positive(message = "{validation.positive}") Long musicId,
                                          @LoginId Long loginMember,
                                          @RequestBody @Valid TagResponseApi response) {

        tagService.createTag(musicId, loginMember, response.toService());

        return ResponseEntity.noContent().build();
    }

}
