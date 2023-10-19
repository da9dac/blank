package com.none.no_name.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.none.no_name.auth.controller.dto.OAuthRequest;
import com.none.no_name.auth.oauth.service.OAuthService;
import com.none.no_name.auth.oauth.service.dto.OAuthResult;
import com.none.no_name.auth.util.AuthConstant;
import com.none.no_name.domain.member.entity.Authority;
import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.repository.MemberRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private MemberRepository memberRepository;
	private PasswordEncoder passwordEncoder;
	private final OAuthService oAuthService;

	public AuthController(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
		OAuthService oAuthService) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.oAuthService = oAuthService;
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> signup() {
		memberRepository.save(
			Member.builder()
				.email("test@email.com")
				.password(passwordEncoder.encode("qwer1234!"))
				.authority(Authority.ROLE_USER)
				.build()
		);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/oauth")
	public ResponseEntity<Void> oauth(@RequestBody @Valid OAuthRequest request) {
		OAuthResult token = oAuthService.login(request.getProvider(), request.getCode());

		HttpHeaders headers = new HttpHeaders();
		headers.add(AuthConstant.AUTHORIZATION, AuthConstant.BEARER + token.getAccessToken());
		headers.add(AuthConstant.REFRESH, AuthConstant.BEARER + token.getRefreshToken());

		return ResponseEntity.ok().headers(headers).build();
	}

	@GetMapping("/oauth/code")
	public String code() {
		return "get code";
	}

	@GetMapping("/test")
	public String test() {
		return "테스트 성공";
	}
}
