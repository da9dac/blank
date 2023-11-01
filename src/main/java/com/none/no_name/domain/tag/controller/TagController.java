package com.none.no_name.domain.tag.controller;


import com.none.no_name.domain.musicTag.dto.TagInfo;
import com.none.no_name.domain.tag.dto.CategoryInfo;
import com.none.no_name.domain.tag.dto.TagResponseApi;
import com.none.no_name.domain.tag.service.TagService;
import com.none.no_name.global.annotation.LoginId;
import com.none.no_name.global.base.BaseEntity;
import com.none.no_name.global.response.ApiPageResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tags")
@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @DeleteMapping("/{tag-id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("tag-id") @Positive(message = "validation.positive") Long tagId,
                                          @LoginId Long loginMember) {

        tagService.deleteTag(tagId, loginMember);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{tag-id}")
    public ResponseEntity<Void> updateTag(@PathVariable("tag-id") @Positive(message = "validation.positive") Long tagId,
                                          @LoginId Long loginMemberId,
                                          @RequestBody TagInfo tagInfo) {

        tagService.updateTag(tagId, loginMemberId, tagInfo);

        return ResponseEntity.noContent().build();
    }

    //태그 전체 조회
    @GetMapping
    public ResponseEntity<ApiPageResponse<TagInfo>> getTags(@LoginId Long loginMemberId,
                                                            @RequestParam  @Positive(message = "validation.positive") int page,
                                                            @RequestParam  @Positive(message = "validation.positive") int size) {

        Page<TagInfo> tags = tagService.getTags(loginMemberId, page, size);

        return ResponseEntity.ok(ApiPageResponse.ok(tags, "태그 전체 조회가 완료되었습니다."));
    }

}

