package com.none.no_name.domain.musicComment.service;


import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.member.repository.MemberRepository;
import com.none.no_name.domain.music.dto.MusicInfo;
import com.none.no_name.domain.music.entity.Music;
import com.none.no_name.domain.music.repository.MusicRepository;
import com.none.no_name.domain.musicComment.dto.CommentApi;
import com.none.no_name.domain.musicComment.dto.CommentInfo;
import com.none.no_name.domain.musicComment.dto.CommentSort;
import com.none.no_name.domain.musicComment.entity.MusicComment;
import com.none.no_name.domain.musicComment.repository.MusicCommentRepository;
import com.none.no_name.global.exception.business.member.MemberAccessDeniedException;
import com.none.no_name.global.exception.business.music.MusicNotFoundException;
import com.none.no_name.global.exception.business.musicComment.MusicCommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicCommentService {

    private final MemberRepository memberRepository;

    private final MusicRepository musicRepository;

    private final MusicCommentRepository musicCommentRepository;

    public Long createComment(Long musicId, Long loginMemberId, CommentApi request) {

        Member loginMember = verifiedMember(loginMemberId);

        Music music = verifedMusic(musicId);

        MusicComment musicComment = MusicComment.newComment(request, loginMember, music);

        musicCommentRepository.save(musicComment);

        return musicComment.getMusicCommentId();
    }

    public Page<CommentInfo> getComments(int page, int size, CommentSort commentSort) {

        Sort sort = (commentSort == CommentSort.Likes)
                ? Sort.by(Sort.Direction.DESC, "likes", "createdDate")
                : Sort.by(Sort.Direction.DESC, "createdDate");

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<MusicComment> comments = musicCommentRepository.findAll(pageRequest);

        return comments.map(comment -> {
            CommentInfo.CommentInfoBuilder builder = CommentInfo.builder()
                    .commentId(comment.getMusicCommentId())
                    .content(comment.getContent());

            if (comment.getMusic() != null) {
                builder.musicId(comment.getMusic().getMusicId());
            } else {
                throw new MusicNotFoundException();
            }

            return builder.build();
        });
    }


    public void updateComment(Long commentId, Long loginMemberId, CommentApi request) {

        verifiedMember(loginMemberId);

        verifiedComment(commentId);

        MusicComment musicComment = verifiedComment(commentId);

        musicComment.updateMusicComment(request.getContent());
    }

    public void deleteComment(Long commentId, Long loginMemberId) {

        verifiedMember(loginMemberId);

        verifiedComment(commentId);

        musicCommentRepository.deleteById(commentId);
    }

    private MusicComment verifiedComment(Long commentId) {

        return musicCommentRepository.findById(commentId).orElseThrow(MusicCommentNotFoundException::new);
    }


    public Member verifiedMember(Long loginMemberId) {
        return memberRepository.findById(loginMemberId).orElseThrow(MemberAccessDeniedException::new);
    }

    public Music verifedMusic(Long musicId) {
        return musicRepository.findById(musicId).orElseThrow(MusicNotFoundException::new);
    }
}
