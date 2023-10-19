package com.none.no_name.auth.oauth.service;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.http.MediaType.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.none.no_name.auth.jwt.userdetail.CustomUserDetails;
import com.none.no_name.auth.util.AuthConstant;
import com.none.no_name.auth.jwt.util.JwtProvider;
import com.none.no_name.auth.oauth.profile.MemberProfile;
import com.none.no_name.auth.oauth.provider.OAuthProvider;
import com.none.no_name.auth.oauth.service.dto.OAuthResult;
import com.none.no_name.domain.member.entity.Member;
import com.none.no_name.domain.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class OAuthService {
	private final InMemoryClientRegistrationRepository inMemoryRepository;
	private final MemberRepository memberRepository;
	private final JwtProvider jwtProvider;
	private final DefaultOAuth2UserService defaultOAuth2UserService;
	private final RestTemplate restTemplate;

	public OAuthService(InMemoryClientRegistrationRepository inMemoryRepository, MemberRepository memberRepository,
		JwtProvider jwtProvider, DefaultOAuth2UserService defaultOAuth2UserService, RestTemplate restTemplate) {
		this.inMemoryRepository = inMemoryRepository;
		this.memberRepository = memberRepository;
		this.jwtProvider = jwtProvider;
		this.defaultOAuth2UserService = defaultOAuth2UserService;
		this.restTemplate = restTemplate;
	}

	@Transactional
	public OAuthResult login(OAuthProvider provider, String code) {

		String registrationId = provider.getDescription();

		ClientRegistration clientRegistration = inMemoryRepository.findByRegistrationId(registrationId);

		String token = getToken(code, clientRegistration);

		OAuth2User oAuth2User = getOAuth2User(token, clientRegistration);

		Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

		MemberProfile memberProfile = OAuthProvider.extract(registrationId, attributes);

		Member member = getOrSaveMember(memberProfile);

		return createToken(member);
	}

	private MultiValueMap<String, String> tokenRequest(String code, ClientRegistration provider) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

		formData.add("code", code);
		formData.add("grant_type", "authorization_code");
		formData.add("redirect_uri", provider.getRedirectUri());
		formData.add("client_secret", provider.getClientSecret());
		formData.add("client_id",provider.getClientId());
		return formData;
	}

	private String getToken(String code, ClientRegistration clientRegistration) {

		String uri = clientRegistration.getProviderDetails().getTokenUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(APPLICATION_FORM_URLENCODED);
		headers.setAcceptCharset(List.of(UTF_8));

		HttpEntity<MultiValueMap<String, String>> entity =
			new HttpEntity<>(tokenRequest(code, clientRegistration), headers);

		try {
			ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.POST,
				entity,
				new ParameterizedTypeReference<>() {}
			);

			return responseEntity.getBody().get("access_token");

		} catch (HttpClientErrorException.BadRequest e) {
			throw new RuntimeException();
		}
	}

	private OAuth2User getOAuth2User(String token, ClientRegistration clientRegistration) {

		OAuth2AccessTokenResponse tokenResponse = OAuth2AccessTokenResponse.withToken(token)
			.tokenType(OAuth2AccessToken.TokenType.BEARER)
			.expiresIn(3600L)
			.build();

		OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, tokenResponse.getAccessToken());

		return defaultOAuth2UserService.loadUser(userRequest);
	}

	private Member getOrSaveMember(MemberProfile memberProfile) {
		Member member = getMember(memberProfile);
		if (member == null) {
			member = saveMember(memberProfile);
		}
		return member;
	}

	private Member getMember(MemberProfile memberProfile) {
		return memberRepository.findByEmail(memberProfile.getEmail())
			.orElse(null);
	}

	private Member saveMember(MemberProfile memberProfile) {
		Member member = Member.createMember(
			memberProfile.getEmail(),
			memberProfile.getEmail().split("@")[0],
			generateRandomNickname());

		return memberRepository.save(member);
	}

	private OAuthResult createToken(Member member) {
		CustomUserDetails userDetails = new CustomUserDetails(member);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		String accessToken = jwtProvider.createAccessToken(authentication, AuthConstant.ACCESS_TOKEN_EXPIRE_TIME);
		String refreshToken = jwtProvider.createRefreshToken(authentication, AuthConstant.ACCESS_TOKEN_EXPIRE_TIME);

		return OAuthResult.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	private String generateRandomNickname() {
		final String[] FIRST_WORDS =
			{"귀여운", "무서운", "책읽는", "코딩하는", "노래하는", "춤추는", "강력한", "잠자는", "빛나는", "대머리", "매력적인", "행복한", "슬픈", "신비로운"};

		final String[] SECOND_WORDS =
			{"호랑이", "고양이", "강아지", "팬더", "코끼리", "사자", "기린", "원숭이", "팽귄", "오리", "앵무새", "비둘기", "다람쥐", "참새"};

		Random random = new Random();
		String firstWord = FIRST_WORDS[random.nextInt(FIRST_WORDS.length)];
		String secondWord = SECOND_WORDS[random.nextInt(SECOND_WORDS.length)];

		return firstWord + " " + secondWord;
	}
}
