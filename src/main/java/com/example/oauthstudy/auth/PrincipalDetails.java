package com.example.oauthstudy.auth;

import com.example.oauthstudy.auth.userinfo.OAuth2UserInfo;
import com.example.oauthstudy.domain.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User
{

    private final User user;
    private OAuth2UserInfo oAuth2UserInfo;

    //UserDetails : Form 로그인 시 사용
    public PrincipalDetails(User user)
    {
        this.user = user;
    }

    //OAuth2User : OAuth2 로그인 시 사용
    public PrincipalDetails(User user, OAuth2UserInfo userInfo)
    {
        //PrincipalOauth2UserService 참고
        this.user = user;
        this.oAuth2UserInfo = userInfo;
    }

    /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority()
        {
            @Override
            public String getAuthority()
            {
                return user.getRole().toString();
            }
        });
        return collect;
    }

    /**
     * UserDetails 구현
     * 비밀번호를 리턴
     */
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    /**
     * UserDetails 구현
     * 계정 만료 여부
     * true : 만료안됨
     * false : 만료됨
     */
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 활성화 여부
     * true : 활성화됨
     * false : 활성화 안됨
     */
    @Override
    public boolean isEnabled()
    {
        return true;
    }


    /**
     * OAuth2User 구현
     *
     * @return
     */
    @Override
    public Map<String, Object> getAttributes()
    {
        return oAuth2UserInfo.getAttributes();
    }

    /**
     * OAuth2User 구현
     *
     * @return
     */
    @Override
    public String getName()
    {
        return oAuth2UserInfo.getProviderId();
    }
}
