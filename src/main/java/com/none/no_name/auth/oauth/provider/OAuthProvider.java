package com.none.no_name.auth.oauth.provider;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.none.no_name.auth.oauth.profile.MemberProfile;
import com.none.no_name.global.base.BaseEnum;

public enum OAuthProvider implements BaseEnum {

	GOOGLE("google", (attributes) ->
		MemberProfile.builder()
			.email((String) attributes.get("email"))
			.build()),

	KAKAO("kakao", (attributes) -> {
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

		return MemberProfile.builder()
			.email((String) kakaoAccount.get("email"))
			.build();
	}),

	NAVER("naver", (attributes) -> {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");

		return MemberProfile.builder()
			.email((String) response.get("email"))
			.build();
	});

	private final String registrationId;
	private final Function<Map<String, Object>, MemberProfile> of;

	OAuthProvider(String registrationId, Function<Map<String, Object>, MemberProfile> of) {
		this.registrationId = registrationId;
		this.of = of;
	}

	public static MemberProfile extract(String registrationId, Map<String, Object> attributes) {
		return Arrays.stream(values())
			.filter(provider -> registrationId.equals(provider.registrationId))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new)
			.of.apply(attributes);
	}

	@Override
	@JsonValue
	public String getName() {
		return this.registrationId;
	}

	@Override
	public String getDescription() {
		return this.registrationId;
	}

	@JsonCreator
	public static OAuthProvider from(String value) {
		for (OAuthProvider provider : OAuthProvider.values()) {
			if (provider.getName().equalsIgnoreCase(value)) {
				return provider;
			}
		}
		return null;
	}
}