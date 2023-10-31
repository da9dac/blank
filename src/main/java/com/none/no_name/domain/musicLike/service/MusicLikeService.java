package com.none.no_name.domain.musicLike.service;


import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.member.repository.MemberRepository;
import com.none.no_name.domain.memberMusic.entity.MemberMusic;
import com.none.no_name.domain.memberMusic.repository.MemberMusicRepository;
import com.none.no_name.domain.music.dto.MusicInfo;
import com.none.no_name.domain.music.dto.MusicSort;
import com.none.no_name.domain.music.entity.Music;
import com.none.no_name.domain.music.repository.MusicRepository;
import com.none.no_name.domain.musicLike.entity.MusicLike;
import com.none.no_name.domain.musicLike.musicLikeRepository.MusicLikeRepository;
import com.none.no_name.global.exception.business.MusicLike.MusicLikeValidationException;
import com.none.no_name.global.exception.business.member.MemberAccessDeniedException;
import com.none.no_name.global.exception.business.music.MusicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MusicLikeService {

    private final MemberRepository memberRepository;
    private final MusicLikeRepository musicLikeRepository;
    private final MusicRepository musicRepository;
    private final MemberMusicRepository memberMusicRepository;

    public boolean updateLike(Long loginMemberId, Long musicId) {

        Member member = verifiedMember(loginMemberId);

        boolean isLiked =  isLiked(loginMemberId);

        if(!isLiked) {
            liked(loginMemberId, musicId);
            return true;

        } else {
            hated(loginMemberId, musicId);
            return false;
        }
    }

    private void liked(Long loginMemberId, Long musicId) {

        Member member = verifiedMember(loginMemberId);

        Music music = verifiedMusic(musicId);

        // MusicLike 엔티티를 조회하거나 생성합니다.
        MusicLike musicLike = musicLikeRepository.findByMusic(music)
                .orElse(MusicLike.builder()
                        .member(member)
                        .music(music)
                        .likes(0) // 처음 좋아요를 누를 때 0으로 설정
                        .build());

        // 좋아요 수 증가
        musicLike.addLikes();

        // MusicLike 엔티티를 저장 또는 업데이트
        musicLikeRepository.save(musicLike);
    }

    private void hated(Long loginMemberId, Long musicId) {

        Member member = verifiedMember(loginMemberId);

        Music music = verifiedMusic(musicId);

        MusicLike musicLike = musicLikeRepository.findByMusic(musicId)
                .orElse(MusicLike.builder()
                        .member(member)
                        .music(music)
                        .likes(0)
                        .build());

        musicLike.decreaseLikes();

        musicLikeRepository.findByMusic(music).ifPresent(musicLikeRepository::delete);
    }

    public MusicInfo getMusicLike(Long loginMemberId, Long musicId) {

        verifiedMember(loginMemberId);

        Music music = musicLikeRepository.findById(musicId).orElseThrow(MusicNotFoundException::new).getMusic();

        return MusicInfo.builder()
                .artistName(music.getArtistName())
                .albumName(music.getAlbumName())
                .musicTime(music.getMusicTime())
                .albumCoverImg(music.getAlbumCoverImag())
                .musicUri(music.getMusicUrl())
                .musicLikeCount(music.getMusicLikeCount())
                .tags(music.getTags())
                .build();
    }

    public Page<MusicInfo> getMusicLikes(Long musicId, Long loginMember, int page, int size) {

        Member member = verifiedMember(loginMember);

        Music findMusic = verifiedMusic(musicId);

        Page<MusicLike> musicLikes = musicLikeRepository.findAllByMusicIdAndMemberId(member, findMusic, PageRequest.of(page, size));

        // MusicLike 객체를 MusicInfo로 매핑
        Page<MusicInfo> musics = musicLikes.map(musicLike -> {
            Music music = musicLike.getMusic();
            // MusicLike 객체에서 MusicInfo 객체로 필요한 정보를 복사 또는 매핑

            return MusicInfo.builder()
                    .artistName(music.getArtistName())
                    .albumName(music.getAlbumName())
                    .musicTime(music.getMusicTime())
                    .albumCoverImg(music.getAlbumCoverImag())
                    .musicLikeCount(music.getMusicLikeCount())
                    .build();
        });

        return musics;
    }

    private Boolean isLiked(Long musicId) {

        Optional<Boolean> likedStatus = memberRepository.checkMemberLikedMusic(musicId);

        return likedStatus.orElse(true);
    }

    public Member verifiedMember(Long loginMember) {

        return memberRepository.findById(loginMember).orElseThrow(MemberAccessDeniedException::new);
    }

    public Music verifiedMusic(Long musicId) {

        return musicRepository.findById(musicId).orElseThrow(MusicNotFoundException::new);
    }
}
