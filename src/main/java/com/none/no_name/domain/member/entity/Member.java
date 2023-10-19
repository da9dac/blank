package com.none.no_name.domain.member.entity;

import com.none.no_name.domain.memberMusic.MemberMusic;
import com.none.no_name.domain.playList.PlayList;
import com.none.no_name.domain.playListLike.PlayListLike;
import com.none.no_name.global.base.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	private String nickname;

	private String email;

	private String password;

	private Authority authority;

	private Status status = Status.MEMBER_ACTIVE;

	@OneToMany(mappedBy = "member", cascade = ALL)
	private List<MemberMusic> memberMusics = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = ALL)
	private List<PlayList> playListList = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = ALL)
	private List<PlayListLike> playListLikeList = new ArrayList<>();

	public static Member createMember(String email, String password, String nickname) {
		return Member.builder()
			.email(email)
			.password(password)
			.nickname(nickname)
			.authority(Authority.ROLE_USER)
			.build();
	}

	private enum Status {
		MEMBER_ACTIVE("활동중"),
		MEMBER_DELETE("탈퇴한 회원");

		private String status;

		Status(String status) {
			this.status = status;
		}
	}
}
