package com.none.no_name.domain.playList.repository;

import com.none.no_name.domain.music.entity.Music;
import com.none.no_name.domain.playList.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long>, PlayListRepositoryCustom{

//    @Query("select p from PlayListLike p where p.playListLikeId =: playListLikeId")
//    Optional<Boolean> checkMemberLikedMusic(Long memberId);
}
