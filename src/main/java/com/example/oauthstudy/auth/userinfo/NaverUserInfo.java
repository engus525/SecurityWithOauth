package com.example.oauthstudy.auth.userinfo;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo
{
    private Map<String, Object> attributes;
    private Map<String, Object> attributesResponse;

    public NaverUserInfo(Map<String, Object> attributes)
    {
        this.attributes = (Map<String, Object>) attributes.get("response");
        this.attributesResponse = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Map<String, Object> getAttributes()
    {
        return this.attributes;
    }

    @Override
    public String getProviderId()
    {
        return attributesResponse.get("id").toString();
    }

    @Override
    public String getProvider()
    {
        return "naver";
    }

    @Override
    public String getEmail()
    {
        return attributesResponse.get("email").toString();
    }

    @Override
    public String getName()
    {
        return attributesResponse.get("name").toString();
    }
}
