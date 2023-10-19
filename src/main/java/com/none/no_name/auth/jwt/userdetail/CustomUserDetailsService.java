package com.none.no_name.auth.jwt.userdetail;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.repository.MemberRepository;
import com.none.no_name.global.exception.business.member.MemberNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private MemberRepository memberRepository;

	public CustomUserDetailsService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username).orElseThrow(MemberNotFoundException::new);

		return new CustomUserDetails(member);
	}
}
