package com.none.no_name.domain.musicLike.musicLikeRepository;

import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.music.entity.Music;
import com.none.no_name.domain.musicLike.entity.MusicLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MusicLikeRepository extends JpaRepository<MusicLike, Long> {

    @Query("select m from MusicLike m where m.music.musicId = :musicId")
    Optional<MusicLike> findByMusic(Music music);

    @Query("select m from MusicLike m where m.music = :music")
    Page<MusicLike> findAllByMusicIdAndMemberId(Member member, Music music, PageRequest pageRequest);
}